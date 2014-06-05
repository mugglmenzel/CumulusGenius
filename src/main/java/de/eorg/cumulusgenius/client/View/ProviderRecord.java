package de.eorg.cumulusgenius.client.View;

import java.io.Serializable;

import com.googlecode.objectify.annotation.Entity;

@Entity
public class ProviderRecord implements Serializable {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 9060225882014217467L;

		private String name;
		private String price;
		private String picture;
		private String description;
	  
	    public ProviderRecord(String name, String price, String picture) {  
	        this(name, price, picture,null);  
	    }  
	  
	    public ProviderRecord(String name, String price, String picture, String description) {  
	        this.name = name;  
	        this.price = price;  
	        this.picture = picture;  
	        this.description = description;  
	    }  
	  
	    /** 
	     * Set the name. 
	     * 
	     * @param name the name 
	     */  
	    public void setName(String name) {  
	        this.name = name;  
	    }  
	  
	    /** 
	     * Return the name. 
	     * 
	     * @return the name 
	     */  
	    public String getName() {  
	        return this.name;  
	    }  
	  
	    /** 
	     * Set the price. 
	     * 
	     * @param price the price 
	     */  
	    public void setPrice(String price) {  
	        this.price = price;  
	    }  
	  
	    /** 
	     * Return the price. 
	     * 
	     * @return the price 
	     */  
	    public String getPrice() {  
	        return this.price;  
	    }  
	  
	    /** 
	     * Set the picture. 
	     * 
	     * @param picture the picture 
	     */  
	    public void setPicture(String picture) {  
	        this.picture = picture;  
	    }  
	  
	    /** 
	     * Return the picture. 
	     * 
	     * @return the picture 
	     */  
	    public String getPicture() {  
	        return this.picture;  
	    }  
	  
	    /** 
	     * Set the description. 
	     * 
	     * @param description the description 
	     */  
	    public void setDescription(String description) {  
	        this.description = description;  
	    }  
	  
	    /** 
	     * Return the description. 
	     * 
	     * @return the description 
	     */  
	    public String getDescription() {  
	        return this.description;  
	    }  
	  
	  
	}  
