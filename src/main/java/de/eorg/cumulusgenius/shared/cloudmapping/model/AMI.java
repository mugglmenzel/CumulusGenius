package de.eorg.cumulusgenius.shared.cloudmapping.model;

import de.eorg.cumulusgenius.shared.cloudmapping.model.mapping.Appliance;
import de.eorg.cumulusgenius.shared.cloudmapping.model.mapping.Attribute;
import de.eorg.cumulusgenius.shared.cloudmapping.model.mapping.IAttributedItem;

import java.util.Map;

public class AMI extends Appliance {

    /**
     *
     */
    private static final long serialVersionUID = 7548443317591314554L;

    public AMI(String name) {
        super(name);
    }

    public AMI(String name, Map<String, Attribute<?>> attributes) {
        super(name, attributes);
    }


    @Override
    public int compareTo(IAttributedItem o) {
        return (o instanceof AMI) ? getName().compareTo(((AMI) o).getName())
                : ((o.getAttributes() != null && o.getAttributes() != null) ? getAttributes()
                .size() - o.getAttributes().size()
                : (getAttributes() != null ? 1 : -1));
    }

	/*
     * String us_East_AMI_Id = ""; // e.g. ami-82ea18eb String europe_AMI_ID =
	 * ""; String us_West_AMI_ID = ""; String ap_Southeast_AMI_ID = ""; String
	 * ap_Northeast_AMI_ID = "";
	 * 
	 * 
	 * String webserverImpl = ""; //e.g. Apache, IIS
	 * 
	 * String amiManifest = ""; // e.g.
	 * 979382823631/bitnami-tracks-1.7.3-0-windows-2008.r1.sp2-ebs
	 * 
	 * String licence = ""; //e.g. public, paid
	 * 
	 * String opSys = ""; //e.g. Microsoft Windows, Unix...
	 * 
	 * String submittedBy = ""; //e.g. amazon-catalog@bitrock.com; Lance Larson
	 * (user could also submit AMIs
	 * 
	 * Date createdOn; //e.g. February 2, 2011 12:56 AM GMT
	 * 
	 * Date lastUpdate; //
	 * 
	 * String backedOn = ""; //EBS or S3;
	 */
}
