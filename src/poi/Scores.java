package poi;

import java.awt.BorderLayout;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

@SuppressWarnings("serial")
public class Scores extends JFrame {
	public Scores() {
	}

	private static void createStuff(String left, String right) {
		JDialog dialog = new JDialog();
		dialog.setTitle("Highscores");
		dialog.setSize(220,400);
		dialog.setResizable(false);



		JPanel panel = new JPanel(new BorderLayout());

		panel.add(new JLabel(left),BorderLayout.WEST);
		panel.add(new JLabel(right),BorderLayout.EAST);


		JScrollPane scrollPane = new JScrollPane (panel,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);


		dialog.getContentPane().add(scrollPane);
		dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		dialog.setVisible(true);
	}

	public static void highscoreAll(Map<String, Integer> map){

		String spieler="",punkte="";

		for(Entry<String, Integer> entry : map.entrySet()) {
			punkte=entry.getValue()+"<br>"+punkte;
			spieler=entry.getKey()+"<br>"+spieler;
		}

		punkte = "<html><h1>Score:</h1>"+punkte+"</html>";
		spieler = "<html><h1>Spieler:</h1>"+spieler+"</html>";

		createStuff(punkte, spieler);

	}

	public static void highscorePlayer(List<Integer> scores) {

		String platz="", punkte="";
		int cntr=0;

		for (Integer entry : scores) {
			platz = platz + ++cntr +"<br>";
			punkte = punkte + entry +"<br>";

		}

		punkte = "<html><h1>Score:</h1>"+punkte+"</html>";
		platz = "<html><h1>Ranking:</h1>"+platz+"</html>";

		createStuff(platz, punkte);

	}
}
