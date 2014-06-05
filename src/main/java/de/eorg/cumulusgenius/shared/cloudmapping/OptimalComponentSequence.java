/**
 * 
 */
package de.eorg.cumulusgenius.shared.cloudmapping;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import de.eorg.cumulusgenius.shared.cloudmapping.model.ComponentSequence;
import de.eorg.cumulusgenius.shared.cloudmapping.model.mapping.Component;

/**
 * @author menzel  
 * 
 */
public class OptimalComponentSequence {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		List<Component> comps = new ArrayList<Component>();
		Set<Component> req = new HashSet<Component>();
		Component ds2 = new Component("DS2");
		req.add(ds2);
		comps.add(new Component("DS", req));
		comps.add(ds2);
		comps.add(new Component("MS", req));
		comps.add(new Component("WS", req));

		Set<ComponentSequence> all = getComponentSequences(comps);
		System.out.println(all);

		Set<ComponentSequence> invalid = new HashSet<ComponentSequence>();
		for (ComponentSequence cs : all)
			if(!cs.isValid()) invalid.add(cs);
		
		System.out.println("invalid: " + invalid);
		all.removeAll(invalid);
		System.out.println("valid: " + all);
		
		ComponentSequence bestSeq = all.iterator().next();
		for (ComponentSequence cs : all)
			bestSeq = cs.getValue() > bestSeq.getValue() ? cs : bestSeq;
			
		System.out.println("best = " + bestSeq + ", value = " + bestSeq.getValue());
		
	}

	private static Set<ComponentSequence> getComponentSequences(
			List<Component> comps) {
		Set<ComponentSequence> seqs = new HashSet<ComponentSequence>();

		if (comps.size() > 1)
			for (Component c : comps) {
				List<Component> compsCopy = new ArrayList<Component>(comps);
				compsCopy.remove(c);
				List<Component> compSeq = new ArrayList<Component>();
				compSeq.add(c);
				for (ComponentSequence seq : getComponentSequences(compsCopy)) {
					List<Component> compSeqCopy = new ArrayList<Component>(
							compSeq);
					compSeqCopy.addAll(seq.getSequence());
					seqs.add(new ComponentSequence(compSeqCopy));
				}
			}
		else
			seqs.add(new ComponentSequence(comps));

		return seqs;
	}

}
