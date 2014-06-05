/**
 * 
 */
package de.eorg.cumulusgenius.shared.cloudmapping.model.mapping;

/**
 * @author mugglmenzel
 *
 */
public enum EProviderAttribute implements IEAttribute {

	NETWORK_COST_RECIEVE("network_cost_recieve"), NETWORK_COST_SEND("network_cost_send"), INTERNET_COST_RECIEVE("internet_cost_recieve"), INTERNET_COST_SEND("internet_cost_send");
	
	/**
	 * @uml.property  name="name"
	 */
	private String name;
	
	private EProviderAttribute(String name) {
		this.setName(name);
	}

	/**
	 * @param name  the name to set
	 * @uml.property  name="name"
	 */
	@Override
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return  the name
	 * @uml.property  name="name"
	 */
	@Override
	public String getName() {
		return name;
	}
}
