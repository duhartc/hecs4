
import java.util.Iterator;
import java.util.Vector;

public class IteT {

	public static void main(String args[]){
		Vector<String> words = new Vector<String>();
		words.add("Antoine"); words.add("de"); words.add("St Exupery");

		Iterator<String> it = words.iterator();
		while(it.hasNext()) {
			String w1 = (String)it.next();
			if (it.hasNext()){
				String w2 = (String)it.next();
				String concatenation = w1 + w2;
				System.out.print(concatenation);
				if (it.hasNext()) {
					System.out.println("there is more!");
				}
			}
		}
	}

}