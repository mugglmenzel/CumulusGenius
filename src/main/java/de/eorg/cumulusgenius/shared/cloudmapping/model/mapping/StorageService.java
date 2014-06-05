package de.eorg.cumulusgenius.shared.cloudmapping.model.mapping;

public class StorageService extends AbstractAttributedItem {

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
	public StorageService(String name) {
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

}
