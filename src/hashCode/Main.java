package hashCode;
import java.util.ArrayList;
import java.util.*;
import java.util.Iterator;

public class Main {

	public static void wrongPrg() {
		System.out.println("Incorrect program: ");
		
		Set<Collection<String>> s = new HashSet<Collection<String>>();
		Collection<String> c = new ArrayList<String>();
		c.add("this is ok"); /** MODIFY COLLECTION (c) **/
		s.add(c);
		c.add("don’t do this");
		System.out.println(s.contains(c));

	}
	
	public static void correctPrg() {
		System.out.println("Correct program: ");
		
		Set<Collection<String>> s = new HashSet<Collection<String>>();
		Collection<String> c = new ArrayList<String>();
		c.add("this is ok"); /** MODIFY COLLECTION (c) **/
		s.add(c);
		s.remove(c);
		c.add("We modify c, it is allowed");
		s.add(c);
		System.out.println(s.contains(c));
	}
	
	
	
	
	public static void main(String[] args) {
		wrongPrg();
		//correctPrg();
	}

}
