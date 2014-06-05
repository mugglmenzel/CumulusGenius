package de.eorg.cumulusgenius.client.View;

import com.google.gwt.user.client.ui.HTML;
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
import de.eorg.cumulusgenius.client.datasource.CSRequirementsXmlDS;
import de.eorg.cumulusgenius.client.datasource.VMRequirementsXmlDS;

public class RequirementsView extends AbstractView {

	public RequirementsView() {

		setHeading(new HTML("<h1>Requirements</h1>", true));
		setInstructions(new HTML(
				"<h2>Please define Requirements which have to be met by your System</h2>",
				true));

		// Überschrift setzen
		HTML vMconstraints = new HTML(
				"<h2>Virtual Machine Image Constraints</h2>", true);
		
		// erstes Grid erstellen
		Canvas vMcanvas = new Canvas();

		final ListGrid vMGrid = new ListGrid();
		vMGrid.setWidth(500);
		vMGrid.setHeight(150);
		vMGrid.setCellHeight(22);
		vMGrid.setDataSource(VMRequirementsXmlDS.getInstance());

		final ListGridField nameTypeField = new ListGridField("name", "Name");
		final ListGridField interconnectionsField = new ListGridField("constraint",
				"Constraint");
		final ListGridField valueFiel = new ListGridField("value", "Value");
		final ListGridField softwareField = new ListGridField("metric", "Metric");

		vMGrid.setAutoFetchData(true);
		vMGrid.setCanEdit(true);
		vMGrid.setModalEditing(true);
		vMGrid.setEditEvent(ListGridEditEvent.CLICK);
		vMGrid.setListEndEditAction(RowEndEditAction.NEXT);
		vMGrid.setAutoSaveEdits(false);
		vMcanvas.addChild(vMGrid);

		// Buttons zum editieren der Tabelle
		IButton addComponentButton = new IButton("Add Component");
		addComponentButton.setTop(250);
		addComponentButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				vMGrid.startEditingNew();
			}
		});
		vMcanvas.addChild(addComponentButton);

		IButton saveButton = new IButton("Save");
		saveButton.setTop(250);
		saveButton.setLeft(110);
		saveButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				vMGrid.saveAllEdits();
			}
		});
		vMcanvas.addChild(saveButton);

		IButton deleteButton = new IButton("Delete Component");
		deleteButton.setTop(250);
		deleteButton.setLeft(220);
		deleteButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				vMGrid.removeSelectedData();
			}
		});
		vMcanvas.addChild(deleteButton);
		
		//Überschrift setzen
		HTML cSconstraints = new HTML("<h2>Cloud Service Constraints</h2>",
				true);
		
		//zweites Grid erstellen
		Canvas cScanvas = new Canvas();
		final ListGrid cSGrid = new ListGrid();
		cSGrid.setWidth(500);
		cSGrid.setHeight(150);
		cSGrid.setCellHeight(22);
		cSGrid.setDataSource(CSRequirementsXmlDS.getInstance());

		final ListGridField cS_nameTypeField = new ListGridField("name", "Name");
		final ListGridField cs_interconnectionsField = new ListGridField("constraint",
				"Constraint");
		final ListGridField cS_valueFiel = new ListGridField("value", "Value");
		final ListGridField cS_softwareField = new ListGridField("metric", "Metric");
		

		cSGrid.setAutoFetchData(true);
		cSGrid.setCanEdit(true);
		cSGrid.setModalEditing(true);
		cSGrid.setEditEvent(ListGridEditEvent.CLICK);
		cSGrid.setListEndEditAction(RowEndEditAction.NEXT);
		cSGrid.setAutoSaveEdits(false);
		cScanvas.addChild(cSGrid);
		
		// Buttons zum editieren der Tabelle
				IButton cS_addComponentButton = new IButton("Add Component");
				cS_addComponentButton.setTop(250);
				cS_addComponentButton.addClickHandler(new ClickHandler() {
					public void onClick(ClickEvent event) {
						cSGrid.startEditingNew();
					}
				});
				cScanvas.addChild(cS_addComponentButton);

				IButton cS_saveButton = new IButton("Save");
				cS_saveButton.setTop(250);
				cS_saveButton.setLeft(110);
				cS_saveButton.addClickHandler(new ClickHandler() {
					public void onClick(ClickEvent event) {
						cSGrid.saveAllEdits();
					}
				});
				cScanvas.addChild(cS_saveButton);

				IButton cS_deleteButton = new IButton("Delete Component");
				cS_deleteButton.setTop(250);
				cS_deleteButton.setLeft(220);
				cS_deleteButton.addClickHandler(new ClickHandler() {
					public void onClick(ClickEvent event) {
						cSGrid.removeSelectedData();
					}
				});
				cScanvas.addChild(cS_deleteButton);
		

		// Back und Next Button bauen
		IButton backButton = new IButton("Back");
		backButton.setTop(100);

		IButton nextButton = new IButton("Next");
		nextButton.setTop(100);
		nextButton.setLeft(220);

		backButton.addClickHandler(new ViewHandler(CumulusGenius.componentsView));
		nextButton.addClickHandler(new ViewHandler(CumulusGenius.criteriaView));
		/**nextButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event){
				dao.createVMRequirement(nameTypeField.getValueField(), interconnectionsField.getValueField(), valueFiel.getValueField(),softwareField.getValueField());
				dao.createCSRequirement(cS_nameTypeField.getValueField(), cs_interconnectionsField.getValueField(), cS_valueFiel.getValueField(), cS_softwareField.getValueField());
			}
		});*/

		HLayout buttonLayout = new HLayout();
		buttonLayout.setHeight(150);
		buttonLayout.setMembersMargin(5);
		buttonLayout.setLayoutMargin(10);
		buttonLayout.addChild(backButton);
		buttonLayout.addChild(nextButton);

		// Alles auf in Layout packen
		getLayout().add(getHeading());
		getLayout().add(getInstructions());

		getLayout().add(vMconstraints);
		getLayout().add(vMcanvas);

		getLayout().add(cSconstraints);
		getLayout().add(cScanvas);

		getLayout().add(buttonLayout);

	}
}
