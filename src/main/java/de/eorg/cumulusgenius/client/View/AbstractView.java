package de.eorg.cumulusgenius.client.View;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.layout.Layout;
import com.smartgwt.client.widgets.layout.VLayout;

public abstract class AbstractView {
	

	private VerticalPanel layout = new VerticalPanel();
	private HTML heading;
	private HTML instructions;
	
	private Layout content = new VLayout();
	private Canvas navigation = new Canvas();

	private Label postit = new Label();
	private Label postitHeader = new Label();
	
	
	public AbstractView (){
		
	}


	/**
	 * @return
	 * @uml.property  name="layout"
	 */
	public VerticalPanel getLayout() {
		return layout;
	}


	/**
	 * @param layout
	 * @uml.property  name="layout"
	 */
	public void setLayout(VerticalPanel layout) {
		this.layout = layout;
	}


	public HTML getHeading() {
		return heading;
	}

	public void setHeading(HTML heading) {
		this.heading = heading;
	}

	public HTML getInstructions() {
		return instructions;
	}

	public void setInstructions(HTML instructions) {
		this.instructions = instructions;
	}

	public Layout getContent() {
		return content;
	}

	public void setContent(Layout content) {
		this.content = content;
	}

	public Canvas getNavigation() {
		return navigation;
	}


	public void setNavigation(Canvas navigation) {
		this.navigation = navigation;
	}

	public Label getPostit() {
		return postit;
	}


	public void setPostit(Label postit) {
		this.postit = postit;
	}


	public Label getPostitHeader() {
		return postitHeader;
	}

	public void setPostitHeader(Label postitHeader) {
		this.postitHeader = postitHeader;
	}
	

}
