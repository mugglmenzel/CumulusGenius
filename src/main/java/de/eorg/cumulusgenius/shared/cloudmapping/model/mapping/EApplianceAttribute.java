/**
 * 
 */
package de.eorg.cumulusgenius.shared.cloudmapping.model.mapping;

/**
 * @author mugglmenzel
 *
 */
public enum EApplianceAttribute implements IEAttribute {

	COSTPERHOUR("costperhour"), POPULARITY("popularity");
	
	/**
	 * @uml.property  name="name"
	 */
	private String name;
	
	private EApplianceAttribute(String name) {
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
