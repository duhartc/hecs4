package enum2;
import java.util.*;

public class Main {

	public static void wrongPrg() {
		System.out.println("Incorrect program: ");
		Vector<Integer> v = new Vector<Integer>();
		v.add(1);
		v.add(2);
		v.add(3);
		Enumeration<Integer> en = v.elements();
		while(en.hasMoreElements()) {
			Integer i = (Integer)en.nextElement();
			if (i == 2) {
				v.add(4); /** ERROR **/
			}
			else {
				System.out.println(i);
			}
		}
	}
	
	
	
	public static void correctPrg() {
		System.out.println("Correct program: ");
		Vector<Integer> v = new Vector<Integer>();
		v.add(1);
		v.add(2);
		v.add(3);
		Enumeration<Integer> en = v.elements();
		while(en.hasMoreElements()) {
			Integer i = (Integer)en.nextElement();
			System.out.println(i);
		}
	}
	
	public static void correctPrgMultipleVectors() {
		System.out.println("Correct program: ");
		Vector<Integer> v = new Vector<Integer>();
		Vector<Integer> v2 = new Vector<Integer>();
		v.add(1);
		v.add(2);
		v.add(3);
		v2.add(4);
		Enumeration<Integer> en = v.elements();
		Enumeration<Integer> en2 = v2.elements();
		while(en.hasMoreElements()) {
			Integer i = (Integer)en.nextElement();
			System.out.println(i);
		}
		while(en2.hasMoreElements()) {
			Integer i = (Integer)en2.nextElement();
			System.out.println(i);
		}
	}
	
	public static void wrongPrgMultipleVectors() {
		System.out.println("Correct program: ");
		Vector<Integer> v = new Vector<Integer>();
		Vector<Integer> v2 = new Vector<Integer>();
		v.add(1);
		v.add(2);
		v.add(3);
		v2.add(4);
		Enumeration<Integer> en = v.elements();
		Enumeration<Integer> en2 = v2.elements();
		while(en.hasMoreElements()) {
			Integer i = (Integer)en.nextElement();
			System.out.println(i);
		}
		v2.add(5); // ERROR
		while(en2.hasMoreElements()) {
			Integer i = (Integer)en2.nextElement();
			System.out.println(i);
		}
	}
	
	
	
	public static void main(String[] args) {
		wrongPrg();
		//correctPrg();
		//correctPrgMultipleVectors();
		//wrongPrgMultipleVectors();
	}

}
