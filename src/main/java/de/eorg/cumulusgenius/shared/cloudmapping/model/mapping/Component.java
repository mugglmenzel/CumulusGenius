package de.eorg.cumulusgenius.shared.cloudmapping.model.mapping;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Component implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7424511072280849957L;
	/**
	 * @uml.property name="name"
	 * @uml.associationEnd multiplicity="(0 -1)" elementType=
	 *                     "de.eorg.cumulusgenius.shared.cloudmapping.model.mapping.Component"
	 */
	private String name;
	private String software;
	private String interconnection;
	


	/**
	 * @uml.property name="connectedComponents"
	 */
	private Set<Component> connectedComponents;

	// public Set<Attribute> attributes;

	public Component()
	{
		
	}
	
	public Component(String name) {
		this(name, new HashSet<Component>());
	}

	/**
	 * @param name
	 */
	public Component(String name, Set<Component> connected) {
		super();
		this.name = name;
		this.setConnectedComponents(connected);
	}
	
	public Component(String name, String software, String interconnection)
	{
		super();
		this.name = name;
		this.setSoftware(software);
		this.setInterconnection(interconnection);
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
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getName();
	}

	/**
	 * @param requiredComponents
	 *            the requiredComponents to set
	 */
	public void setConnectedComponents(Set<Component> requiredComponents) {
		this.connectedComponents = requiredComponents;
	}

	/**
	 * @return the requiredComponents
	 */
	public Set<Component> getConnectedComponents() {
		return connectedComponents != null ? connectedComponents
				: new HashSet<Component>();
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
		if (!(obj instanceof Component)) {
			return false;
		}
		Component other = (Component) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}

	public String getSoftware() {
		return software;
	}

	public void setSoftware(String software) {
		this.software = software;
	}

	public String getInterconnection() {
		return interconnection;
	}

	public void setInterconnection(String interconnection) {
		this.interconnection = interconnection;
	}

}
