/**
 * 
 */
package de.eorg.cumulusgenius.shared.cloudmapping.model.mapping;

/**
 * @author mugglmenzel
 * 
 */
public class CombinationTotalValue extends CombinationValue {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5416572223175191060L;

	/**
	 * @uml.property name="totalValue"
	 */
	private double totalValue = 0D;

	public CombinationTotalValue(Appliance appliance, ComputeService service,
			double applianceValue, double serviceValue, double totalValue) {
		super(appliance, service, applianceValue, serviceValue);
		this.setTotalValue(totalValue);
	}

	public CombinationTotalValue(CombinationValue cv, double totalValue) {
		super(cv.getAppliance(), cv.getService(), cv.getApplianceValue(), cv
				.getServiceValue());
		this.setTotalValue(totalValue);
	}

	/**
	 * @return the totalValue
	 * @uml.property name="totalValue"
	 */
	public double getTotalValue() {
		return totalValue;
	}

	/**
	 * @param totalValue
	 *            the totalValue to set
	 * @uml.property name="totalValue"
	 */
	public void setTotalValue(double totalValue) {
		this.totalValue = totalValue;
	}

	@Override
	public int compareTo(IAttributedItem o) {
		return o == null ? (this == null ? 0 : 1)
				: ((o instanceof CombinationTotalValue) ? new Double(
						getTotalValue()).compareTo(new Double(
						((CombinationTotalValue) o).getTotalValue())) : super
						.compareTo(o));
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
		result = prime * result
				+ ((getAppliance() == null) ? 0 : getAppliance().hashCode());
		result = prime * result
				+ ((getService() == null) ? 0 : getService().hashCode());
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
		if (!(obj instanceof CombinationTotalValue)) {
			return false;
		}
		CombinationTotalValue other = (CombinationTotalValue) obj;
		if (getAppliance() == null) {
			if (other.getAppliance() != null) {
				return false;
			}
		} else if (!getAppliance().equals(other.getAppliance())) {
			return false;
		}
		if (getService() == null) {
			if (other.getService() != null) {
				return false;
			}
		} else if (!getService().equals(other.getService())) {
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
		return "[(" + getAppliance().getName() + "=" + getApplianceValue()
				+ ", " + getService().getName() + "=" + getServiceValue()
				+ "), " + getTotalValue() + "]";
	}

}
