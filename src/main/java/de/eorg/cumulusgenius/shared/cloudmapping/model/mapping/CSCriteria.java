package de.eorg.cumulusgenius.shared.cloudmapping.model.mapping;


public class CSCriteria {


	private boolean initialLicenceCosts;
	private boolean hourlyLicenceCosts;
	private boolean popularity;
	private boolean maxLatency;
	private boolean avgLatency;
	private boolean cpu;
	private boolean ram;
	private boolean uptime;
	
	
	
	public CSCriteria(boolean cpu, boolean ram, boolean uptime, boolean popularity, boolean initialLicenceCosts, boolean hourlyLicenceCosts, boolean maxLatency, boolean avgLatency )
	{
		this.initialLicenceCosts = initialLicenceCosts;
		this.hourlyLicenceCosts = hourlyLicenceCosts;
		this.popularity = popularity;
		this.ram = ram;
		this.initialLicenceCosts = initialLicenceCosts;
		this.hourlyLicenceCosts = hourlyLicenceCosts;
		this.uptime = uptime;
		this.cpu = cpu;
	}

}
