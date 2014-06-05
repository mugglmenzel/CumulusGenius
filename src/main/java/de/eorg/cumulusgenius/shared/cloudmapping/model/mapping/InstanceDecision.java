/**
 * 
 */
package de.eorg.cumulusgenius.shared.cloudmapping.model.mapping;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import de.eorg.cumulusgenius.shared.cloudmapping.model.ahp.configuration.Alternative;
import de.eorg.cumulusgenius.shared.cloudmapping.model.ahp.configuration.Decision;
import de.eorg.cumulusgenius.shared.cloudmapping.model.mapping.requirements.Requirement;


/**
 * @author mugglmenzel
 * 
 */
public class InstanceDecision extends Decision<InstanceAlternative> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7722924479698464974L;

	// private ComputeService computeService;

	/**
	 * @uml.property  name="fctRequirements"
	 * @uml.associationEnd  multiplicity="(0 -1)" elementType="de.eorg.cumulusgenius.shared.cloudmapping.model.mapping.requirements.MinRequirement"
	 */
	private Set<Requirement<?>> fctRequirements = new HashSet<Requirement<?>>();

	/**
	 * @uml.property  name="validMapping"
	 */
	public boolean validMapping = false;

	public InstanceDecision() {
		super();
	}

	public Set<Requirement<?>> getFctRequirements() {
		return fctRequirements;
	}

	public void setFctRequirements(Set<Requirement<?>> fctRequirements) {
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
			for (InstanceAlternative a : getAlternatives()) {
				System.out.print("checking " + a + "/"
						+ a.getInstance().getComputeService() + " for "
						+ r.getAttributeName());
				if (a.getInstance().getComputeService()
						.getAttribute(r.getAttributeName()) != null)
					System.out.println(", result: "
							+ r.checkValue(a.getInstance().getComputeService()
									.getAttribute(r.getAttributeName())
									.getValue()));
			}
	}
}
