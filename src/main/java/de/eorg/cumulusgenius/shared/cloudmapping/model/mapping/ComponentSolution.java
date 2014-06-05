/**
 * 
 */
package de.eorg.cumulusgenius.shared.cloudmapping.model.mapping;

/**
 * @author mugglmenzel
 * 
 */
public class ComponentSolution implements Comparable<ComponentSolution> {

	private Component component;

	private CombinationTotalValue combinationTotalValue;

	/**
	 * @param component
	 * @param totalValue
	 */
	public ComponentSolution(Component component,
			CombinationTotalValue totalValue) {
		super();
		this.component = component;
		this.combinationTotalValue = totalValue;
	}

	/**
	 * @return the component
	 */
	public Component getComponent() {
		return component;
	}

	/**
	 * @param component
	 *            the component to set
	 */
	public void setComponent(Component component) {
		this.component = component;
	}

	/**
	 * @return the totalValue
	 */
	public CombinationTotalValue getCombinationTotalValue() {
		return combinationTotalValue;
	}

	/**
	 * @param totalValue
	 *            the totalValue to set
	 */
	public void setCombinationTotalValue(CombinationTotalValue totalValue) {
		this.combinationTotalValue = totalValue;
	}

	@Override
	public int compareTo(ComponentSolution o) {
		return o == null ? (this == null ? 0 : 1) : equals(o) ? 0
				: getCombinationTotalValue().compareTo(
						o.getCombinationTotalValue());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ComponentSolution [component=" + component
				+ ", combinationTotalValue=" + combinationTotalValue + "]";
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
				+ ((combinationTotalValue == null) ? 0 : combinationTotalValue
						.hashCode());
		result = prime * result
				+ ((component == null) ? 0 : component.hashCode());
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
		if (!(obj instanceof ComponentSolution)) {
			return false;
		}
		ComponentSolution other = (ComponentSolution) obj;
		if (combinationTotalValue == null) {
			if (other.combinationTotalValue != null) {
				return false;
			}
		} else if (!combinationTotalValue.equals(other.combinationTotalValue)) {
			return false;
		}
		if (component == null) {
			if (other.component != null) {
				return false;
			}
		} else if (!component.equals(other.component)) {
			return false;
		}
		return true;
	}

}
