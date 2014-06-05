/**
 * 
 */
package de.eorg.cumulusgenius.shared.cloudmapping.model;

import de.eorg.cumulusgenius.shared.cloudmapping.model.mapping.Provider;

/**
 * @author mugglmenzel
 * 
 */
public class BLOBStorage {

	private String name;

	private Double storageCost;

	private Double networkUploadCost;

	private Double networkDownloadCost;

	private Double networkHops;

	private Double bandwidthConnection;

	private Double latencyConnection;
	
	private Provider provider;
	
	
	
	

	/**
	 * @param name
	 * @param storageCost
	 * @param networkUploadCost
	 * @param networkDownloadCost
	 * @param networkHops
	 * @param bandwidthConnection
	 * @param latencyConnection
	 */
	public BLOBStorage(String name, Double storageCost,
			Double networkUploadCost, Double networkDownloadCost,
			Double networkHops, Double bandwidthConnection,
			Double latencyConnection, Provider provider) {
		super();
		this.name = name;
		this.storageCost = storageCost;
		this.networkUploadCost = networkUploadCost;
		this.networkDownloadCost = networkDownloadCost;
		this.networkHops = networkHops;
		this.bandwidthConnection = bandwidthConnection;
		this.latencyConnection = latencyConnection;
		this.setProvider(provider);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the storageCost
	 */
	public Double getStorageCost() {
		return storageCost;
	}

	/**
	 * @return the networkUploadCost
	 */
	public Double getNetworkUploadCost() {
		return networkUploadCost;
	}

	/**
	 * @return the networkDownloadCost
	 */
	public Double getNetworkDownloadCost() {
		return networkDownloadCost;
	}

	/**
	 * @return the networkHops
	 */
	public Double getNetworkHops() {
		return networkHops;
	}

	/**
	 * @return the bandwidthConnection
	 */
	public Double getBandwidthConnection() {
		return bandwidthConnection;
	}

	/**
	 * @return the latencyConnection
	 */
	public Double getLatencyConnection() {
		return latencyConnection;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param storageCost the storageCost to set
	 */
	public void setStorageCost(Double storageCost) {
		this.storageCost = storageCost;
	}

	/**
	 * @param networkUploadCost the networkUploadCost to set
	 */
	public void setNetworkUploadCost(Double networkUploadCost) {
		this.networkUploadCost = networkUploadCost;
	}

	/**
	 * @param networkDownloadCost the networkDownloadCost to set
	 */
	public void setNetworkDownloadCost(Double networkDownloadCost) {
		this.networkDownloadCost = networkDownloadCost;
	}

	/**
	 * @param networkHops the networkHops to set
	 */
	public void setNetworkHops(Double networkHops) {
		this.networkHops = networkHops;
	}

	/**
	 * @param bandwidthConnection the bandwidthConnection to set
	 */
	public void setBandwidthConnection(Double bandwidthConnection) {
		this.bandwidthConnection = bandwidthConnection;
	}

	/**
	 * @param latencyConnection the latencyConnection to set
	 */
	public void setLatencyConnection(Double latencyConnection) {
		this.latencyConnection = latencyConnection;
	}

	/**
	 * @return the provider
	 */
	public Provider getProvider() {
		return provider;
	}

	/**
	 * @param provider the provider to set
	 */
	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	
	
}
