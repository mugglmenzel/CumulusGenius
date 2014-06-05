/**
 * 
 */
package de.eorg.cumulusgenius.shared.cloudmapping.model.mapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mugglmenzel
 * 
 */
public class FormationSolution extends AbstractAttributedItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5447824309731597238L;

	private List<ComponentSolution> componentSolutions = new ArrayList<ComponentSolution>();

	private double formationValue = 0D;

	/**
	 * @return the componentSolutionMap
	 */
	public List<ComponentSolution> getComponentSolutions() {
		return componentSolutions;
	}

	/**
	 * @param componentSolutionMap
	 *            the componentSolutionMap to set
	 */
	public void setComponentSolutions(List<ComponentSolution> componentSolutions) {
		this.componentSolutions = componentSolutions;
	}

	/**
	 * @return the formationValue
	 */
	public double getFormationValue() {
		return formationValue;
	}

	/**
	 * @param formationValue
	 *            the formationValue to set
	 */
	public void setFormationValue(double formationValue) {
		this.formationValue = formationValue;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FormationSolution [componentSolutions=" + componentSolutions
				+ ", formationValue=" + formationValue + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((componentSolutions == null) ? 0 : componentSolutions
						.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof FormationSolution)) {
			return false;
		}
		FormationSolution other = (FormationSolution) obj;
		if (componentSolutions == null) {
			if (other.componentSolutions != null) {
				return false;
			}
		} else if (new ArrayList<ComponentSolution>(getComponentSolutions())
				.retainAll(other.getComponentSolutions())) {
			return false;
		}
		return true;
	}
}
