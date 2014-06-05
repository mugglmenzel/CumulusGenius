package de.eorg.cumulusgenius.client.View;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.ImgButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;

import de.eorg.cumulusgenius.client.CumulusGenius;


public class WelcomeView extends AbstractView {

	public WelcomeView(){
		setHeading(new HTML ("<h1>Welcome to Cumulus Genius</h1>",true));

		VerticalPanel loginPanel = new VerticalPanel();
		loginPanel.setWidth("500px");
		loginPanel.setHeight("400px");
		loginPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		DOM.setElementAttribute(loginPanel.getElement(), "id", "loginPanel");
		
		Label log = new Label("Sign In");
		log.setWidth("400px");
		log.setHeight("100px");
		log.setAlign(Alignment.CENTER);
		DOM.setElementAttribute(log.getElement(),"id", "log");
		
		Label loginLabel = new Label(
				"Please sign in to your Google Account to access Cumulus Genius.");
		loginLabel.setHeight("200px");
		loginLabel.setWidth("400px");
		loginLabel.setAlign(Alignment.CENTER);
		DOM.setElementAttribute(loginLabel.getElement(), "id", "loginLabel");
		
		ImgButton img = new ImgButton();
		img.setShowFocused(false);
		img.setSrc("google-sign-in.png");
		img.setWidth("200px");
		img.setHeight("37px");
		img.setAlign(Alignment.CENTER);
		img.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){
				Window.Location.assign(CumulusGenius.loginInfo.getLoginUrl());
			}
		});
		
		
		loginPanel.add(log);
		loginPanel.add(loginLabel);
		loginPanel.add(img);
		getLayout().add(getHeading());
		getLayout().add(loginPanel);

	}}
