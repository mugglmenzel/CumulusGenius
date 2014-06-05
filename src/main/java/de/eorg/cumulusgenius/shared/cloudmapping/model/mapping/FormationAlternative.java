/**
 * 
 */
package de.eorg.cumulusgenius.shared.cloudmapping.model.mapping;

import de.eorg.cumulusgenius.shared.cloudmapping.model.ahp.configuration.Alternative;

/**
 * @author mugglmenzel
 * 
 */
public class FormationAlternative extends Alternative {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1495788070444434492L;

	/**
	 * @uml.property name="combination"
	 * @uml.associationEnd multiplicity="(1 1)"
	 */
	private FormationSolution formation;

	/**
	 * @param combination
	 */
	public FormationAlternative(String name, FormationSolution formation) {
		super();
		setName(name);
		this.formation = formation;
	}

	/**
	 * @return the formation
	 */
	public FormationSolution getFormation() {
		return formation;
	}

	/**
	 * @param formation
	 *            the formation to set
	 */
	public void setFormation(FormationSolution formation) {
		this.formation = formation;
	}

	@Override
	public Alternative clone() {
		FormationAlternative alt = new FormationAlternative(getName(),
				getFormation());
		alt.setDescription(getDescription());
		return alt;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((formation == null) ? 0 : formation.hashCode());
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
		if (!(obj instanceof FormationAlternative)) {
			return false;
		}
		FormationAlternative other = (FormationAlternative) obj;
		if (formation == null) {
			if (other.formation != null) {
				return false;
			}
		} else if (!formation.equals(other.formation)) {
			return false;
		}
		return true;
	}

}
