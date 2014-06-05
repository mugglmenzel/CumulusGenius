package de.eorg.cumulusgenius.client.datasource;  
  
import com.smartgwt.client.data.DataSource;  
import com.smartgwt.client.data.fields.*;  
  
public class ComponentsXmlDS extends DataSource {  
  
    private static ComponentsXmlDS instance = null;  
  
    public static ComponentsXmlDS getInstance() {  
        if (instance == null) {  
            instance = new ComponentsXmlDS("componentsDS");  
        }  
        return instance;  
    }  
  
    public ComponentsXmlDS(String id) {  
  
        setID(id);  
        setRecordXPath("/List/component");  
        DataSourceIntegerField pkField = new DataSourceIntegerField("pk");  
        pkField.setHidden(true);  
        pkField.setPrimaryKey(true);
        
        DataSourceTextField componentTypeField = new DataSourceTextField("componentType", "Component Type");
        componentTypeField.setValueMap("Load Balancer","Web Server", "Application Server");
        
        DataSourceTextField softwareField = new DataSourceTextField("software", "Software");
        softwareField.setValueMap("ElasticLoadBalancer", "Apache 2.0", "Tomcat", "JBoss");
        
        DataSourceTextField interconnectionsField = new DataSourceTextField("interconnections", "Interconnections");
        interconnectionsField.setValueMap("lb1", "lb2", "ws1", "ws2", "as1", "as2");
        
        
        
        
        setFields(pkField, componentTypeField, softwareField, interconnectionsField);  
  
        setDataURL("ds/test_data/components.data.xml");  
        setClientOnly(true);  
    }  
}  
