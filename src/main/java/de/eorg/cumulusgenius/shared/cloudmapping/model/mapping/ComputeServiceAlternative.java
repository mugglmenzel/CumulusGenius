/**
 * 
 */
package de.eorg.cumulusgenius.shared.cloudmapping.model.mapping;

import de.eorg.cumulusgenius.shared.cloudmapping.model.ahp.configuration.Alternative;

/**
 * @author mugglmenzel
 * 
 */
public class ComputeServiceAlternative extends Alternative {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6431007114719302435L;

	/**
	 * @uml.property name="computeService"
	 * @uml.associationEnd multiplicity="(1 1)"
	 */
	private ComputeService computeService;

	public ComputeServiceAlternative(ComputeService cs, String name) {
		setComputeService(cs);
		setName(name);
	}

	/**
	 * @return the computeService
	 * @uml.property name="computeService"
	 */
	public ComputeService getComputeService() {
		return computeService;
	}

	/**
	 * @param computeService
	 *            the computeService to set
	 * @uml.property name="computeService"
	 */
	public void setComputeService(ComputeService computeService) {
		this.computeService = computeService;
	}

	@Override
	public Alternative clone() {
		ComputeServiceAlternative alt = new ComputeServiceAlternative(
				getComputeService(), getName());
		alt.setDescription(getDescription());
		return alt;
	}

}
