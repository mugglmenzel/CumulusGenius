package de.eorg.cumulusgenius.client.View.Handlers;

import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;

import de.eorg.cumulusgenius.client.CumulusGenius;
import de.eorg.cumulusgenius.client.View.AbstractView;


public class ViewHandler implements ClickHandler {
	
	AbstractView av = CumulusGenius.componentsView;
	
	public ViewHandler (AbstractView av){
	this.av= av;
	}
	
	

	@Override
	public void onClick(ClickEvent event) {
				
		CumulusGenius.componentsView.getLayout().setVisible(false);
		CumulusGenius.requirementsView.getLayout().setVisible(false);
		CumulusGenius.criteriaView.getLayout().setVisible(false);
		CumulusGenius.preferencesView.getLayout().setVisible(false);
		CumulusGenius.recommendationView.getLayout().setVisible(false);
		
		//if (av == CumulusGenius.componentsView)
		//{
		//	CumulusGenius.componentsView.componentInterconnections[0] = "Test";
		//}
		
		av.getLayout().setVisible(true);
		

		
		
	}

}
