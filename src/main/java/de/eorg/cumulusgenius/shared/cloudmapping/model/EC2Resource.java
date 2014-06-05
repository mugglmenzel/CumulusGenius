package de.eorg.cumulusgenius.shared.cloudmapping.model;

import de.eorg.cumulusgenius.shared.cloudmapping.model.mapping.ComputeService;
import de.eorg.cumulusgenius.shared.cloudmapping.model.mapping.IAttributedItem;

public class EC2Resource extends ComputeService {

	// In our first algrithm approach we concentrate on the
	// specific name of the instance, its costs and some benchmark values.

	/**
	 * 
	 */
	private static final long serialVersionUID = 8851622447841903053L;

	public EC2Resource(String name) {
		super(name);
	}

	@Override
	public int compareTo(IAttributedItem o) {
		return (o instanceof EC2Resource) ? getName().compareTo(
				((EC2Resource) o).getName())
				: ((o.getAttributes() != null && o.getAttributes() != null) ? getAttributes()
						.size() - o.getAttributes().size()
						: (getAttributes() != null ? 1 : -1));
	}

	/*
	 * String family = ""; //e.g. standard, Micro, High-CPU, High-Memory,
	 * Cluster-Compute, Cluster-GPU
	 * 
	 * String type = ""; //e.g. Small, Large, Extra Large, High-Memory Quadruple
	 * Extra Large
	 * 
	 * String cpu = ""; //e.g. 1 EC2 Compute Unit (1 virtual core with 1 EC2
	 * Compute Unit), //26 EC2 Compute Units (8 virtual cores with 3.25 EC2
	 * Compute Units each)
	 * 
	 * Double memory = 0D; //in GB, e.g. 7.5 = 7.5 GB
	 * 
	 * Double localStorage = 0D; //in GB, inclusive 10GB root storage; e.g. 850
	 * = 2 x 420GB + 10 GB root storage
	 * 
	 * Double platform = 0D; //in bit, e.g. 32 bit, 64 bit,32 or 64 bit (both)
	 * 
	 * String inputOutput = ""; //qualitative, e.g. Low, Moderate, High, Very
	 * high
	 * 
	 * Double availability = 0D; //e.g. 0.99 = 99%; 0.9998
	 * 
	 * String zone = ""; //e.g. N Virginia
	 * 
	 * String providing = ""; //on-demand instance, reserved instance or spot
	 * instance; "costPerHour" does differ
	 * 
	 * Double costPerHour = 0D; //e.g. 0.12D
	 */

}
