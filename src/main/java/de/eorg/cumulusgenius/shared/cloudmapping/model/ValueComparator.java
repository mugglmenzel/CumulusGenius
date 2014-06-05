package de.eorg.cumulusgenius.shared.cloudmapping.model;

import java.util.Comparator;
import java.util.Map;

public class ValueComparator implements Comparator {

	  /**
	 * @uml.property  name="base"
	 * @uml.associationEnd  qualifier="b:java.lang.Object java.lang.Double"
	 */
	Map base;
	  public ValueComparator(Map base) {
	      this.base = base;
	  }

	  @Override
	public int compare(Object a, Object b) {

	    if((Double)base.get(a) < (Double)base.get(b)) {
	      return 1;
	    } else if((Double)base.get(a) == (Double)base.get(b)) {
	      return 0;
	    } else {
	      return -1;
	    }
	  }
	}

