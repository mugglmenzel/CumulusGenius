package de.eorg.cumulusgenius.shared.cloudmapping;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import de.eorg.cumulusgenius.shared.cloudmapping.logic.ahp.AnalyticHierarchyProcess;
import de.eorg.cumulusgenius.shared.cloudmapping.model.AMI;
import de.eorg.cumulusgenius.shared.cloudmapping.model.DeploymentManager;
import de.eorg.cumulusgenius.shared.cloudmapping.model.EC2Resource;
import de.eorg.cumulusgenius.shared.cloudmapping.model.ValueComparator;
import de.eorg.cumulusgenius.shared.cloudmapping.model.ahp.configuration.Alternative;
import de.eorg.cumulusgenius.shared.cloudmapping.model.ahp.configuration.Criterion;
import de.eorg.cumulusgenius.shared.cloudmapping.model.ahp.configuration.Goal;
import de.eorg.cumulusgenius.shared.cloudmapping.model.ahp.configuration.GoalType;
import de.eorg.cumulusgenius.shared.cloudmapping.model.ahp.values.Evaluation;
import de.eorg.cumulusgenius.shared.cloudmapping.model.ahp.values.EvaluationResult;
import de.eorg.cumulusgenius.shared.cloudmapping.model.jama.Matrix;
import de.eorg.cumulusgenius.shared.cloudmapping.model.mapping.Appliance;
import de.eorg.cumulusgenius.shared.cloudmapping.model.mapping.Attribute;
import de.eorg.cumulusgenius.shared.cloudmapping.model.mapping.ComputeService;
import de.eorg.cumulusgenius.shared.cloudmapping.model.mapping.EComputeServiceAttribute;
import de.eorg.cumulusgenius.shared.cloudmapping.model.mapping.Instance;
import de.eorg.cumulusgenius.shared.cloudmapping.model.mapping.InstanceAlternative;
import de.eorg.cumulusgenius.shared.cloudmapping.model.mapping.InstanceDecision;
import de.eorg.cumulusgenius.shared.cloudmapping.model.mapping.requirements.MaxRequirement;
import de.eorg.cumulusgenius.shared.cloudmapping.model.mapping.requirements.MinRequirement;
import de.eorg.cumulusgenius.shared.cloudmapping.model.mapping.requirements.RequirementItem;


public class CloudComponentMapper {

	/**
	 * @param args
	 */
	final static List<EC2Resource> resources = new ArrayList<EC2Resource>();
	final static List<AMI> amis = new ArrayList<AMI>();
	private static List<Instance> instances = new ArrayList<Instance>();

	final static String acceskey = "";
	final static String secretkey = "";

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// add your amis and hardware components
		amis.add(new AMI("ami-27fb3f4e"));
		amis.add(new AMI("AMI_Name_234"));
		EC2Resource ec1 = new EC2Resource("m1.small");
		ec1.addAttribute(
				new Attribute<Double>(EComputeServiceAttribute.COSTPERHOUR, 5D));
		ec1.addAttribute(
				new Attribute<Double>(EComputeServiceAttribute.CPUBENCHMARK, 30D));
		EC2Resource ec2 = new EC2Resource("m1.large");
		ec2.addAttribute(
				new Attribute<Double>(EComputeServiceAttribute.COSTPERHOUR, 10D));
		ec2.addAttribute(
				new Attribute<Double>(EComputeServiceAttribute.CPUBENCHMARK, 12D));
		EC2Resource ec3 = new EC2Resource("m1.xlarge");
		ec3.addAttribute(
				new Attribute<Double>(EComputeServiceAttribute.COSTPERHOUR, 25D));
		ec3.addAttribute(
				new Attribute<Double>(EComputeServiceAttribute.CPUBENCHMARK, 32D));
		resources.add(ec1);
		resources.add(ec2);
		resources.add(ec3);

		for (Appliance ami : amis) {

			/*
			 * At first we need to define the decision we want to make.After
			 * that we need do define goals we want to reach. This is important
			 * because different aspects need different consideration e.g. price
			 * and performance
			 */

			InstanceDecision decision = new InstanceDecision();
			decision.setName("Optimal cloud component mapping");

			decision.getFctRequirements().add(
					new MinRequirement<Double>("Min Benchmark Result",
							EComputeServiceAttribute.CPUBENCHMARK, new RequirementItem<Double>(
									15D)));
			decision.getFctRequirements().add(
					new MaxRequirement<Double>("Max Costs",
							EComputeServiceAttribute.COSTPERHOUR,
							new RequirementItem<Double>(20D)));

			/*
			 * Mapping AMIs and ec2 resources to get possible alternatives
			 */

			for (ComputeService resource : resources) {

				InstanceAlternative alternative = new InstanceAlternative(
						new Instance(ami, resource), ami.getName() + "->"
								+ resource.getName());

				alternative.setDescription("AMI: " + ami.getName()
						+ "EC2_Resource: " + resource.getName());

				decision.addAlternative(alternative);
				System.out.println(alternative.getName());

			}

			// adding goals
			// one for performance
			Goal goal_1 = new Goal();
			goal_1.setName("Find the most powerfull mapping");
			goal_1.setWeight(1);
			goal_1.setGoalType(GoalType.POSITIVE);
			decision.addGoal(goal_1);

			// lets say there are two benchmarking values
			Criterion g1c1 = new Criterion("BenchmarkValue1");
			Criterion g1c2 = new Criterion("BenchmarkValue2");

			goal_1.addChild(g1c1);
			goal_1.addChild(g1c2);

			// one for costs
			Goal goal_2 = new Goal();
			goal_2.setName("Find the cheapest mapping for your needs");
			goal_2.setWeight(1);
			goal_2.setGoalType(GoalType.NEGATIVE);
			decision.addGoal(goal_2);

			// costs per hour
			Criterion g2c1 = new Criterion("costsPerHour");

			goal_2.addChild(g2c1);

			decision.reqCheck();
			System.out.println("req check:" + decision.isValidMapping());

			// start with AHP

			AnalyticHierarchyProcess ahp = new AnalyticHierarchyProcess(
					decision);

			System.out.println("\n weights of criteria");

			// TODO: Following in a UI -> ask user for his preferences

			// First we have weight our different criteria
			// in this moment via hardcode
			double[][] criteriaG1 = { { 1D, 1D }, { 1D, 1D } };
			Matrix cgoal_1 = new Matrix(criteriaG1);
			ahp.setChildrenCriteriaWeights(goal_1, cgoal_1, 4);

			// since we have only one criteria regarding goal 2 our matrix is a
			// bit degenerated

			double[][] criteriaG2 = { { 1 } };
			Matrix cgoal_2 = new Matrix(criteriaG2);
			ahp.setChildrenCriteriaWeights(goal_2, cgoal_2, 4);

			// Evaluation...

			// ...1.) of performance

			// for (int i = 0; i < 1; i++) {

			List<Evaluation> evaluations = new ArrayList<Evaluation>();
			Evaluation evG1 = new Evaluation();
			evG1.getEvaluations().add(createBench1Matrix(decision.getAlternatives()));
			evG1.getEvaluations().add(createBench2Matrix(decision.getAlternatives()));
			evaluations.add(evG1);

			Evaluation evG2 = new Evaluation();
			evG2.getEvaluations().add(createCostMatrix(decision.getAlternatives()));
			evaluations.add(evG2);

			try {
				System.out.println("\n ----results--- \n");
				System.out.println(decision.getGoals().iterator().next()
						.getLeafCriteria());
				EvaluationResult results = ahp.evaluateFull(evaluations);
				ValueComparator vcp = new ValueComparator(
						results.getResultMultiplicativeIndexMap());
				TreeMap<Alternative, Double> sortedResults = new TreeMap<Alternative, Double>(
						vcp);
				sortedResults.putAll(results.getResultMultiplicativeIndexMap());
				System.out.println("\n" + results);
				System.out.println("\n" + sortedResults + "\n");

				// TODO: result festhalten, richtige resource wählen!!!
				instances.add(((InstanceAlternative) sortedResults.firstKey())
						.getInstance());
				System.out.println("The best choice for your needs is: \n "
						+ sortedResults.firstEntry().getKey().toString() + "\n"
						+ "with an absolut value of: \n"
						+ sortedResults.firstEntry().getValue());
				System.out.println("\n ##### END OF PROCESS ##### \n");
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		System.out.println("Start deploying... \n");
		DeploymentManager dpm = new DeploymentManager(instances.get(0),
				acceskey, secretkey);
		dpm.deployInstance("test_01");
	}

	private static Matrix createBench1Matrix(List<InstanceAlternative> alt) {
		double[][] critEv = new double[resources.size()][resources.size()];
		double c;

		for (int a = 0; a < resources.size(); a++) {
			c = (Double) alt.get(a).getInstance().getComputeService()
					.getAttribute(EComputeServiceAttribute.CPUBENCHMARK).getValue();
			for (int b = 0; b < resources.size(); b++) {

				critEv[a][b] = c
						/ (Double) alt.get(b).getInstance().getComputeService()
								.getAttribute(EComputeServiceAttribute.CPUBENCHMARK).getValue();
				System.out.println("[" + critEv[a][b] + "]");
			}
			System.out.println("\n");
		}
		Matrix bench1Evalue = new Matrix(critEv);

		return bench1Evalue;
	}

	private static Matrix createBench2Matrix(List<InstanceAlternative> alt) {
		double[][] critEv = new double[resources.size()][resources.size()];
		double c;
		for (int a = 0; a < resources.size(); a++) {
			c = (Double) alt.get(a).getInstance().getComputeService()
					.getAttribute(EComputeServiceAttribute.CPUBENCHMARK).getValue();
			for (int b = 0; b < resources.size(); b++) {
				critEv[a][b] = c
						/ (Double) alt.get(b).getInstance().getComputeService()
								.getAttribute(EComputeServiceAttribute.CPUBENCHMARK).getValue();
			}

		}

		Matrix bench2Evalue = new Matrix(critEv);
		return bench2Evalue;
	}

	private static Matrix createCostMatrix(List<InstanceAlternative> alt) {
		double[][] critEv = new double[resources.size()][resources.size()];
		double c;

		for (int a = 0; a < resources.size(); a++) {
			c = (Double) alt.get(a).getInstance().getComputeService()
					.getAttribute(EComputeServiceAttribute.COSTPERHOUR).getValue();
			for (int b = 0; b < resources.size(); b++) {
				critEv[a][b] = c
						/ (Double) alt.get(b).getInstance().getComputeService()
								.getAttribute(EComputeServiceAttribute.COSTPERHOUR)
								.getValue();
			}

		}
		Matrix costEvalue = new Matrix(critEv);
		return costEvalue;
	}

}
