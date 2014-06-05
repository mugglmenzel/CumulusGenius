/**
 *
 */
package de.eorg.cumulusgenius.shared.cloudmapping.model.mapping;

import java.util.HashMap;
import java.util.Map;

/**
 * @author mugglmenzel
 */
public abstract class AbstractAttributedItem implements IAttributedItem {

    /**
     *
     */
    private static final long serialVersionUID = 2650872196579507357L;

    /**
     * @uml.property name="attributes"
     * @uml.associationEnd multiplicity="(0 -1)" elementType="de.eorg.cumulusgenius.shared.cloudmapping.model.mapping.Attribute"
     */
    private Map<String, Attribute<?>> attributes = new HashMap<String, Attribute<?>>();

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(IAttributedItem o) {
        return ((o.getAttributes() != null && o.getAttributes() != null) ? getAttributes()
                .size() - o.getAttributes().size()
                : (getAttributes() != null ? 1 : -1));
    }

    public void addAttribute(Attribute<?> attribute) {
        getAttributes().put(attribute.getName().getName(), attribute);
    }

    public Attribute<?> getAttribute(IEAttribute attribute) {
        return getAttribute(attribute.getName());
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.collaboration.cloudmapping.model.mapping.IAttributedItem#getAttribute
     * (org.collaboration.cloudmapping.model.mapping.EAttribute)
     */
    @Override
    public Attribute<?> getAttribute(String attributeName) {
        return getAttributes().get(attributeName);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.collaboration.cloudmapping.model.mapping.IAttributedItem#getAttributes
     * ()
     */
    @Override
    public Map<String, Attribute<?>> getAttributes() {
        return attributes;
    }

}
