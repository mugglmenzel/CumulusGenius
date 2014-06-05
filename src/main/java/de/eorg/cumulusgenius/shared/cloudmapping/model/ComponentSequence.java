/**
 * 
 */
package de.eorg.cumulusgenius.shared.cloudmapping.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import de.eorg.cumulusgenius.shared.cloudmapping.model.mapping.Component;

/**
 * @author menzel
 * 
 */
public class ComponentSequence {

	/**
	 * @uml.property  name="sequence"
	 * @uml.associationEnd  multiplicity="(0 -1)" elementType="de.eorg.cumulusgenius.shared.cloudmapping.model.mapping.Component"
	 */
	private List<Component> sequence;

	/**
	 * @uml.property  name="value"
	 */
	private Double value = new Random().nextDouble();

	/**
	 * @param sequence
	 */
	public ComponentSequence(List<Component> sequence) {
		super();
		this.sequence = sequence;
	}

	/**
	 * @return the sequence
	 */
	public List<Component> getSequence() {
		return sequence;
	}

	/**
	 * @return
	 * @uml.property  name="value"
	 */
	public Double getValue() {
		return this.value;
	}

	public boolean isValid() {
		for (int i = 0; i < getSequence().size(); i++)
			if (getSequence().get(i).getConnectedComponents().size() > 0) {
				List<Component> previous = new ArrayList<Component>(getSequence().subList(0, i));
				previous
						.retainAll(getSequence().get(i).getConnectedComponents());
				if (previous.size() != getSequence().get(i)
						.getConnectedComponents().size())
					return false;
			}

		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getSequence().toString();
	}

}
