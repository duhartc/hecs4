package enum2;
import java.util.*;

import enum2.VerificationMonitor;


public aspect ParametricHasMore {
	HashMap<Vector, Enumeration> enumVectors = new HashMap<Vector, Enumeration>();
	HashMap<Enumeration, VerificationMonitor> enumMap = new HashMap<Enumeration, VerificationMonitor>();

	public Verdict dispatchEvent(String concreteEventName, Vector vect, Enumeration en) {

		Verdict v = null;
	
		if (concreteEventName.equals("createEnum") && !this.enumVectors.containsKey(vect)) {
			VerificationMonitor monitor = new VerificationMonitor (en.hashCode());
			if (en != null) {
				enumVectors.put(vect, en);
				enumMap.put(en, monitor);
			}
		}
		Enumeration e = enumVectors.get(vect);
		switch (concreteEventName) {
		case "update":
			if (this.enumVectors.containsKey(vect))
				v = enumMap.get(e).receiveEvent(Event.update);
			break;
		case "createEnum":
			v = enumMap.get(e).receiveEvent(Event.create);
			break;
		default: break;
		}
		return v;

	}
	
	public Verdict dispatchEventEnum(String concreteEventName, Enumeration en) {

		Verdict v = null;
		if (!this.enumMap.containsKey(en)) {
			return null;
		}
		switch (concreteEventName) {
		case "next":
			v = enumMap.get(en).receiveEvent(Event.next); 
			break;
		case "hasMore":
			v = enumMap.get(en).receiveEvent(Event.hasMore);
			break;
		default: break;
		}
		return v;

	}


	pointcut nextElement(Enumeration en): call(Object java.util.Enumeration.nextElement()) && target(en);
	pointcut hasMore(Enumeration en): call(boolean java.util.Enumeration.hasMoreElements()) && target(en);
	pointcut elements(Vector v) : call(Enumeration java.util.Vector.elements()) && target(v) && !within(ParametricHasMore);
	pointcut add(Vector v): call (boolean java.util.Vector.add(Object)) && target(v);
	pointcut remove(Vector v): call (boolean java.util.Vector.remove(Object)) && target(v);


	before(Enumeration en) : nextElement(en) {dispatchEventEnum("next", en);}
	before(Enumeration en) : hasMore(en) {dispatchEventEnum("hasMore", en);}
	after(Vector v)returning(Enumeration e) : elements(v) {dispatchEvent("createEnum", v, e);}
	before(Vector v) : add(v) {dispatchEvent("update", v, null);};
	before(Vector v) : remove(v) {dispatchEvent("update", v, null);};
	

}