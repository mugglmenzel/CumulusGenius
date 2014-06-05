/**
 * 
 */
package de.eorg.cumulusgenius.shared.cloudmapping.model.mapping;


/**
 * @author mugglmenzel
 * 
 */
public class Provider extends AbstractAttributedItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6764200102732091381L;

	
	private Long id;
	/**
	 * @uml.property name="name"
	 */
	private String name;

	/**
	 * @param name
	 */
	public Provider(String name) {
		super();
		this.name = name;
	}
	
	public Provider(String name, Long id) {
		super();
		this.setId(id);
		this.name = name;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	/**
	 * @return the name
	 * @uml.property name="name"
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 * @uml.property name="name"
	 */
	public void setName(String name) {
		this.name = name;
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
		if (!(obj instanceof Provider)) {
			return false;
		}
		Provider other = (Provider) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}

}
