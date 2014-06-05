package de.eorg.cumulusgenius.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Anchor;
import com.smartgwt.client.widgets.ImgButton;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.Layout;
import com.smartgwt.client.widgets.layout.VLayout;

import de.eorg.cumulusgenius.client.View.ComponentsView;
import de.eorg.cumulusgenius.client.View.CriteriaView;
import de.eorg.cumulusgenius.client.View.PreferencesView;
import de.eorg.cumulusgenius.client.View.RecommendationView;
import de.eorg.cumulusgenius.client.View.RequirementsView;
import de.eorg.cumulusgenius.client.View.WelcomeView;
import de.eorg.cumulusgenius.client.View.Handlers.ViewHandler;
import de.eorg.cumulusgenius.client.services.CumulusGeniusService;
import de.eorg.cumulusgenius.client.services.CumulusGeniusServiceAsync;
import de.eorg.cumulusgenius.client.services.LoginInfo;
import de.eorg.cumulusgenius.client.services.LoginService;
import de.eorg.cumulusgenius.client.services.LoginServiceAsync;




/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class CumulusGenius implements EntryPoint {

	public static LoginServiceAsync loginService = GWT
			.create(LoginService.class);
	
	public static CumulusGeniusServiceAsync cumulusGeniusService = GWT
			.create(CumulusGeniusService.class);

	public static LoginInfo loginInfo = null;

	private Anchor signOutLink = new Anchor("Sign Out");

	public static TabPanel tabs = new TabPanel();

	ImgButton componentsButton = new ImgButton();
	ImgButton requirementsButton = new ImgButton();
	ImgButton criteriaButton = new ImgButton();
	ImgButton preferencesButton = new ImgButton();
	ImgButton recommendationButton = new ImgButton();

	
	//liste bauen mit indizes
	{
		
	}
	public static WelcomeView welcomeView = new WelcomeView();
	public static ComponentsView componentsView = new ComponentsView();
	public static RequirementsView requirementsView = new RequirementsView();
	public static CriteriaView criteriaView = new CriteriaView();
	public static PreferencesView preferencesView = new PreferencesView();
	public static RecommendationView recommendationView = new RecommendationView();
	
	public static Layout hPan = new HLayout();
	public static Layout vPan = new VLayout();

	@Override
	public void onModuleLoad() {

		// Check login status using login service.
		loginService.login(GWT.getHostPageBaseURL(),
				new AsyncCallback<LoginInfo>() {
					public void onFailure(Throwable error) {
					}

					public void onSuccess(LoginInfo result) {
						loginInfo = result;
						if (loginInfo.isLoggedIn()) {
							createMasterLayout();
						} else {
							loadLogin();
						}

					}
				});

	}

	private void loadLogin() {
		RootPanel.get("cumulus").add(welcomeView.getLayout());

	}

	private void createMasterLayout() {

		componentsButton.setWidth("150px");
		componentsButton.setHeight("50px");
		componentsButton.setSrc("bild1.png");
		componentsButton.setTitle("Components");
		componentsButton.setShowTitle(true);

		requirementsButton.setWidth("150px");
		requirementsButton.setLeft("150px");
		requirementsButton.setHeight("50px");
		requirementsButton.setSrc("bild2.png");
		requirementsButton.setTitle("Requirements");
		requirementsButton.setShowTitle(true);

		criteriaButton.setWidth("150px");
		criteriaButton.setLeft("300px");
		criteriaButton.setHeight("50px");
		criteriaButton.setSrc("bild2.png");
		criteriaButton.setTitle("Criteria");
		criteriaButton.setShowTitle(true);

		preferencesButton.setWidth("150px");
		preferencesButton.setLeft("450px");
		preferencesButton.setHeight("50px");
		preferencesButton.setSrc("bild2.png");
		preferencesButton.setTitle("Preferences");
		preferencesButton.setShowTitle(true);

		recommendationButton.setSrc("bild3.png");
		recommendationButton.setLeft("600px");
		recommendationButton.setTitle("Recommendation");
		recommendationButton.setWidth("150px");
		recommendationButton.setHeight("50px");
		recommendationButton.setShowTitle(true);

		hPan.setTop("50px");
		hPan.addChild(componentsButton);
		hPan.addChild(requirementsButton);
		hPan.addChild(criteriaButton);
		hPan.addChild(preferencesButton);
		hPan.addChild(recommendationButton);
		
		
		
		
		VLayout vLay = new VLayout();
		vLay.setTop("100px");
		vLay.addChild(componentsView.getLayout());
		vLay.addChild(requirementsView.getLayout());
		vLay.addChild(criteriaView.getLayout());
		vLay.addChild(preferencesView.getLayout());
		vLay.addChild(recommendationView.getLayout());
		
		
		
		requirementsView.getLayout().setVisible(false);
		criteriaView.getLayout().setVisible(false);
		preferencesView.getLayout().setVisible(false);
		recommendationView.getLayout().setVisible(false);
		
		

		componentsButton.addClickHandler(new ViewHandler(componentsView));
		requirementsButton.addClickHandler(new ViewHandler(requirementsView));
		criteriaButton.addClickHandler(new ViewHandler(criteriaView));
		preferencesButton.addClickHandler(new ViewHandler(preferencesView));
		recommendationButton.addClickHandler(new ViewHandler (recommendationView));
		
		
		
		
		// Set up sign out hyperlink.
		signOutLink.setHref(loginInfo.getLogoutUrl());
		
		vPan.addChild(signOutLink);
		vPan.addChild(hPan);
		vPan.addChild(vLay);
		//
		// tabs.add(componentsView.getLayout(),
		// "<div id=\"components\">Components</div>", true);
		//
		// tabs.add(requirementsView.getLayout(),
		// "<div id=\"requirements\">Requirements</div>", true);
		// tabs.add(criteriaView.getLayout(),
		// "<div id=\"criteria\">Criteria</div>", true);
		// tabs.add(preferencesView.getLayout(),
		// "<div id=\"preferences\">Preferences</div>", true);
		// tabs.add(preferencesView.getLayout(),
		// "<div id=\"recommendation\">Recommendation</div>", true);
		//
		// tabs.setWidth("100%");
		// tabs.selectTab(0);
		//
		// VerticalPanel vp = new VerticalPanel();
		// vp.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		// vp.add(signOutLink);
		// vp.add(tabs);
		// vp.add(hPan);
		//
		// RootPanel.get("cumulus").add(vp);

		RootPanel.get("cumulus").add(vPan);

	}

	public static TabPanel getTabs() {
		return tabs;
	}

	public static void setTabs(TabPanel tabs) {
		CumulusGenius.tabs = tabs;
	}
}
