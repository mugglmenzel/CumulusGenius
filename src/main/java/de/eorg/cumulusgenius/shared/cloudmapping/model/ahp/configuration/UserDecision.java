package de.eorg.cumulusgenius.shared.cloudmapping.model.ahp.configuration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import de.eorg.cumulusgenius.shared.cloudmapping.model.mapping.Component;

public class UserDecision implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2622278348846849036L;
	
	String name;
	String userId;
	String description;
	List<Component>  component = new ArrayList<Component>();
	
	public UserDecision()
	{
		super();
	}
	
	public UserDecision(String name, String userId, String description)
	{
		this.description = description;
		this.name = name;
		this.userId = userId;
	}
	
	public void addComponent(Component component)
	{
		this.component.add(component);
	}
	
	
	
	public String getName()
	{
		return this.name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	
}
