package de.eorg.cumulusgenius.client.datasource;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourceTextField;

public class CSRequirementsXmlDS extends DataSource {

	private static CSRequirementsXmlDS instance = null;

	public static CSRequirementsXmlDS getInstance() {
		if (instance == null) {
			instance = new CSRequirementsXmlDS("cs_requirementsDS");
		}
		return instance;
	}
	
	 public CSRequirementsXmlDS(String id) {  
		  
	        setID(id);  
	        setRecordXPath("/List/cs_requirement");  
	        DataSourceIntegerField pkField = new DataSourceIntegerField("pk");  
	        pkField.setHidden(true);  
	        pkField.setPrimaryKey(true);
	        
	        DataSourceTextField nameField = new DataSourceTextField("name", "Name");
	        nameField.setValueMap("Max. Latency", "Uptime", "Hourly Cost CPU");
	        
	        DataSourceTextField constraintField = new DataSourceTextField("constraint", "Constraint");
	        constraintField.setValueMap(" ", "=", "<", ">", "<=", ">=");
	        
	        DataSourceTextField valueField = new DataSourceTextField("value", "Value");	 
	        
	        DataSourceTextField metricField = new DataSourceTextField("metric", "Metric");
	        metricField.setValueMap("%", "ordinal", "$", "€", "ms", "$/h");
	        
	        
	        
	        setFields(pkField, nameField, constraintField, valueField, metricField);  
	  
	        setDataURL("ds/test_data/cs_requirements.data.xml");  
	        setClientOnly(true);  
	    }  

}
