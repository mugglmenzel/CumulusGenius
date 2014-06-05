/**
 * 
 */
package de.eorg.cumulusgenius.shared.cloudmapping.model.mapping;

import de.eorg.cumulusgenius.shared.cloudmapping.model.ahp.configuration.Alternative;

/**
 * @author mugglmenzel
 *
 */
public class InstanceAlternative extends Alternative {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2480586708835376560L;
	
	/**
	 * @uml.property  name="instance"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private Instance instance;
	
	public InstanceAlternative(Instance instance, String name) {
		setInstance(instance);
		setName(name);
	}

	/**
	 * @return
	 * @uml.property  name="instance"
	 */
	public Instance getInstance() {
		return this.instance;
	}
	
	/**
	 * @param instance  the instance to set
	 * @uml.property  name="instance"
	 */
	public void setInstance(Instance instance) {
		this.instance = instance;
	}

	@Override
	public Alternative clone() {
		InstanceAlternative alt = new InstanceAlternative(getInstance(), getName());
		alt.setDescription(getDescription());
		return alt;
	}

}
