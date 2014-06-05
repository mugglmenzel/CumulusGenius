package de.eorg.cumulusgenius.shared.cloudmapping.model.mapping;


public class VMCriteria {


	private boolean initialLicenceCosts;
	private boolean hourlyLicenceCosts;
	private boolean popularity;
	private boolean age;
	
	
	public VMCriteria(boolean initialLicenceCosts, boolean hourlyLicenceCosts, boolean popularity, boolean age)
	{
		this.initialLicenceCosts = initialLicenceCosts;
		this.hourlyLicenceCosts = hourlyLicenceCosts;
		this.popularity = popularity;
		this.age = age;
	}
	
}
