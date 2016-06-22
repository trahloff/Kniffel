package testing;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

import poi.*;

public class TesterClass {
	
	private static void testPOI() throws IOException {
			


		
		POI.savePlayerScores("Hanssss", 12345);
		
		Iterator<String> i = POI.getPlayerList().iterator();
		while (i.hasNext()) {
			System.out.println(i.next());
		}
		
	}
	

private static void testSave() throws IOException {

System.out.println(POI.getAllScores());
	
}
	
	
	
	
	
	
	
	public static void main(String[] args) throws IOException {

		testSave();




	}

}
