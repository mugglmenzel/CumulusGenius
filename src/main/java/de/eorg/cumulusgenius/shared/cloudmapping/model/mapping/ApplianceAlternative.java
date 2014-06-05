/**
 * 
 */
package de.eorg.cumulusgenius.shared.cloudmapping.model.mapping;

import de.eorg.cumulusgenius.shared.cloudmapping.model.ahp.configuration.Alternative;

/**
 * @author mugglmenzel
 *
 */
public class ApplianceAlternative extends Alternative {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4366388083750956404L;
	/**
	 * @uml.property  name="appl"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private Appliance appl;
	
	public ApplianceAlternative(Appliance appl, String name) {
		this.setAppl(appl);
		setName(name);
	}

	/**
	 * @param appl  the appl to set
	 * @uml.property  name="appl"
	 */
	public void setAppl(Appliance appl) {
		this.appl = appl;
	}

	/**
	 * @return  the appl
	 * @uml.property  name="appl"
	 */
	public Appliance getAppl() {
		return appl;
	}

	@Override
	public Alternative clone() {
		ApplianceAlternative alt = new ApplianceAlternative(getAppl(), getName());
		alt.setDescription(getDescription());
		return alt;
	}
}
