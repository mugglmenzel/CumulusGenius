package de.eorg.cumulusgenius.shared.cloudmapping.model.mapping;


public class Preference {

	private int vmImage;
	private int service;
	private int latency;
	private int performance;
	private int cost;
	
	public Preference(int vmImage, int service, int latency, int performance, int cost)
	{
		this.vmImage = vmImage;
		this.service = service;
		this.latency = latency;
		this.performance = performance;
		this.cost = cost;
		
		
	}
	
}
