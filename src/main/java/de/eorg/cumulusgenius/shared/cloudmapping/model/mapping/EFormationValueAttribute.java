/**
 * 
 */
package de.eorg.cumulusgenius.shared.cloudmapping.model.mapping;

/**
 * @author mugglmenzel
 *
 */
public enum EFormationValueAttribute implements IEAttribute {

	NETWORK_COST_RECIEVE("network_cost_recieve"), NETWORK_COST_SEND("network_cost_send"), VALUE("value");
	
	/**
	 * @uml.property  name="name"
	 */
	private String name;
	
	private EFormationValueAttribute(String name) {
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
