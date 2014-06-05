/**
 * 
 */
package de.eorg.cumulusgenius.experiments;

import com.google.gwt.user.client.ui.VerticalPanel;
import com.smartgwt.client.widgets.Slider;

/**
 * @author mugglmenzel
 *
 */
public class AlexExperiments {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		VerticalPanel vp = new VerticalPanel();
	    vp.setSpacing(10);
	 
	    final Slider s = new Slider();
	    vp.add(s);
	    
	    final Slider vs = new Slider();
	    vp.add(vs);
	}

}
