package de.eorg.cumulusgenius.client.View;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.smartgwt.client.types.ListGridEditEvent;
import com.smartgwt.client.types.RowEndEditAction;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.layout.HLayout;

import de.eorg.cumulusgenius.client.CumulusGenius;
import de.eorg.cumulusgenius.client.View.Handlers.ViewHandler;
import de.eorg.cumulusgenius.client.datasource.ComponentsXmlDS;
import de.eorg.cumulusgenius.client.services.CumulusGeniusService;
import de.eorg.cumulusgenius.client.services.CumulusGeniusServiceAsync;
import de.eorg.cumulusgenius.client.services.LoginInfo;

public class ComponentsView extends AbstractView {

	/**	final ListGridField componentTypeField = new ListGridField("componentType",
			"Component Type");
		final ListGridField softwareField = new ListGridField("software", "Software");
		final ListGridField interconnectionsField = new ListGridField(
			"interconnections", "Interconnections");*/
	
	public final ListGrid componentsGrid = new ListGrid();
	
	
	
	public ComponentsView() {
		final CumulusGeniusServiceAsync cumulusGeniusService = GWT.create(CumulusGeniusService.class);

		setHeading(new HTML("<h1>Components</h1>", true));
		setInstructions(new HTML(
				"<h2>Please define Requirements which have to be met by your System</h2>",
				true));
		Canvas canvas = new Canvas();

		componentsGrid.setWidth(500);
		componentsGrid.setHeight(224);
		componentsGrid.setCellHeight(22);
		componentsGrid.setDataSource(ComponentsXmlDS.getInstance());

		
				
		
		componentsGrid.setAutoFetchData(true);
		componentsGrid.setCanEdit(true);
		componentsGrid.setModalEditing(true);
		componentsGrid.setEditEvent(ListGridEditEvent.CLICK);
		componentsGrid.setListEndEditAction(RowEndEditAction.NEXT);
		componentsGrid.setAutoSaveEdits(false);
		canvas.addChild(componentsGrid);

		IButton addComponentButton = new IButton("Add Component");
		addComponentButton.setTop(250);
		addComponentButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				componentsGrid.startEditingNew();
				
			}
		});
		canvas.addChild(addComponentButton);

		IButton saveButton = new IButton("Save");
		saveButton.setTop(250);
		saveButton.setLeft(110);
		saveButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				componentsGrid.saveAllEdits();
				
				
				ListGridField[] componentsField = componentsGrid.getAllFields();
				/**for(int i = 0;i < componentsField.length;i++)
				{
					cumulusGeniusService.createComponent(componentsField[i].getDisplayField(), componentsField[i+1].getDisplayField(),componentsField[i+2].getDisplayField(), new AsyncCallback<Void>(){

				
					
					@Override
					public void onSuccess(Void test) {
					
					//SC.say("Saved.");
					}

					@Override
					public void onFailure(Throwable caught) {
					//SC.warn("Something went wrong!");
					}
			});
			}*/
		}});
		canvas.addChild(saveButton);

		IButton deleteButton = new IButton("Delete Component");
		deleteButton.setTop(250);
		deleteButton.setLeft(220);
		deleteButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				componentsGrid.removeSelectedData();
			}
		});
		canvas.addChild(deleteButton);

		// Back und Next Button hinzufügen
		IButton backButton = new IButton("Back");
		backButton.setTop(100);
		
		IButton nextButton = new IButton("Next");
		nextButton.setTop(100);
		nextButton.setLeft(220);
		
		nextButton.addClickHandler(new ViewHandler(CumulusGenius.requirementsView));
		backButton.addClickHandler(new ViewHandler(CumulusGenius.componentsView));
		
		

		
		HLayout buttonLayout = new HLayout();
		buttonLayout.setHeight(150);  
		buttonLayout.setMembersMargin(5);  
		buttonLayout.setLayoutMargin(10);  
		buttonLayout.addChild(backButton);
		buttonLayout.addChild(nextButton);

		Label testLabel = new Label();
		ListGridField[] componentsField1 = componentsGrid.getAllFields();
		if(componentsField1[1] == null)
		testLabel.setText("Das Feld ist leer.");
		
		// Alle Elemente in Layout packen
		getLayout().add(getHeading());
		getLayout().add(getInstructions());
		getLayout().add(canvas);
		getLayout().add(buttonLayout);
		getLayout().add(testLabel);
	}
	


}
