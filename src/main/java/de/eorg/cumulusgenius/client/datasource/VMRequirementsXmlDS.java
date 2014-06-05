package de.eorg.cumulusgenius.client.datasource;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourceTextField;

public class VMRequirementsXmlDS extends DataSource {

	private static VMRequirementsXmlDS instance = null;

	public static VMRequirementsXmlDS getInstance() {
		if (instance == null) {
			instance = new VMRequirementsXmlDS("vm_requirementsDS");
		}
		return instance;
	}
	
	 public VMRequirementsXmlDS(String id) {  
		  
	        setID(id);  
	        setRecordXPath("/List/vm_requirement");  
	        DataSourceIntegerField pkField = new DataSourceIntegerField("pk");  
	        pkField.setHidden(true);  
	        pkField.setPrimaryKey(true);
	        
	        DataSourceTextField nameField = new DataSourceTextField("name", "Name");
	        nameField.setValueMap("Operating System", "Popularity", "Format", "Age", "Software Feature", "Price");
	        
	        DataSourceTextField constraintField = new DataSourceTextField("constraint", "Constraint");
	        constraintField.setValueMap(" ", "=", "<", ">", "<=", ">=");
	        
	        DataSourceTextField valueField = new DataSourceTextField("value", "Value");	 
	        
	        DataSourceTextField metricField = new DataSourceTextField("metric", "Metric");
	        metricField.setValueMap("%", "ordinal", "$", "€");
	        
	        
	        
	        setFields(pkField, nameField, constraintField, valueField, metricField);  
	  
	        setDataURL("ds/test_data/vm_requirements.data.xml");  
	        setClientOnly(true);  
	    }  

}
