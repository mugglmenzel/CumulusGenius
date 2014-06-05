package de.eorg.cumulusgenius.shared.cloudmapping.model.mapping;


public class CSRequirement {

	private String name;
	private String constraint;
	private String value;
	private String metric;
	
	
	public CSRequirement(String name, String constraint, String value, String metric)
	{
		this.name = name;
		this.constraint = constraint;
		this.value = value;
		this.metric = metric;
	}
		
	public String getName()
	{
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return getName();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof CSRequirement)) {
			return false;
		}
		CSRequirement other = (CSRequirement) obj;
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
