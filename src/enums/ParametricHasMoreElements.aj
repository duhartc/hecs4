package enums;
import java.util.*;


public aspect ParametricHasMoreElements {
	// We need three maps to proceed
	HashMap<Enumeration, VerificationMonitor> enumVerifMap = new HashMap<Enumeration, VerificationMonitor>();
	HashMap<Enumeration, Vector> enumVectors = new HashMap<Enumeration, Vector>();
	HashMap<Vector, VerificationMonitor> vectorVerifMap = new HashMap<Vector, VerificationMonitor>();
	

	public Verdict dispatchEvent(String concreteEventName, Object o) {
		/*
		Verdict v = null;
		if (!this.enumVerifMap.containsKey(o)) {
			VerificationMonitor monitor = new VerificationMonitor (o.hashCode());
			enumVerifMap.put((Enumeration)o, monitor);
		}
		switch (concreteEventName) {
		case "hasMoreElements":
			v = enumVerifMap.get(o).receiveEvent(Event.hasMoreElements);
			break;
		case "nextElement":
			v = enumVerifMap.get(o).receiveEvent(Event.nextElement);
			break;
		default: break;
		}
		return v;*/
		Verdict v = null;
		
		// Unknown vector so far, we need to track it 
		if (!this.vectorVerifMap.containsKey(o)) {
			VerificationMonitor monitor = new VerificationMonitor (o.hashCode());
			vectorVerifMap.put((Vector)o, monitor);
		}
		
		switch(concreteEventName) {
		case "nextElement":
			// Send an event nextElement
			v = enumVerifMap.get(o).receiveEvent(Event.nextElement);
			break;
			
		case "elements":
			//Creation step -> we need to update the map(Enumeration, vector)
			enumVectors.put(((Vector) o).elements(), (Vector)o);
			v = vectorVerifMap.get(o).receiveEvent(Event.elements);
			break;
		case "update":
			// Update
			v = vectorVerifMap.get(o).receiveEvent(Event.update);
			break;
		default: break;
		}
		
		return v;

	}


	pointcut nextElement(Enumeration en): call(Object java.util.Enumeration.nextElement()) && target(en);
	pointcut elements(Vector v): call(Object java.util.Vector.elements()) && target(v);
	pointcut add(Vector v): call (Object java.util.Vector.add()) && target(v);
	pointcut remove(Vector v): call (Object java.util.Vector.remove()) && target(v);


	after(Enumeration en) : nextElement(en) {dispatchEvent("nextElement", en);}
	after(Vector v) : elements(v) {dispatchEvent("elements", v);}
	before(Vector v) : add(v) {dispatchEvent("update", v);};
	before(Vector v) : remove(v) {dispatchEvent("update", v);};
		

}