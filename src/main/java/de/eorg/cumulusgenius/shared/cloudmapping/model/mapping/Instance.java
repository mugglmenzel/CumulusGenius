package de.eorg.cumulusgenius.shared.cloudmapping.model.mapping;


public class Instance {

	/**
	 * @uml.property  name="appliance"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private Appliance appliance;

	/**
	 * @uml.property  name="computeService"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private ComputeService computeService;


	/**
	 * @param appliance
	 * @param computeResource
	 */
	public Instance(Appliance appliance, ComputeService computeResource) {
		super();
		this.appliance = appliance;
		this.computeService = computeResource;
	}



	/**
	 * @return  the appliance
	 * @uml.property  name="appliance"
	 */
	public Appliance getAppliance() {
		return appliance;
	}



	/**
	 * @param appliance  the appliance to set
	 * @uml.property  name="appliance"
	 */
	public void setAppliance(Appliance appliance) {
		this.appliance = appliance;
	}



	/**
	 * @return  the computeResource
	 * @uml.property  name="computeService"
	 */
	public ComputeService getComputeService() {
		return computeService;
	}



	/**
	 * @param computeResource  the computeResource to set
	 * @uml.property  name="computeService"
	 */
	public void setComputeService(ComputeService computeResource) {
		this.computeService = computeResource;
	}



	@Override
	public String toString() {

		return "Appliance: " + this.appliance.getName() + "\n" + "Resource: "
				+ this.computeService.getName() + "\n \n";
	}
}
