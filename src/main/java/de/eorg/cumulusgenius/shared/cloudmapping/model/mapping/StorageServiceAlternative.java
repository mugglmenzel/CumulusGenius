/**
 * 
 */
package de.eorg.cumulusgenius.shared.cloudmapping.model.mapping;

import de.eorg.cumulusgenius.shared.cloudmapping.model.ahp.configuration.Alternative;

/**
 * @author mugglmenzel
 * 
 */
public class StorageServiceAlternative extends Alternative {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6431007114719302435L;
	
	/**
	 * @uml.property  name="computeService"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private StorageService storageService;

	public StorageServiceAlternative(StorageService ss, String name) {
		setStorageService(ss);
		setName(name);
	}

	/**
	 * @return  the computeService
	 * @uml.property  name="computeService"
	 */
	public StorageService getStorageService() {
		return storageService;
	}

	/**
	 * @param computeService  the computeService to set
	 * @uml.property  name="computeService"
	 */
	public void setStorageService(StorageService storageService) {
		this.storageService = storageService;
	}

	@Override
	public Alternative clone() {
		Alternative alt = new StorageServiceAlternative(getStorageService(), getName());
		alt.setDescription(getDescription());
		return alt;
	}

}
