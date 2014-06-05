package de.eorg.cumulusgenius.server.db.dao;

import com.googlecode.objectify.ObjectifyOpts;
import com.googlecode.objectify.util.DAOBase;

public class CumulusDAO extends DAOBase {
	static{
/*			ObjectifyService.register(Decision.class);
			ObjectifyService.register(Component.class);
			ObjectifyService.register(VMRequirement.class);
			ObjectifyService.register(CSRequirement.class);
			ObjectifyService.register(VMCriteria.class);
			ObjectifyService.register(CSCriteria.class);
			ObjectifyService.register(Preference.class); */
	}
	
	public CumulusDAO() {
		this(new ObjectifyOpts().setSessionCache(true));
}

	public CumulusDAO(ObjectifyOpts opts) {
		super(opts);
}
	/*
	public Preference getPreference(Long id)
	{
		return id!=null ? ofy().find(Preference.class, id) : null;
	}
	
	public void createPreference(int vmImage, int service, int latency, int performance, int cost)
	{
		Preference preference = new Preference(null, vmImage, service, latency, performance, cost);
		ofy().put(preference);
	}
	
	public CSCriteria getCSCriteria(Long id)
	{
		return id!=null ? ofy().find(CSCriteria.class, id) : null;
	}
	
	public void createCSCriteria(boolean cpu, boolean ram,boolean uptime, boolean popularity, boolean initialLicenceCosts, boolean hourlyLicenceCosts, boolean maxLatency, boolean avgLatency )
	{
		CSCriteria cscriteria = new CSCriteria (null, cpu, ram, uptime, popularity, initialLicenceCosts, hourlyLicenceCosts, maxLatency, avgLatency);
		ofy().put(cscriteria);
	}
	
	public VMCriteria getVMCriteria(Long id)
	{
		return id!=null ? ofy().find(VMCriteria.class, id) : null;
	}
	
	public void createVMCriteria(boolean initialLicenceCosts, boolean hourlyLicenceCosts, boolean popularity, boolean age)
	{
		VMCriteria vmcriteria = new VMCriteria (null, initialLicenceCosts, hourlyLicenceCosts, popularity, age);
		ofy().put(vmcriteria);
	}
	
	public VMRequirement getVMRequirement(Long id)
	{
		return id != null ? ofy().find(VMRequirement.class, id) : null;
	}
	
	public void createVMRequirement(String name, String constraint, String value, String metric)
	{
		VMRequirement vmrequirement = new VMRequirement(null,name,constraint,value,metric);
		ofy().put(vmrequirement);
	}
	
	public CSRequirement getCSRequirement(Long id)
	{
		return id != null ? ofy().find(CSRequirement.class, id) : null;
	}
	
	public void createCSRequirement(String name, String constraint, String value, String metric)
	{
		CSRequirement csrequirement = new CSRequirement(null,name,constraint,value,metric);
		ofy().put(csrequirement);
	}
	
	public Component getComponent(Long id)
	{
		return id != null ? ofy().find(Component.class, id) : null;
	}
	
	public void createComponent(String name, String software, String interconnection)
	{
		Component component = new Component(null,name,software,interconnection);
		//getComponentCounter().increment();
		ofy().put(component);
	}
	
	public Decision getDecision(Long keyId)
	{
		return keyId != null ? ofy().find(Decision.class, keyId) : null;	
	}
	
	public Decision getDecision(Key<Decision> key) {
		return key != null ? ofy().get(key) : null;
	}
	
	public void createDecision(String userId, String name, String description)
	{
		Decision decision = new Decision(null, userId, name, description);
		//getDecisionCounter().increment();
		ofy().put(decision);		
		
	}
	
	public Decision getOrCreateDecision(Long id, String userId, String name, String description)
	{
		Decision found = id != null ? ofy().find(Decision.class, id) : null;
		if (found == null) {
			Decision dec = new Decision(id, userId, name, description);
			//getDecisionCounter().increment();
			ofy().put(dec);
			return dec;
			} else
			return found;
	}
	/**
	public ShardedCounter getDecisionCounter()
	{
		return new ShardedCounter("Decision");
	}
	
	public ShardedCounter getComponentCounter()
	{
		return new ShardedCounter("Component");
	}
	*/
}