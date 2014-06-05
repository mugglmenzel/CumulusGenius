/**
 * 
 */
package de.eorg.cumulusgenius.shared.cloudmapping.model.mapping;

/**
 * @author mugglmenzel
 * 
 */
public enum EStorageServiceAttribute implements IEAttribute {

	STORAGECOST("cost.storage"), NETWORKDOWNLOADCOST("cost.network.download"), NETWORKUPLOADCOST(
			"cost.network.upload"), NETWORKHOPS("network.hops"), NETWORKLATENCY(
			"network.latency"), NETWORKBANDWIDTH("network.bandwidth");

	/**
	 * @uml.property name="name"
	 */
	private String name;

	private EStorageServiceAttribute(String name) {
		this.setName(name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.collaboration.cloudmapping.model.mapping.IEAttribute#setName(java
	 * .lang.String)
	 */
	/**
	 * @param name
	 * @uml.property name="name"
	 */
	@Override
	public void setName(String name) {
		this.name = name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.collaboration.cloudmapping.model.mapping.IEAttribute#getName()
	 */
	/**
	 * @return
	 * @uml.property name="name"
	 */
	@Override
	public String getName() {
		return name;
	}
}
