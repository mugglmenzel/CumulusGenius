/**
 * 
 */
package de.eorg.cumulusgenius.shared.cloudmapping.model.mapping;

import de.eorg.cumulusgenius.shared.cloudmapping.model.ahp.configuration.Alternative;

/**
 * @author mugglmenzel
 *
 */
public class CombinationAlternative extends Alternative {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1495788070444434492L;
	
	/**
	 * @uml.property  name="combination"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private CombinationValue combination;

	/**
	 * @param combination
	 */
	public CombinationAlternative(String name, CombinationValue combination) {
		super();
		setName(name);
		this.combination = combination;
	}

	/**
	 * @return  the combination
	 * @uml.property  name="combination"
	 */
	public CombinationValue getCombination() {
		return combination;
	}

	/**
	 * @param combination  the combination to set
	 * @uml.property  name="combination"
	 */
	public void setCombination(CombinationValue combination) {
		this.combination = combination;
	}

	@Override
	public Alternative clone() {
		CombinationAlternative alt = new CombinationAlternative(getName(), getCombination());
		alt.setDescription(getDescription());
		return alt;
	}
	
	
	
}
