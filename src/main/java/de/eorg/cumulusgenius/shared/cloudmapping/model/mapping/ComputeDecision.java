/**
 * 
 */
package de.eorg.cumulusgenius.shared.cloudmapping.model.mapping;

import java.util.ArrayList;
import java.util.List;

import de.eorg.cumulusgenius.shared.cloudmapping.model.ahp.configuration.Alternative;
import de.eorg.cumulusgenius.shared.cloudmapping.model.ahp.configuration.Decision;
import de.eorg.cumulusgenius.shared.cloudmapping.model.mapping.requirements.Requirement;

/**
 * @author mugglmenzel
 * 
 */
public class ComputeDecision extends Decision<ComputeServiceAlternative> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7722924479698464974L;

	// private ComputeService computeService;

	/**
	 * @uml.property  name="fctRequirements"
	 */
	private List<Requirement<?>> fctRequirements = new ArrayList<Requirement<?>>();

	/**
	 * @uml.property  name="validMapping"
	 */
	public boolean validMapping = false;

	public ComputeDecision() {
		super();
	}

	public List<Requirement<?>> getFctRequirements() {
		return fctRequirements;
	}

	public void setFctRequirements(List<Requirement<?>> fctRequirements) {
		this.fctRequirements = fctRequirements;
	}


	/**
	 * @return
	 * @uml.property  name="validMapping"
	 */
	public boolean isValidMapping() {
		return validMapping;
	}

	/*
	 * Die Methode reqCheck mapped die Attribute auf die Requirements und prüft
	 * diese so auf Erfüllung.
	 */
	// TODO: später als universelle Klasse/Methode einarbeiten, da
	// ApplianceDecision ebenfalls Att. auf Requ. mapped
	public void reqCheck() {

		for (Requirement r : fctRequirements)
			for (ComputeServiceAlternative a : getAlternatives()) {
				System.out.println("checking "
						+ a
						+ ", result: "
						+ r.checkValue(a.getComputeService()
								.getAttribute(r.getAttributeName()).getValue()));
			}
	}
}
