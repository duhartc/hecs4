package hashCode;
import java.util.*;


public aspect ParametricHasNext {	
	HashMap<Collection<String>, HashSet<HashSet<Collection<String>>>> collectionMap = new HashMap<Collection<String>, HashSet<HashSet<Collection<String>>>>();
	HashMap<Collection<String>, VerificationMonitor> collectionMonitors = new HashMap<Collection<String>, VerificationMonitor>();
	
	
	public Verdict dispatchEvent(String concreteEventName, Collection<String> c) {
		Verdict v = null;
		
		if (!this.collectionMap.containsKey(c)) {
			VerificationMonitor monitor = new VerificationMonitor(c.hashCode());
			collectionMonitors.put(c, monitor);
		}
		
		switch (concreteEventName) {
		case "update":
			v = collectionMonitors.get(c).receiveEvent(Event.update);
			break;
		default: break;
		}
		
		return v;
	}
	
	
	public Verdict dispatchEventHash(String concreteEventName, HashSet<Collection<String>> hSet, Object c) {
		Collection<String> collec = (Collection<String>) c;
		Verdict v = null;
		
		switch (concreteEventName) {
		case "addInHash":
			v = collectionMonitors.get(collec).receiveEvent(Event.addInHash);
			collectionMap.get(collec).add(hSet);
			break;
		case "removeFromHash":
			v = collectionMonitors.get(collec).receiveEvent(Event.removeFromHash);
			collectionMap.get(collec).remove(hSet);
			break;
		default: break;
		}
		
		return v;
		
	}
	
	pointcut update_hash_add(HashSet hSet, Collection<String> c): call (void java.util.HashSet.add()) && target(hSet) && args(c);
	pointcut update_hash_remove(HashSet hSet, Collection<String> c): call (void java.util.HashSet.remove()) && target(hSet) && args(c);
	pointcut update_collec_add(Collection c, String s): call (void java.util.Collection.add()) && target(c) && args(s);
	pointcut update_collec_remove(Collection c, String s): call (void java.util.Collection.remove()) && target(c) && args(s);
	
	before(HashSet hSet, Object c) : update_hash_add(hSet, c){dispatchEventHash("addInHash", hSet, c );}
	before(HashSet hSet, Object c) : update_hash_remove(hSet, c){dispatchEventHash("removeFromHash", hSet, c );}
	before(Collection c, String s) : update_collec_add(c, s){dispatchEvent("update", c);}
	before(Collection c, String s) : update_collec_remove(c, s){dispatchEvent("update", c);}
}