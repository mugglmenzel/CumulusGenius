package de.eorg.cumulusgenius.shared.cloudmapping.model.mapping.requirements;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import de.eorg.cumulusgenius.shared.cloudmapping.model.mapping.IEAttribute;

public abstract class Requirement<T> implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4265980830540891007L;

	/**
	 * @uml.property  name="name"
	 */
	private String name;
	
	/**
	 * @uml.property  name="reqType"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private RequirementType reqType;
	
	/**
	 * @uml.property  name="attributeName"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private IEAttribute attributeName;
	
	/**
	 * @uml.property  name="value"
	 * @uml.associationEnd  
	 */
	private IRequirementItem<T> value;
	
	/**
	 * @uml.property  name="values"
	 */
	private List<IRequirementItem<T>> values;
	
	
	public Requirement(String name, RequirementType reqType, IEAttribute attribute, IRequirementItem<T> value) {
		super();
		this.name = name;
		this.reqType = reqType;
		this.attributeName = attribute;
		this.value = value;
	}
	
	public Requirement(String name, RequirementType reqType, IEAttribute attribute, Collection<IRequirementItem<T>> values) {
		super();
		this.name = name;
		this.reqType = reqType;
		this.attributeName = attribute;
		this.setValues(values);
	}
	
	
	public IRequirementItem<T> getValue() {
		return value;
	}


	public void setValue(IRequirementItem<T> value) {
		this.value = value;
	}


	/**
	 * @return
	 * @uml.property  name="name"
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name
	 * @uml.property  name="name"
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return
	 * @uml.property  name="reqType"
	 */
	public RequirementType getReqType() {
		return reqType;
	}
	/**
	 * @param reqType
	 * @uml.property  name="reqType"
	 */
	public void setReqType(RequirementType reqType) {
		this.reqType = reqType;
	}
	
	/**
	 * @return  the attribute
	 * @uml.property  name="attributeName"
	 */
	public IEAttribute getAttributeName() {
		return attributeName;
	}


	/**
	 * @param attribute  the attribute to set
	 * @uml.property  name="attributeName"
	 */
	public void setAttributeName(IEAttribute attribute) {
		this.attributeName = attribute;
	}


	/**
	 * @return the values
	 */
	public List<IRequirementItem<T>> getValues() {
		return values;
	}

	/**
	 * @param values the values to set
	 */
	public void setValues(Collection<IRequirementItem<T>> values) {
		this.values = new ArrayList<IRequirementItem<T>>(values);
	}

	public abstract boolean checkValue(T item);
	
}
