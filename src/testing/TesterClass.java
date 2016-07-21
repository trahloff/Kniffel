package testing;

import java.io.IOException;

import poi.POI;


public class TesterClass {


	private static void testSave() throws IOException {

		for (int i = 0; i < 14; i++) {
			POI.savePlayerScore("Hans", (int)(Math.random() * 1000));
		}

		POI.highscoreAll();
		POI.highscoreByPlayer("Hans");
		POI.highscoreByPlayer("Joachim");

	}

	public static void main(String[] args) throws IOException {

		testSave();




	}

}
