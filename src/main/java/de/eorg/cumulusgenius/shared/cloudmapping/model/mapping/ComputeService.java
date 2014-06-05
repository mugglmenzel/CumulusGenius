package de.eorg.cumulusgenius.shared.cloudmapping.model.mapping;

public abstract class ComputeService extends AbstractAttributedItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5770949839975306149L;

	/**
	 * @uml.property name="name"
	 */
	private String name;

	/**
	 * @uml.property name="provider"
	 * @uml.associationEnd
	 */
	private Provider provider;

	/**
	 * @param name
	 */
	public ComputeService(String name) {
		super();
		this.name = name;
	}

	/**
	 * @param name
	 *            the name to set
	 * @uml.property name="name"
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the name
	 * @uml.property name="name"
	 */
	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return getName();
	}

	/**
	 * @return the provider
	 * @uml.property name="provider"
	 */
	public Provider getProvider() {
		return provider;
	}

	/**
	 * @param provider
	 *            the provider to set
	 * @uml.property name="provider"
	 */
	public void setProvider(Provider provider) {
		this.provider = provider;
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
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((provider == null) ? 0 : provider.hashCode());
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
		if (!(obj instanceof ComputeService)) {
			return false;
		}
		ComputeService other = (ComputeService) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (provider == null) {
			if (other.provider != null) {
				return false;
			}
		} else if (!provider.equals(other.provider)) {
			return false;
		}
		return true;
	}

}
