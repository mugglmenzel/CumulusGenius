/**
 * 
 */
package de.eorg.cumulusgenius.shared.cloudmapping.model.mapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author mugglmenzel
 * 
 */
public abstract class Appliance extends AbstractAttributedItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5718934871710445135L;

	/**
	 * @uml.property name="name"
	 */
	private String name;

    public Appliance() {
    }

    public Appliance(String name) {
		super();
		this.name = name;
	}

	public Appliance(String name, Map<String, Attribute<?>> attributes) {
		super();
		this.name = name;
        super.getAttributes().putAll(attributes);
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
	public int compareTo(IAttributedItem o) {
		return ((o.getAttributes() != null && o.getAttributes() != null) ? getAttributes()
				.size() - o.getAttributes().size()
				: (getAttributes() != null ? 1 : -1));
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
		if (!(obj instanceof Appliance)) {
			return false;
		}
		Appliance other = (Appliance) obj;
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
