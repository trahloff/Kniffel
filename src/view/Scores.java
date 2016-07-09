package view;

import java.awt.BorderLayout;
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

	public static void create(Map<String, Integer> map){

		String spieler="",punkte="";

		for(Entry<String, Integer> entry : map.entrySet()) {
			punkte=entry.getValue()+"<br>"+punkte;
			spieler=entry.getKey()+"<br>"+spieler;
		}

		punkte = "<html><h1>Score:</h1>"+punkte+"</html>";
		spieler = "<html><h1>Spieler:</h1>"+spieler+"</html>";


		JDialog dialog = new JDialog();
		dialog.setTitle("Highscores");
		dialog.setSize(220,400);
		dialog.setResizable(false);



		JPanel panel = new JPanel(new BorderLayout());

		panel.add(new JLabel(punkte),BorderLayout.WEST);
		panel.add(new JLabel(spieler),BorderLayout.EAST);



		JScrollPane scrollPane = new JScrollPane (panel,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);


		dialog.add(scrollPane);
		dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
	}


}
