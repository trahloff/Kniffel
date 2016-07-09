package testing;

import java.io.IOException;

import poi.POI;
<<<<<<< HEAD
import view.Scores;
=======
>>>>>>> branch 'master' of https://github.com/trahloff/kniffel.git

public class TesterClass {


	private static void testSave() throws IOException {

		POI.checkSave();

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
