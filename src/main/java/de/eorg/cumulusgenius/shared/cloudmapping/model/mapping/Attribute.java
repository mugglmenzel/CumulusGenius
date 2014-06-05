/**
 * 
 */
package de.eorg.cumulusgenius.shared.cloudmapping.model.mapping;

import java.io.Serializable;

/**
 * @author mugglmenzel
 * 
 */
public class Attribute<T> implements Serializable, Cloneable,
		Comparable<Attribute<T>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1423409082864357874L;

	/**
	 * @uml.property  name="name"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private IEAttribute name;

	/**
	 * @uml.property  name="value"
	 */
	private Comparable<T> value;

	/**
	 * @param name
	 * @param value
	 */
	public Attribute(IEAttribute name, Comparable<T> value) {
		super();
		this.name = name;
		this.value = value;
	}

	/**
	 * @return
	 * @uml.property  name="name"
	 */
	public IEAttribute getName() {
		return name;
	}

	/**
	 * @param name
	 * @uml.property  name="name"
	 */
	public void setName(IEAttribute name) {
		this.name = name;
	}

	public Comparable<T> getValue() {
		return value;
	}

	public void setValue(Comparable<T> value) {
		this.value = value;
	}

	@Override
	public int compareTo(Attribute<T> o) {
		return getValue().equals(o.getValue()) ? 0 : getValue().toString()
				.compareTo(o.getValue().toString());
	}

    @Override
    public String toString() {
        return "Attribute{" +
                "name=" + name +
                ", value=" + value +
                '}';
    }
}
