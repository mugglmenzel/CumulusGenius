package de.eorg.cumulusgenius.client.View;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Slider;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;


import de.eorg.cumulusgenius.client.CumulusGenius;
import de.eorg.cumulusgenius.client.View.Handlers.ViewHandler;

public class PreferencesView extends AbstractView implements IsWidget, EntryPoint {
	
	public PreferencesView(){
		setHeading(new HTML ("<h1>Preferences</h1>",true));
		setInstructions(new HTML("<h2>Please define Preferences comparing evaluation criteria in pairwise comparisons in matters of importance</h2>",true));	
		
		
		

		
		// Back und Next Button bauen
				IButton backButton = new IButton("Back");
				backButton.setTop(100);
				
				IButton nextButton = new IButton("Next");
				nextButton.setTop(100);
				nextButton.setLeft(220);

				backButton.addClickHandler(new ViewHandler(CumulusGenius.criteriaView));
				nextButton.addClickHandler(new ViewHandler(CumulusGenius.recommendationView));
				/**nextButton.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event){
					dao.createPreference((int)s1.getValue(), (int)s2.getValue(), (int)s3.getValue(), (int)s4.getValue(), (int)s5.getValue());
				}
			});*/
				
				
				HLayout buttonLayout = new HLayout();
				buttonLayout.setHeight(150);  
				buttonLayout.setMembersMargin(5);  
				buttonLayout.setLayoutMargin(10);  
				buttonLayout.addChild(backButton);
				buttonLayout.addChild(nextButton);
				
			//Alles auf in Layout packen
				getLayout().add(getHeading());
				getLayout().add(getInstructions());
				getLayout().add(asWidget());
				getLayout().add(buttonLayout);
				

	}

	@Override
	public void onModuleLoad() {
			RootPanel.get().add(asWidget());
		
	}
	
    final Slider s1 = new Slider(" ");
    final Slider s2 = new Slider(" ");
    final Slider s3 = new Slider(" ");
    final Slider s4 = new Slider(" ");
    final Slider s5 = new Slider(" ");


	@Override
	public Widget asWidget() {
		VerticalPanel vp = new VerticalPanel();
	    vp.setSpacing(10);
	 
	    s1.setVertical(false);
	    s1.setNumValues(5);
	    s1.setLength(800);
	    s1.setMinValueLabel("Cheap VM Image");
	    s1.setMaxValueLabel("Quality VM Image");
	    s1.setMaxValue(5);
	    s1.setMinValue(1);
	    vp.add(s1);
	    	    
	    
	    s2.setVertical(false);
	    s2.setNumValues(5);
	    s2.setLength(800);
	    s2.setMinValueLabel("Cheap Service");
	    s2.setMaxValueLabel("Quality Service");
	    s2.setMaxValue(5);
	    s2.setMinValue(1);
	    vp.add(s2);
	    
	    
	    s3.setVertical(false);
	    s3.setNumValues(5);
	    s3.setLength(800);
	    s3.setMinValueLabel("Latency Service");
	    s3.setMaxValueLabel("Cheap Service");
	    s3.setMaxValue(5);
	    s3.setMinValue(1);
	    vp.add(s3);
	    
	    
	    s4.setVertical(false);
	    s4.setNumValues(5);
	    s4.setLength(800);
	    s4.setMinValueLabel("Performance");
	    s4.setMaxValueLabel("Uptime");
	    s4.setMaxValue(5);
	    s4.setMinValue(1);
	    vp.add(s4);
	    
	    
	    s5.setVertical(false);
	    s5.setNumValues(5);
	    s5.setLength(800);
	    s5.setMinValueLabel("Initial Cost");
	    s5.setMaxValueLabel("Hourly Cost");
	    s5.setMaxValue(5);
	    s5.setMinValue(1);
	    vp.add(s5);
		return vp;
	}
	
	
	

}
