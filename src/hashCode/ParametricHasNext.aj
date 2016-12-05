package hashCode;
import java.util.*;


public aspect ParametricHasNext {
	
	/*
	HashMap<Iterator, VerificationMonitor> iteratorMap = new HashMap<Iterator,
			VerificationMonitor>();

	public Verdict dispatchEvent(String concreteEventName, Iterator it) {

		Verdict v = null;
		if (!this.iteratorMap.containsKey(it)) {
			VerificationMonitor monitor = new VerificationMonitor (it.hashCode());
			iteratorMap.put(it, monitor);
		}
		switch (concreteEventName) {
		case "hasNext":
			v = iteratorMap.get(it).receiveEvent(Event.hasNext);
			break;
		case "next":
			v = iteratorMap.get(it).receiveEvent(Event.next);
			break;
		default: break;
		}
		return v;

	}

	pointcut hasNext(Iterator it): call(boolean java.util.Iterator.hasNext())&&target(it);
	pointcut next(Iterator it): call(Object java.util.Iterator.next()) && target(it);

	before(Iterator it) : hasNext(it) {dispatchEvent("hasNext", it);}
	before(Iterator it) : next(it) {dispatchEvent("next", it);}
	 */
	
	HashMap<Collection<String>, HashSet<Collection<String>>> collectionMap = new HashMap<Collection<String>, HashSet<Collection<String>>>();
	HashMap<Collection<String>, VerificationMonitor> hMapMonitors = new HashMap<Collection<String>, VerificationMonitor>();
	
	
	public Verdict dispatchEvent(String concreteName, Collection<String> c) {
		Verdict v = null;
		if (!this.collectionMap.containsKey(c)) {
			VerificationMonitor monitor = new VerificationMonitor(c.hashCode());
			
		}
		
		
		return v;
	}
	
	
	public Verdict dispatchEventHash(String concreteEventName, HashSet<Collection<String>> hSet, Object c) {
		Collection<String> collec = (Collection<String>) c;
		Verdict v = null;
		
		if (!hMapMonitors.containsKey(c)) {
			hMapMonitors.put(collec, new VerificationMonitor(collec.hashCode()));
			collectionMap.put(collec, hSet);
		}
		switch (concreteEventName) {
		case "add":
			v = hMapMonitors.get(collec).receiveEvent(Event.add_collec);
			//v = iteratorMap.get(it).receiveEvent(Event.hasNext);
			break;
		case "remove":
			//v = iteratorMap.get(it).receiveEvent(Event.next);
			break;
		default: break;
		}
		return v;
		
	}
	
	pointcut add_collec(HashSet hSet, Collection<String> c): call (void java.util.HashSet.add()) && target(hSet) && args(c);
	pointcut remove_collec(HashSet hSet, Collection<String> c): call (void java.util.HashSet.remove()) && target(hSet) && args(c);
	 // arg(hset) -> collection
//	pointcut add(Collection c): call (void java.util.Collection.add()) && target(c);
//	pointcut remove(Collection c): call (void java.util.Collection.remove()) && target(c);
//	
	
	
	before(HashSet hSet, Object c) : add_collec(hSet, c){dispatchEventHash("add_collec", hSet, c );}
	//before(HashSet<Collection<String>> hSet, Collection<String> c) : remove_collec(hSet, c){dispatchEventHash("remove_collec", hSet, c);}
	
//	before(Collection<String> c) : add(arg(c)){dispatchEvent("add", c);}
//	before(Collection<String> c) : remove(arg(c)){dispatchEvent("remove", c);}
//	
}