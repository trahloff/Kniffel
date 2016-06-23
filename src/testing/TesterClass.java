package testing;

import java.io.IOException;
import java.util.Iterator;

import poi.POI;

public class TesterClass {

	@SuppressWarnings("unused")
	private static void testPOI() throws IOException {

		POI.savePlayerScores("Hanssss", 12345);

		Iterator<String> i = POI.getPlayerList().iterator();
		while (i.hasNext()) {
			System.out.println(i.next());
		}

	}

	private static void testSave() throws IOException {

		System.out.println(POI.getAllScores());
		System.out.println(POI.getScoreByPlayer("User4"));

	}

	public static void main(String[] args) throws IOException {

		//		Scores.create(POI.getAllScores());
		testSave();


	}

}
