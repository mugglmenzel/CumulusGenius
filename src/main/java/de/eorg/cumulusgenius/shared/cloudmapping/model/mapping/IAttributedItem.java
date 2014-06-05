/**
 * 
 */
package de.eorg.cumulusgenius.shared.cloudmapping.model.mapping;

import java.io.Serializable;
import java.util.List;

/**
 * @author mugglmenzel
 *
 */
public interface IAttributedItem extends Cloneable, Serializable, Comparable<IAttributedItem> {

	public Attribute<?> getAttribute(String attributeName);
	
	public java.util.Map<String, Attribute<?>> getAttributes();
	
}
