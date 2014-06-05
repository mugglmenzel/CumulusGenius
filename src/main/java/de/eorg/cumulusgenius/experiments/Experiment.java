package de.eorg.cumulusgenius.experiments;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.logging.LogManager;

import de.eorg.cumulusgenius.shared.cloudmapping.logic.ahp.AnalyticHierarchyProcess;
import de.eorg.cumulusgenius.shared.cloudmapping.model.AMI;
import de.eorg.cumulusgenius.shared.cloudmapping.model.EC2Resource;
import de.eorg.cumulusgenius.shared.cloudmapping.model.ahp.configuration.Criterion;
import de.eorg.cumulusgenius.shared.cloudmapping.model.ahp.configuration.CriterionType;
import de.eorg.cumulusgenius.shared.cloudmapping.model.ahp.configuration.Goal;
import de.eorg.cumulusgenius.shared.cloudmapping.model.ahp.configuration.GoalType;
import de.eorg.cumulusgenius.shared.cloudmapping.model.ahp.values.Evaluation;
import de.eorg.cumulusgenius.shared.cloudmapping.model.ahp.values.EvaluationResult;
import de.eorg.cumulusgenius.shared.cloudmapping.model.jama.Matrix;
import de.eorg.cumulusgenius.shared.cloudmapping.model.mapping.ApplianceAlternative;
import de.eorg.cumulusgenius.shared.cloudmapping.model.mapping.ApplianceDecision;
import de.eorg.cumulusgenius.shared.cloudmapping.model.mapping.Attribute;
import de.eorg.cumulusgenius.shared.cloudmapping.model.mapping.CombinationValue;
import de.eorg.cumulusgenius.shared.cloudmapping.model.mapping.ComputeDecision;
import de.eorg.cumulusgenius.shared.cloudmapping.model.mapping.ComputeServiceAlternative;
import de.eorg.cumulusgenius.shared.cloudmapping.model.mapping.EApplianceAttribute;
import de.eorg.cumulusgenius.shared.cloudmapping.model.mapping.EComputeServiceAttribute;

public class Experiment {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		FileWriter fw = new FileWriter("out.txt");
		BufferedWriter out = new BufferedWriter(fw);
		out.write("Number of AMIs,Number of Services,AMIs Model Creation (ms),AMIs Evaluation (ms),Services Model Creation (ms),Services Evaluation (ms),Combination Computation (ms),Total (ms)\n");
		out.close();

		for (int i = 100; i <= 1000; i = i + 100)
			for (int j = 100; j <= 1000; j = j + 100) {
				System.out.println("Running AMIs=" + i + ", Services=" + j);
				computeSolutions(i, j);
			}

	}

	private static void computeSolutions(int numAMIs, int numServices)
			throws Exception {

		LogManager.getLogManager().readConfiguration(
				new FileInputStream(new File("logging.properties")));

		long startTimeAMIModel = new Date().getTime();
		List<AMI> amis = new ArrayList<AMI>();
		for (int i = 0; i < numAMIs; i++) {
			AMI ami = new AMI("ami-" + i);
			ami.addAttribute(
					new Attribute<Double>(EApplianceAttribute.COSTPERHOUR,
							new Double((Math.random() * 0.4 + 0.1))));
			ami.addAttribute(
					new Attribute<Double>(EApplianceAttribute.POPULARITY,
							new Double(Math.random())));
			amis.add(ami);

		}

		// System.out.println("Generated AMIs: " + amis);

		ApplianceDecision ad = new ApplianceDecision();
		ad.setName("AMI Decision");

		Goal bestAppliance = new Goal("Best Appliance");
		bestAppliance.setGoalType(GoalType.POSITIVE);
		Criterion appliancePopularity = new Criterion("Appliance Popularity");
		appliancePopularity.setType(CriterionType.QUANTITATIVE);
		bestAppliance.addChild(appliancePopularity);

		Goal cheapestAppliance = new Goal("Cheapest Appliance");
		cheapestAppliance.setGoalType(GoalType.NEGATIVE);
		Criterion applianceCosts = new Criterion("Appliance Costs");
		applianceCosts.setType(CriterionType.QUANTITATIVE);
		cheapestAppliance.addChild(applianceCosts);

		ad.addGoal(bestAppliance);
		ad.addGoal(cheapestAppliance);

		List<ApplianceAlternative> applianceAlternatives = new ArrayList<ApplianceAlternative>();
		for (AMI a : amis) {
			ApplianceAlternative aa = new ApplianceAlternative(a,
					"alternative-" + a.getName());
			applianceAlternatives.add(aa);
			ad.addAlternative(aa);
		}

		// System.out.println("AMI Decision: " + ad);

		List<Evaluation> amiEvaluations = new ArrayList<Evaluation>();
		Evaluation evBest = new Evaluation();
		evBest.getEvaluations().add(
				createAMIMatrix(applianceAlternatives,
						EApplianceAttribute.POPULARITY));
		Evaluation evCheapest = new Evaluation();
		evCheapest.getEvaluations().add(
				createAMIMatrix(applianceAlternatives,
						EApplianceAttribute.COSTPERHOUR));
		amiEvaluations.add(evBest);
		amiEvaluations.add(evCheapest);

		long endTimeAMIModel = new Date().getTime();
		System.out.println("AMI Model Creation took "
				+ (endTimeAMIModel - startTimeAMIModel) + " ms");

		long startTimeAMIEval = new Date().getTime();
		AnalyticHierarchyProcess ahpAMI = new AnalyticHierarchyProcess(ad);
		EvaluationResult amiEvalResult = ahpAMI.evaluateFull(amiEvaluations);
		long endTimeAMIEval = new Date().getTime();
		System.out.println("AMI Evaluation took "
				+ (endTimeAMIEval - startTimeAMIEval) + " ms");

		long startTimeServiceModel = new Date().getTime();
		List<EC2Resource> services = new ArrayList<EC2Resource>();
		for (int i = 0; i < numServices; i++) {
			EC2Resource ec2 = new EC2Resource("ec2-" + i);
			ec2.addAttribute(
					new Attribute<Double>(EComputeServiceAttribute.COSTPERHOUR,
							new Double((Math.random() * 0.39 + 0.01))));
			ec2.addAttribute(
					new Attribute<Double>(
							EComputeServiceAttribute.CPUBENCHMARK, new Double(
									Math.random() * 1000)));
			ec2.addAttribute(
					new Attribute<Double>(
							EComputeServiceAttribute.RAMBENCHMARK, new Double(
									Math.random() * 1000)));
			ec2.addAttribute(
					new Attribute<Double>(
							EComputeServiceAttribute.DISKBENCHMARK, new Double(
									Math.random() * 1000)));
			ec2.addAttribute(
					new Attribute<Double>(EComputeServiceAttribute.MAXLATENCY,
							new Double(Math.random() * 450 + 50)));
			ec2.addAttribute(
					new Attribute<Double>(EComputeServiceAttribute.AVGLATENCY,
							new Double(Math.random() * 490 + 10)));
			ec2.addAttribute(
					new Attribute<Double>(
							EComputeServiceAttribute.SERVICEPOPULARITY,
							new Double(Math.random())));
			ec2.addAttribute(
					new Attribute<Double>(EComputeServiceAttribute.UPTIME,
							new Double(Math.random() * 0.1 + 0.9)));

			services.add(ec2);
		}

		ComputeDecision sd = new ComputeDecision();
		sd.setName("Service Decision");

		Goal bestService = new Goal("Best Service");
		bestService.setGoalType(GoalType.POSITIVE);
		Criterion servicePopularity = new Criterion("Service Popularity");
		servicePopularity.setType(CriterionType.QUANTITATIVE);
		Criterion serviceCPU = new Criterion("Service CPU");
		serviceCPU.setType(CriterionType.BENCHMARK);
		Criterion serviceRAM = new Criterion("Service RAM");
		serviceRAM.setType(CriterionType.BENCHMARK);
		Criterion serviceDisk = new Criterion("Service Disk");
		serviceDisk.setType(CriterionType.BENCHMARK);
		Criterion serviceUptime = new Criterion("Service Uptime");
		serviceUptime.setType(CriterionType.QUANTITATIVE);

		bestService.addChild(servicePopularity);
		bestService.addChild(serviceCPU);
		bestService.addChild(serviceRAM);
		bestService.addChild(serviceDisk);
		bestService.addChild(serviceUptime);

		Goal cheapestService = new Goal("Cheapest Service");
		cheapestAppliance.setGoalType(GoalType.NEGATIVE);
		Criterion serviceCosts = new Criterion("Service Costs");
		serviceCosts.setType(CriterionType.QUANTITATIVE);
		cheapestService.addChild(applianceCosts);

		Goal latencyService = new Goal("Low Latency Service");
		latencyService.setGoalType(GoalType.NEGATIVE);
		Criterion serviceMaxLatency = new Criterion("Service Max Latency");
		serviceMaxLatency.setType(CriterionType.BENCHMARK);
		Criterion serviceAvgLatency = new Criterion("Service Avg Latency");
		serviceAvgLatency.setType(CriterionType.BENCHMARK);
		latencyService.addChild(serviceMaxLatency);
		latencyService.addChild(serviceAvgLatency);

		sd.addGoal(bestService);
		sd.addGoal(cheapestService);
		sd.addGoal(latencyService);

		List<ComputeServiceAlternative> ec2Alternatives = new ArrayList<ComputeServiceAlternative>();
		for (EC2Resource e : services) {
			ComputeServiceAlternative ea = new ComputeServiceAlternative(e,
					"alternative-" + e.getName());
			ec2Alternatives.add(ea);
			sd.addAlternative(ea);
		}

		List<Evaluation> serviceEvaluations = new ArrayList<Evaluation>();

		Evaluation evServicePopularity = new Evaluation();
		evServicePopularity.getEvaluations().add(
				createServiceMatrix(ec2Alternatives,
						EComputeServiceAttribute.SERVICEPOPULARITY));
		evServicePopularity.getEvaluations().add(
				createServiceMatrix(ec2Alternatives,
						EComputeServiceAttribute.CPUBENCHMARK));
		evServicePopularity.getEvaluations().add(
				createServiceMatrix(ec2Alternatives,
						EComputeServiceAttribute.RAMBENCHMARK));
		evServicePopularity.getEvaluations().add(
				createServiceMatrix(ec2Alternatives,
						EComputeServiceAttribute.DISKBENCHMARK));
		Evaluation evServiceCheapest = new Evaluation();
		evServiceCheapest.getEvaluations().add(
				createServiceMatrix(ec2Alternatives,
						EComputeServiceAttribute.COSTPERHOUR));
		Evaluation evServiceLatency = new Evaluation();
		evServiceLatency.getEvaluations().add(
				createServiceMatrix(ec2Alternatives,
						EComputeServiceAttribute.MAXLATENCY));
		evServiceLatency.getEvaluations().add(
				createServiceMatrix(ec2Alternatives,
						EComputeServiceAttribute.AVGLATENCY));
		serviceEvaluations.add(evServicePopularity);
		serviceEvaluations.add(evServiceCheapest);
		serviceEvaluations.add(evServiceLatency);

		long endTimeServiceModel = new Date().getTime();
		System.out.println("Service Model Creation took "
				+ (endTimeServiceModel - startTimeServiceModel) + " ms");

		long startTimeServiceEval = new Date().getTime();
		AnalyticHierarchyProcess ahpService = new AnalyticHierarchyProcess(sd);
		EvaluationResult serviceEvalResult = ahpService
				.evaluateFull(serviceEvaluations);
		long endTimeServiceEval = new Date().getTime();
		System.out.println("Service Evaluation took "
				+ (endTimeServiceEval - startTimeServiceEval) + " ms");

		long startTimeCombined = new Date().getTime();
		SortedSet<CombinationValue> combinations = new TreeSet<CombinationValue>();
		for (ApplianceAlternative aa : applianceAlternatives)
			for (ComputeServiceAlternative csa : ec2Alternatives)
				combinations.add(new CombinationValue(aa.getAppl(), csa
						.getComputeService(), amiEvalResult
						.getResultMultiplicativeIndexMap().get(aa),
						serviceEvalResult.getResultMultiplicativeIndexMap()
								.get(csa)));
		long endTimeCombined = new Date().getTime();
		System.out.println("Combination took "
				+ (endTimeCombined - startTimeCombined) + " ms");
		System.out.println("Worst Combination: " + combinations.first());
		System.out.println("Best Combination: " + combinations.last());

		FileWriter fw = new FileWriter("out.txt", true);
		BufferedWriter out = new BufferedWriter(fw);
		out.write(""
				+ numAMIs
				+ ","
				+ numServices
				+ ","
				+ (endTimeAMIModel - startTimeAMIModel)
				+ ","
				+ (endTimeAMIEval - startTimeAMIEval)
				+ ","
				+ (endTimeServiceModel - startTimeServiceModel)
				+ ","
				+ (endTimeServiceEval - startTimeServiceEval)
				+ ","
				+ (endTimeCombined - startTimeCombined)
				+ ","
				+ ((endTimeAMIModel - startTimeAMIModel)
						+ (endTimeAMIEval - startTimeAMIEval)
						+ (endTimeServiceModel - startTimeServiceModel)
						+ (endTimeServiceEval - startTimeServiceEval) + (endTimeCombined - startTimeCombined))
				+ "\n");
		out.close();
	}

	private static Matrix createAMIMatrix(List<ApplianceAlternative> alt,
			EApplianceAttribute attr) {
		double[][] critEv = new double[alt.size()][alt.size()];
		double c;

		for (int a = 0; a < alt.size(); a++) {
			c = (Double) alt.get(a).getAppl().getAttribute(attr).getValue();
			for (int b = 0; b < alt.size(); b++) {

				critEv[a][b] = c
						/ (Double) alt.get(b).getAppl().getAttribute(attr)
								.getValue();
				// System.out.println("[" + critEv[a][b] + "]");
			}
			// System.out.println("\n");
		}
		Matrix mat = new Matrix(critEv);

		return mat;
	}

	private static Matrix createServiceMatrix(
			List<ComputeServiceAlternative> alt, EComputeServiceAttribute attr) {
		double[][] critEv = new double[alt.size()][alt.size()];
		double c;

		for (int a = 0; a < alt.size(); a++) {
			c = (Double) alt.get(a).getComputeService().getAttribute(attr)
					.getValue();
			for (int b = 0; b < alt.size(); b++) {

				critEv[a][b] = c
						/ (Double) alt.get(b).getComputeService()
								.getAttribute(attr).getValue();
				// System.out.println("[" + critEv[a][b] + "]");
			}
			// System.out.println("\n");
		}
		Matrix mat = new Matrix(critEv);

		return mat;
	}

}
