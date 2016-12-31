package hashCode;
import java.util.*;


public aspect ParametricHashCode {
	HashMap<Collection, LinkedList<HashSet<Collection>>> collectionMap = new HashMap<Collection, LinkedList<HashSet<Collection>>>();
	HashMap<Collection, VerificationMonitor> collectionMonitors = new HashMap<Collection, VerificationMonitor>();
	
	
	public Verdict dispatchEvent(String concreteEventName, Collection c) {
		Verdict v = null;
		
		if (this.collectionMap.containsKey(c) && (!this.collectionMap.get(c).isEmpty())) {
			switch (concreteEventName) {
			case "update":
				v = collectionMonitors.get(c).receiveEvent(Event.update);
				break;
			default: break;
			}
		}
		return v;
	}
	
	
	public Verdict dispatchEventHash(String concreteEventName, Set<Collection> set, Collection collec) {
		HashSet<Collection> hSet = (HashSet<Collection>) set;
		Verdict v = null;
		
		if (!this.collectionMap.containsKey(collec)) {
			VerificationMonitor monitor = new VerificationMonitor(collec.hashCode());
			collectionMonitors.put(collec, monitor);
			collectionMap.put(collec, new LinkedList());
		}
		
		switch (concreteEventName) {
		case "addInHash":
			collectionMap.get(collec).add(hSet);
			v = collectionMonitors.get(collec).receiveEvent(Event.addInHash);
			break;
		case "removeFromHash":
			collectionMap.get(collec).remove(hSet);
			if (collectionMap.get(collec).isEmpty()) 
				v = collectionMonitors.get(collec).receiveEvent(Event.removeFromHash);	
			break;
		default: break;
		}
		
		return v;
		
	}
	
	pointcut update_hash_add(Set set, Collection c): call (boolean java.util.Set.add(Object)) && target(set) && args(c) && !within(ParametricHashCode);
	pointcut update_hash_remove(Set set, Collection c): call (boolean java.util.Set.remove(Object)) && target(set) && args(c) && !within(ParametricHashCode);
	pointcut update_collec_add(Collection c, String s): call (boolean java.util.Collection.add(Object)) && target(c) && args(s);
	pointcut update_collec_remove(Collection c, String s): call (boolean java.util.Collection.remove(Object)) && target(c) && args(s);
	
	after(Set set, Collection c) : update_hash_add(set, c){dispatchEventHash("addInHash", set, c );}
	before(Set set, Collection c) : update_hash_remove(set, c){dispatchEventHash("removeFromHash", set, c );}
	before(Collection c, String s) : update_collec_add(c, s){dispatchEvent("update", c);}
	before(Collection c, String s) : update_collec_remove(c, s){dispatchEvent("update", c);}
}