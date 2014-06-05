/**
 * 
 */
package de.eorg.cumulusgenius.shared.cloudmapping.model.mapping;

/**
 * @author mugglmenzel
 * 
 */
public class CombinationValue extends AbstractAttributedItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5416572223175191060L;

	/**
	 * @uml.property name="appliance"
	 * @uml.associationEnd multiplicity="(1 1)"
	 */
	private Appliance appliance;

	/**
	 * @uml.property name="service"
	 * @uml.associationEnd multiplicity="(1 1)"
	 */
	private ComputeService service;

	/**
	 * @uml.property name="applianceValue"
	 */
	private double applianceValue = 0D;

	/**
	 * @uml.property name="serviceValue"
	 */
	private double serviceValue = 0D;

	public CombinationValue(Appliance appliance, ComputeService service,
			double applianceValue, double serviceValue) {
		super();
		this.appliance = appliance;
		this.service = service;
		this.applianceValue = applianceValue;
		this.serviceValue = serviceValue;
	}

	/**
	 * @return the appliance
	 * @uml.property name="appliance"
	 */
	public Appliance getAppliance() {
		return appliance;
	}

	/**
	 * @param appliance
	 *            the appliance to set
	 * @uml.property name="appliance"
	 */
	public void setAppliance(Appliance appliance) {
		this.appliance = appliance;
	}

	/**
	 * @return the service
	 * @uml.property name="service"
	 */
	public ComputeService getService() {
		return service;
	}

	/**
	 * @param service
	 *            the service to set
	 * @uml.property name="service"
	 */
	public void setService(ComputeService service) {
		this.service = service;
	}

	/**
	 * @return the applianceValue
	 * @uml.property name="applianceValue"
	 */
	public double getApplianceValue() {
		return applianceValue;
	}

	/**
	 * @param applianceValue
	 *            the applianceValue to set
	 * @uml.property name="applianceValue"
	 */
	public void setApplianceValue(double applianceValue) {
		this.applianceValue = applianceValue;
	}

	/**
	 * @return the serviceValue
	 * @uml.property name="serviceValue"
	 */
	public double getServiceValue() {
		return serviceValue;
	}

	/**
	 * @param serviceValue
	 *            the serviceValue to set
	 * @uml.property name="serviceValue"
	 */
	public void setServiceValue(double serviceValue) {
		this.serviceValue = serviceValue;
	}

	public Double getOverallValue() {
		return 0.5 * getApplianceValue() + 0.5 * getServiceValue();
	}

	@Override
	public int compareTo(IAttributedItem o) {
		return o == null ? (this == null ? 0 : 1)
				: ((o instanceof CombinationValue) ? getOverallValue()
						.compareTo(((CombinationValue) o).getOverallValue())
						: super.compareTo(o));
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
				+ ((appliance == null) ? 0 : appliance.hashCode());
		result = prime * result + ((service == null) ? 0 : service.hashCode());
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
		if (!(obj instanceof CombinationValue)) {
			return false;
		}
		CombinationValue other = (CombinationValue) obj;
		if (appliance == null) {
			if (other.appliance != null) {
				return false;
			}
		} else if (!appliance.equals(other.appliance)) {
			return false;
		}
		if (service == null) {
			if (other.service != null) {
				return false;
			}
		} else if (!service.equals(other.service)) {
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
		return "[(" + getAppliance().getName() + ", " + getService().getName()
				+ "), " + getOverallValue() + "]";
	}

}
