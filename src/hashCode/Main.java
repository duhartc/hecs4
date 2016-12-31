package hashCode;
import java.util.ArrayList;
import java.util.*;

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
	
	public static void correctPrg2Sets() {
		System.out.println("Correct program: ");
		
		Set<Collection<String>> s = new HashSet<Collection<String>>();
		Set<Collection<String>> s2 = new HashSet<Collection<String>>();
		Collection<String> c = new ArrayList<String>();
		c.add("this is ok"); /** MODIFY COLLECTION (c) **/
		s.add(c);
		s2.add(c);
		s.remove(c);
		s2.remove(c);
		c.add("We modify c, it is allowed");
		s.add(c);
		System.out.println(s.contains(c));
	}
	
	public static void correctPrg2Collections() {
		System.out.println("Correct program: ");
		
		Set<Collection<String>> s = new HashSet<Collection<String>>();
		Collection<String> c = new ArrayList<String>();
		Collection<String> c2 = new ArrayList<String>();
		c.add("this is ok"); /** MODIFY COLLECTION (c) **/
		c2.add("ok");
		s.add(c);
		s.add(c2);
		s.remove(c);
		s.remove(c2);
		c.add("We modify c, ok");
		c2.add("We modify c2, ok");
	}
	
	public static void wrongPrg2Sets() {
		System.out.println("Wrong program: ");
		
		Set<Collection<String>> s = new HashSet<Collection<String>>();
		Set<Collection<String>> s2 = new HashSet<Collection<String>>();
		Collection<String> c = new ArrayList<String>();
		c.add("this is ok"); /** MODIFY COLLECTION (c) **/
		s.add(c);
		s2.add(c);
		s.remove(c);
		c.add("We modify c, error");
		System.out.println(s.contains(c));
	}
	
	public static void wrongPrg2Collections() {
		System.out.println("Wrong program: ");
		
		Set<Collection<String>> s = new HashSet<Collection<String>>();
		Collection<String> c = new ArrayList<String>();
		Collection<String> c2 = new ArrayList<String>();
		c.add("this is ok"); /** MODIFY COLLECTION (c) **/
		c2.add("ok");
		s.add(c);
		s.add(c2);
		s.remove(c);
		c.add("We modify c, ok");
		c2.add("We modify c2, error");
	}
	
	public static void main(String[] args) {
		//wrongPrg();
		correctPrg();
		//correctPrg2Sets();
		//wrongPrg2Sets();
		//wrongPrg2Collections();
		//correctPrg2Collections();
	}

}
