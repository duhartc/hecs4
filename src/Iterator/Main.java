package Iterator;
import java.util.ArrayList;
import java.util.Iterator;

public class Main {

	public static void wrongPrg() {
		System.out.print("Incorrect program: ");
		ArrayList al = new ArrayList();
	    al.add("C");
	    al.add("D");
	    
	    // Create and miss-use an iterator
	    Iterator itr = al.iterator();
	    String str = (String)itr.next();
	}
	
	
	
	public static void correctPrg() {
		System.out.println("Correct program: ");
		ArrayList al = new ArrayList();
	    al.add("C");
	    al.add("D");
	    al.add("E");
	    
	    // Create and use an iterator
	    Iterator itr = al.iterator();
	    if (itr.hasNext()) {
	    	String str = (String)itr.next();
	    }
	}
	
	
	
	
	public static void main(String[] args) {
		wrongPrg();
		//correctPrg();
	}

}
