package de.eorg.cumulusgenius.shared.cloudmapping.model.mapping;

/**
 * Created by mugglmenzel on 26/04/14.
 */
public class VariableAttributeName implements IEAttribute {

    String name;

    public VariableAttributeName(String name) {
        this.name = name;
    }

    @Override
    public void setName(String name) {
       this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VariableAttributeName)) return false;

        VariableAttributeName that = (VariableAttributeName) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "VariableAttributeName{" +
                "name='" + name + '\'' +
                '}';
    }
}
