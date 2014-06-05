/**
 * 
 */
package de.eorg.cumulusgenius.shared.cloudmapping;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import de.eorg.cumulusgenius.shared.cloudmapping.logic.ahp.AnalyticHierarchyProcess;
import de.eorg.cumulusgenius.shared.cloudmapping.model.BLOBStorage;
import de.eorg.cumulusgenius.shared.cloudmapping.model.ahp.configuration.Alternative;
import de.eorg.cumulusgenius.shared.cloudmapping.model.ahp.configuration.Criterion;
import de.eorg.cumulusgenius.shared.cloudmapping.model.ahp.configuration.CriterionType;
import de.eorg.cumulusgenius.shared.cloudmapping.model.ahp.configuration.Decision;
import de.eorg.cumulusgenius.shared.cloudmapping.model.ahp.configuration.Goal;
import de.eorg.cumulusgenius.shared.cloudmapping.model.ahp.configuration.GoalType;
import de.eorg.cumulusgenius.shared.cloudmapping.model.ahp.values.Evaluation;
import de.eorg.cumulusgenius.shared.cloudmapping.model.ahp.values.EvaluationResult;
import de.eorg.cumulusgenius.shared.cloudmapping.model.jama.Matrix;
import de.eorg.cumulusgenius.shared.cloudmapping.model.mapping.Attribute;
import de.eorg.cumulusgenius.shared.cloudmapping.model.mapping.EStorageServiceAttribute;
import de.eorg.cumulusgenius.shared.cloudmapping.model.mapping.Provider;
import de.eorg.cumulusgenius.shared.cloudmapping.model.mapping.StorageService;
import de.eorg.cumulusgenius.shared.cloudmapping.model.mapping.StorageServiceAlternative;

/**
 * @author mugglmenzel
 * 
 */
public class BLOBStorageSelection {
	
	
	
	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		
		List<BLOBStorage> blobs = new ArrayList<BLOBStorage>();
		blobs.add(new BLOBStorage("S3", 1500D, 700D, 300D, 3D, 1000000D, 45D, new Provider("Amazon AWS")));
		blobs.add(new BLOBStorage("BLOBStore", 300D, 200D, 600D, 4D, 2000000D, 40D, new Provider("Google")));
		
		Map<Alternative, Double> results = computeBLOBStorageQualitiesWithAHP(blobs);
		System.out.println("RESULTS:" + results);
		
	}
	

	public static Map<Alternative, Double> computeBLOBStorageQualitiesWithAHP(
			Collection<BLOBStorage> storages) throws Exception {

		long startTimeServiceModel = new Date().getTime();
		List<StorageService> services = new ArrayList<StorageService>();
		List<BLOBStorage> storagesList = new ArrayList<BLOBStorage>(storages);
		for (int i = 0; i < storages.size(); i++) {
			StorageService ss = new StorageService(storagesList.get(i)
					.getName());
			ss.addAttribute(
					new Attribute<Double>(EStorageServiceAttribute.STORAGECOST,
							storagesList.get(i).getStorageCost()));
			ss.addAttribute(
					new Attribute<Double>(
							EStorageServiceAttribute.NETWORKDOWNLOADCOST,
							storagesList.get(i).getNetworkDownloadCost()));
			ss.addAttribute(
					new Attribute<Double>(
							EStorageServiceAttribute.NETWORKUPLOADCOST,
							storagesList.get(i).getNetworkUploadCost()));
			ss.addAttribute(
					new Attribute<Double>(EStorageServiceAttribute.NETWORKHOPS,
							storagesList.get(i).getNetworkHops()));
			ss.addAttribute(
					new Attribute<Double>(
							EStorageServiceAttribute.NETWORKBANDWIDTH,
							storagesList.get(i).getBandwidthConnection()));
			ss.addAttribute(
					new Attribute<Double>(
							EStorageServiceAttribute.NETWORKLATENCY,
							storagesList.get(i).getLatencyConnection()));

			ss.setProvider(storagesList.get(i).getProvider());

			services.add(ss);
		}

		Decision sd = new Decision();
		sd.setName("Storage Service Decision");

		Goal bestService = new Goal("Best Storage");
		bestService.setGoalType(GoalType.POSITIVE);
		Criterion bandwidth = new Criterion("Bandwidth");
		bandwidth.setType(CriterionType.BENCHMARK);

		bestService.addChild(bandwidth);

		Goal cheapestService = new Goal("Cheapest Service");
		cheapestService.setGoalType(GoalType.NEGATIVE);
		Criterion storageCosts = new Criterion("Storage Costs");
		storageCosts.setType(CriterionType.QUANTITATIVE);
		Criterion uploadCosts = new Criterion("Upload Costs");
		uploadCosts.setType(CriterionType.QUANTITATIVE);
		Criterion downloadCosts = new Criterion("Download Costs");
		downloadCosts.setType(CriterionType.QUANTITATIVE);
		cheapestService.addChild(storageCosts);
		cheapestService.addChild(uploadCosts);
		cheapestService.addChild(downloadCosts);

		Goal latencyStorage = new Goal("Low Latency Service");
		latencyStorage.setGoalType(GoalType.NEGATIVE);
		Criterion storageLatency = new Criterion("Storage Latency");
		storageLatency.setType(CriterionType.BENCHMARK);
		Criterion networkHops = new Criterion("Network Hops");
		networkHops.setType(CriterionType.QUANTITATIVE);
		latencyStorage.addChild(storageLatency);
		latencyStorage.addChild(networkHops);

		sd.addGoal(bestService);
		sd.addGoal(cheapestService);
		sd.addGoal(latencyStorage);

		List<StorageServiceAlternative> ssAlternatives = new ArrayList<StorageServiceAlternative>();
		for (StorageService ss : services) {
			StorageServiceAlternative ssa = new StorageServiceAlternative(ss,
					"storage-" + ss.getName());
			ssAlternatives.add(ssa);
			sd.addAlternative(ssa);
		}

		List<Evaluation> serviceEvaluations = new ArrayList<Evaluation>();

		Evaluation evServiceQuality = new Evaluation();
		evServiceQuality.getEvaluations().add(
				createStorageMatrix(ssAlternatives,
						EStorageServiceAttribute.NETWORKBANDWIDTH));

		Evaluation evServiceCheapest = new Evaluation();
		evServiceCheapest.getEvaluations().add(
				createStorageMatrix(ssAlternatives,
						EStorageServiceAttribute.STORAGECOST));
		evServiceCheapest.getEvaluations().add(
				createStorageMatrix(ssAlternatives,
						EStorageServiceAttribute.NETWORKDOWNLOADCOST));
		evServiceCheapest.getEvaluations().add(
				createStorageMatrix(ssAlternatives,
						EStorageServiceAttribute.NETWORKUPLOADCOST));
		Evaluation evServiceLatency = new Evaluation();
		evServiceLatency.getEvaluations().add(
				createStorageMatrix(ssAlternatives,
						EStorageServiceAttribute.NETWORKLATENCY));
		evServiceLatency.getEvaluations().add(
				createStorageMatrix(ssAlternatives,
						EStorageServiceAttribute.NETWORKHOPS));
		serviceEvaluations.add(evServiceQuality);
		serviceEvaluations.add(evServiceCheapest);
		serviceEvaluations.add(evServiceLatency);

		long endTimeServiceModel = new Date().getTime();
		System.out.println("Storage Service Model Creation took "
				+ (endTimeServiceModel - startTimeServiceModel) + " ms");

		long startTimeServiceEval = new Date().getTime();
		AnalyticHierarchyProcess ahpService = new AnalyticHierarchyProcess(sd);
		EvaluationResult serviceEvalResult = ahpService.evaluateFull(
				serviceEvaluations, false);
		long endTimeServiceEval = new Date().getTime();
		System.out.println("Storage Service Evaluation took "
				+ (endTimeServiceEval - startTimeServiceEval) + " ms");

		return serviceEvalResult.getResultMultiplicativeIndexMap();
	}

	private static Matrix createStorageMatrix(
			List<StorageServiceAlternative> alt, EStorageServiceAttribute attr) {
		double[][] critEv = new double[alt.size()][alt.size()];
		double c;

		for (int a = 0; a < alt.size(); a++) {
			c = (Double) alt.get(a).getStorageService().getAttribute(attr)
					.getValue();
			for (int b = 0; b < alt.size(); b++) {

				critEv[a][b] = c
						/ (Double) alt.get(b).getStorageService()
								.getAttribute(attr).getValue();
				// System.out.println("[" + critEv[a][b] + "]");
			}
			// System.out.println("\n");
		}
		Matrix mat = new Matrix(critEv);

		return mat;
	}

}
