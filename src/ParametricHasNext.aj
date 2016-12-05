import java.util.HashMap;
import java.util.Iterator;

public aspect ParametricHasNext {
	HashMap<Iterator, VerificationMonitor> iteratorMap = new HashMap<Iterator,VerificationMonitor>();

	public Verdict dispatchEvent(String concreteEventName, Iterator it) {

		Verdict v = null;
		if (!this.iteratorMap.containsKey(it)) {
			VerificationMonitor monitor = new VerificationMonitor(it.hashCode());
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

}