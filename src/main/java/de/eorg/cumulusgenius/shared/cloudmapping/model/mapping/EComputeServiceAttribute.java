/**
 * 
 */
package de.eorg.cumulusgenius.shared.cloudmapping.model.mapping;

/**
 * @author mugglmenzel
 *
 */
public enum EComputeServiceAttribute implements IEAttribute {

	COSTPERHOUR("cost.perhour"), CPUBENCHMARK("benchmark.cpu"), RAMBENCHMARK("benchmark.ram"), DISKBENCHMARK("benchmark.disk"), PROVDERPOPULARITY("popularity.provider"), SERVICEPOPULARITY("poppularity.service"), UPTIME("uptime"), MAXLATENCY("network.latency.max"), AVGLATENCY("network.latency.avg");
	
	/**
	 * @uml.property  name="name"
	 */
	private String name;
	
	private EComputeServiceAttribute(String name) {
		this.setName(name);
	}

	/* (non-Javadoc)
	 * @see org.collaboration.cloudmapping.model.mapping.IEAttribute#setName(java.lang.String)
	 */
	/**
	 * @param name
	 * @uml.property  name="name"
	 */
	@Override
	public void setName(String name) {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see org.collaboration.cloudmapping.model.mapping.IEAttribute#getName()
	 */
	/**
	 * @return
	 * @uml.property  name="name"
	 */
	@Override
	public String getName() {
		return name;
	}
}
