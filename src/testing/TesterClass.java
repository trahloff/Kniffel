package testing;

import java.io.IOException;
import java.util.Iterator;

import poi.POI;
import poi.Scores;

@SuppressWarnings("unused")
public class TesterClass {

	private static void testPOI() throws IOException {

		POI.savePlayerScores("Hanssss", 12345);

		Iterator<String> i = POI.getPlayerList().iterator();
		while (i.hasNext()) {
			System.out.println(i.next());
		}

	}


	private static void testSave() throws IOException {

		//		for (int i = 0; i < 14; i++) {
		//			POI.savePlayerScores("Hans", (int)(Math.random() * 1000));
		//		}

		//		Scores.highscoreAll(POI.getAllScores());
		Scores.highscorePlayer(POI.getScoreByPlayer("Hans"));
		//		Scores.highscorePlayer(POI.getScoreByPlayer("Haafsdxrsns"));

	}

	public static void main(String[] args) throws IOException {

		testSave();


	}

}
