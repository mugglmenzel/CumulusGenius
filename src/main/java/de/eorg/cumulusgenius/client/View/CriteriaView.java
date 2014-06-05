package de.eorg.cumulusgenius.client.View;

import com.google.gwt.user.client.ui.HTML;
import com.smartgwt.client.types.SelectionAppearance;  
import com.smartgwt.client.types.TreeModelType;  
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.DrawEvent;  
import com.smartgwt.client.widgets.events.DrawHandler;  
import com.smartgwt.client.widgets.layout.HLayout;  
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tree.Tree;  
import com.smartgwt.client.widgets.tree.TreeGrid;  
import com.smartgwt.client.widgets.tree.TreeNode;   


import de.eorg.cumulusgenius.client.CumulusGenius;
import de.eorg.cumulusgenius.client.View.Handlers.ViewHandler;

public class CriteriaView extends AbstractView {
	  public static final TreeNode[] vMImageData = new TreeNode[] {  
          new vMImageTreeNode("100", "1", "CheapestVMImage", true),  
          new vMImageTreeNode("200", "100", "Initial License Costs", false),  
          new vMImageTreeNode("300", "100", "Hourly Licence Costs", true), 
          new vMImageTreeNode("400", "1", "Best Quality VM Image", true),
          new vMImageTreeNode("500", "400", "Popularity", false),  
          new vMImageTreeNode("600", "400", "Age", false)  
  };  
	  


	  public static final TreeNode[] cloudServiceData = new TreeNode[] {  
          new cloudServiceTreeNode("100", "1", "Best Quality Service", true),  
          new cloudServiceTreeNode("110", "100", "Performance", false),  
          new cloudServiceTreeNode("111", "110", "CPU", true), 
          new cloudServiceTreeNode("112", "110", "RAM", true),
          new cloudServiceTreeNode("120", "100", "Uptime", false),  
          new cloudServiceTreeNode("130", "100", "Popularity", false),
          new cloudServiceTreeNode("200", "1", "Cheapest Service", false),
          new cloudServiceTreeNode("210", "200", "Initial License Costs", false),
          new cloudServiceTreeNode("220", "200", "Hourly License Costs", false),
          new cloudServiceTreeNode("300", "1", "Best Latency Service", false),
          new cloudServiceTreeNode("310", "300", "Max. Lateny", false),
          new cloudServiceTreeNode("320", "300", "Avg. Latency", false)
  };  

	
	public CriteriaView(){
		setHeading(new HTML ("<h1>Criteria</h1>",true));
		setInstructions(new HTML("<h2>Please define Criteria on which alternatives will be evaluated</h2>",true));	
		

		
		// first tree
		 final Tree vmImageTree = new Tree();  
		 vmImageTree.setModelType(TreeModelType.PARENT);  
		 vmImageTree.setRootValue(1);  
		 vmImageTree.setNameProperty("Name");  
	        vmImageTree.setIdField("Id");  
	        vmImageTree.setParentIdField("ReportsTo");  
	        vmImageTree.setOpenProperty("isOpen");  
	        vmImageTree.setData(vMImageData);  
	  
	  
	        final TreeGrid vMImageTreeGrid = new TreeGrid();  
	        vMImageTreeGrid.setWidth(200);  
	        vMImageTreeGrid.setHeight(170);  
	        vMImageTreeGrid.setShowOpenIcons(false);  
	        vMImageTreeGrid.setShowDropIcons(false);  
	        vMImageTreeGrid.setClosedIconSuffix("");  
	        vMImageTreeGrid.setData(vmImageTree);  
	        vMImageTreeGrid.setSelectionAppearance(SelectionAppearance.CHECKBOX);  
	        vMImageTreeGrid.setShowSelectedStyle(false);  
	        vMImageTreeGrid.setShowPartialSelection(true);  
	        vMImageTreeGrid.setCascadeSelection(true);  
	  
	        vMImageTreeGrid.addDrawHandler(new DrawHandler() {  
	            public void onDraw(DrawEvent event) {  
	            	vMImageTreeGrid.getTree().openAll();  
	            }  
	        });  
	  
	        VLayout layout_one = new VLayout();  
			HTML h_1 = new HTML ("<h3>Virtual Machine Image Criteria</h3>",true);
	        h_1.setWidth("200px");

			

	        layout_one.addMember(h_1);
	        layout_one.addMember(vMImageTreeGrid); 
	        
	     // second tree
	        final Tree cloudServiceTree = new Tree();  
	        cloudServiceTree.setModelType(TreeModelType.PARENT);  
	        cloudServiceTree.setRootValue(1);  
	        cloudServiceTree.setNameProperty("Name");  
	        cloudServiceTree.setIdField("Id");  
	        cloudServiceTree.setParentIdField("ReportsTo");  
	        cloudServiceTree.setOpenProperty("isOpen");  
	        cloudServiceTree.setData(cloudServiceData);  
	  
	  
	        final TreeGrid cloudServiceTreeGrid = new TreeGrid();  
	        cloudServiceTreeGrid.setWidth(200);  
	        cloudServiceTreeGrid.setHeight(330);  
	        cloudServiceTreeGrid.setShowOpenIcons(false);  
	        cloudServiceTreeGrid.setShowDropIcons(false);  
	        cloudServiceTreeGrid.setClosedIconSuffix("");  
	        cloudServiceTreeGrid.setData(cloudServiceTree);  
	        cloudServiceTreeGrid.setSelectionAppearance(SelectionAppearance.CHECKBOX);  
	        cloudServiceTreeGrid.setShowSelectedStyle(false);  
	        cloudServiceTreeGrid.setShowPartialSelection(true);  
	        cloudServiceTreeGrid.setCascadeSelection(true);  
	  
	        cloudServiceTreeGrid.addDrawHandler(new DrawHandler() {  
	            public void onDraw(DrawEvent event) {  
	            	cloudServiceTreeGrid.getTree().openAll();  
	            }  
	        });  
	  
	        VLayout layout_two = new VLayout();
	        HTML h_2 = new HTML ("<h3>Cloud Service Criteria</h3>",true);
	        h_2.setWidth("200px");
	        
	        
	        layout_two.addMember(h_2);
	        layout_two.addMember(cloudServiceTreeGrid); 
		
		
		// Back und Next Button bauen
				IButton backButton = new IButton("Back");
				backButton.setTop(100);
				
				IButton nextButton = new IButton("Next");
				nextButton.setTop(100);
				nextButton.setLeft(220);

				backButton.addClickHandler(new ViewHandler(CumulusGenius.requirementsView));
				nextButton.addClickHandler(new ViewHandler(CumulusGenius.preferencesView));
				/**nextButton.addClickHandler(new ClickHandler() {
					public void onClick(ClickEvent event){
						dao.createVMCriteria(vmImageTree.getAttributeAsBoolean("Initial License Costs"), vmImageTree.getAttributeAsBoolean("Hourly Licence Costs"), vmImageTree.getAttributeAsBoolean("Popularity"), vmImageTree.getAttributeAsBoolean("Age"));
						dao.createCSCriteria(cloudServiceTree.getAttributeAsBoolean("CPU"), cloudServiceTree.getAttributeAsBoolean("RAM"), cloudServiceTree.getAttributeAsBoolean("Uptime"), cloudServiceTree.getAttributeAsBoolean("Popularity"), cloudServiceTree.getAttributeAsBoolean("Initial License Costs"), cloudServiceTree.getAttributeAsBoolean("Hourly Licence Costs"), cloudServiceTree.getAttributeAsBoolean("Max. Lateny"), cloudServiceTree.getAttributeAsBoolean("Avg. Lateny"));
					}
				});
				*/

				HLayout buttonLayout = new HLayout();
				buttonLayout.setHeight(150);  
				buttonLayout.setMembersMargin(5);  
				buttonLayout.setLayoutMargin(10);  
				buttonLayout.addChild(backButton);
				buttonLayout.addChild(nextButton);
				
			//Alles in Layout packen
				getLayout().add(getHeading());
				getLayout().add(getInstructions());
				getLayout().add(layout_one);
				getLayout().add(layout_two);
				getLayout().add(buttonLayout);
	}
	
	
	  public static class vMImageTreeNode extends TreeNode {  
	        public vMImageTreeNode(String id, String reportsTo, String name, boolean isOpen) {  
	            setAttribute("Id", id);  
	            setAttribute("ReportsTo", reportsTo);  
	            setAttribute("Name", name);  
	            setAttribute("isOpen", isOpen);  
	        }  
	    }  
	  

	  public static class cloudServiceTreeNode extends TreeNode {  
	        public cloudServiceTreeNode(String id, String reportsTo, String name, boolean isOpen) {  
	            setAttribute("Id", id);  
	            setAttribute("ReportsTo", reportsTo);  
	            setAttribute("Name", name);  
	            setAttribute("isOpen", isOpen);  
	        }  
	    }  
	
	    
	    

	

}
