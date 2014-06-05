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
public class FormationValue implements Comparable<FormationValue> {

	private List<ComponentSolution> componentSolutions = new ArrayList<ComponentSolution>();

	private double formationValue = 0D;

	/**
	 * @param componentSolutions
	 * @param formationValue
	 */
	public FormationValue(List<ComponentSolution> componentSolutions,
			double formationValue) {
		super();
		this.componentSolutions = componentSolutions;
		this.formationValue = formationValue;
	}

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

	@Override
	public int compareTo(FormationValue o) {
		return o == null ? (this == null ? 0 : 1) : equals(o) ? 0 : new Double(
				getFormationValue())
				.compareTo(new Double(o.getFormationValue()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object arg0) {
		boolean result = true;

		if (arg0 instanceof FormationValue
				&& getComponentSolutions().size() == ((FormationValue) arg0)
						.getComponentSolutions().size())
			for (ComponentSolution cs : getComponentSolutions())
				result &= ((FormationValue) arg0).getComponentSolutions()
						.contains(cs);
		else
			result = false;

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FormationValue [componentSolutions=" + componentSolutions
				+ ", formationValue=" + formationValue + "]";
	}

}
