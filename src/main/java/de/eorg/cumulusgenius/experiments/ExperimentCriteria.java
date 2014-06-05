package de.eorg.cumulusgenius.experiments;

import de.eorg.cumulusgenius.shared.cloudmapping.logic.ahp.AnalyticHierarchyProcess;
import de.eorg.cumulusgenius.shared.cloudmapping.model.EC2Resource;
import de.eorg.cumulusgenius.shared.cloudmapping.model.ahp.configuration.Criterion;
import de.eorg.cumulusgenius.shared.cloudmapping.model.ahp.configuration.CriterionType;
import de.eorg.cumulusgenius.shared.cloudmapping.model.ahp.configuration.Goal;
import de.eorg.cumulusgenius.shared.cloudmapping.model.ahp.configuration.GoalType;
import de.eorg.cumulusgenius.shared.cloudmapping.model.ahp.values.Evaluation;
import de.eorg.cumulusgenius.shared.cloudmapping.model.ahp.values.EvaluationResult;
import de.eorg.cumulusgenius.shared.cloudmapping.model.jama.Matrix;
import de.eorg.cumulusgenius.shared.cloudmapping.model.mapping.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.LogManager;

public class ExperimentCriteria {

    static String fileName = "out-criteria.txt";
    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        FileWriter fw = new FileWriter(fileName);
        BufferedWriter out = new BufferedWriter(fw);
        out.write("Number of Compute Services,Number of Criteria,Decision Model Creation (ms),Scoring (ms)\n");
        out.close();

        Thread.sleep(5000);

        int numServices = 100;
        int repetitions = 20;
        boolean saaty = true;

        int i = numServices;
        for (int j = 100; j <= 1000; j = j + 100) {
            System.out.println("Running Criteria=" + j + ", Services=" + i);
            computeSolutions(i, j, repetitions, saaty);
        }

    }

    private static void computeSolutions(int numServices, int numCriteria, int repetitions, boolean saaty)
            throws Exception {

        LogManager.getLogManager().readConfiguration(
                new FileInputStream(new File("logging.properties")));

        long timeServiceModel = 0L;
        long timeServiceEval = 0L;

        long startTimeServiceModel = new Date().getTime();

        List<String> attributes = new ArrayList<String>();
        for (int i = 0; i < numCriteria; i++) {
            attributes.add("attribute-" + i);
        }

        List<EC2Resource> services = new ArrayList<EC2Resource>();
        for (int i = 0; i < numServices; i++) {
            EC2Resource ec2 = new EC2Resource("ec2-" + i);
            for (String attribute : attributes)
                ec2.addAttribute(
                        new Attribute<Double>(new VariableAttributeName(attribute),
                                new Double((Math.random() * 1000)))
                );

            services.add(ec2);
        }

        ComputeDecision sd = new ComputeDecision();
        sd.setName("Service Decision");

        Goal bestService = new Goal("Best Service");
        bestService.setGoalType(GoalType.POSITIVE);

        for (String attribute : attributes) {
            bestService.addChild(new Criterion("criterion-" + attribute, CriterionType.BENCHMARK));
        }

        sd.addGoal(bestService);


        List<ComputeServiceAlternative> ec2Alternatives = new ArrayList<ComputeServiceAlternative>();
        for (EC2Resource e : services) {
            ComputeServiceAlternative ea = new ComputeServiceAlternative(e,
                    "alternative-" + e.getName());
            ec2Alternatives.add(ea);
            sd.addAlternative(ea);
        }

        List<Evaluation> serviceEvaluations = new ArrayList<Evaluation>();
        Evaluation evBestService = new Evaluation();
        ExecutorService exec = Executors.newFixedThreadPool(16);
        List<Future<Matrix>> futures = new ArrayList<Future<Matrix>>();

        for (String attribute : attributes) {
            Callable<Matrix> c = new ServiceMatrixFiller(ec2Alternatives, new VariableAttributeName(attribute));
            futures.add(exec.submit(c));
        }
        exec.shutdown();

        for (Future<Matrix> f : futures)
            evBestService.getEvaluations().add(f.get());
        serviceEvaluations.add(evBestService);

        AnalyticHierarchyProcess ahpService = new AnalyticHierarchyProcess(sd);
        ahpService.calculateWeights(saaty);

        long endTimeServiceModel = new Date().getTime();
        timeServiceModel +=  endTimeServiceModel - startTimeServiceModel;

        for(int r = 0; r < repetitions; r++) {
            long startTimeServiceEval = new Date().getTime();
            ahpService
                    .calculateAlternativeValues(serviceEvaluations, saaty);
            ahpService.evaluate();
            long endTimeServiceEval = new Date().getTime();
            timeServiceEval += endTimeServiceEval - startTimeServiceEval;
        }

        System.out.println("Service Model Creation took "
                + (timeServiceModel / repetitions) + " ms");
        System.out.println("Service Evaluation took "
                + (timeServiceEval / repetitions) + " ms");

        FileWriter fw = new FileWriter(fileName, true);
        BufferedWriter out = new BufferedWriter(fw);
        out.write(""
                + numServices
                + ","
                + numCriteria
                + ","
                + (timeServiceModel / repetitions)
                + ","
                + (timeServiceEval / repetitions)
                + "\n");
        out.close();
    }


    private static class ServiceMatrixFiller implements Callable<Matrix> {
        List<ComputeServiceAlternative> alt;
        IEAttribute attr;

        private ServiceMatrixFiller(
                List<ComputeServiceAlternative> alt, IEAttribute attr) {
            this.alt = alt;
            this.attr = attr;
        }

        @Override
        public Matrix call() throws Exception {
            double[][] critEv = new double[alt.size()][alt.size()];

            for (int a = 0; a < alt.size(); a++) {
                double c = (Double) alt.get(a).getComputeService().getAttribute(attr.getName())
                        .getValue();
                for (int b = 0; b < alt.size(); b++) {
                    critEv[a][b] = c / (Double) alt.get(b).getComputeService()
                            .getAttribute(attr.getName()).getValue();

                }
            }

            return new Matrix(critEv);
        }
    }

}
