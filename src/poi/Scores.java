package poi;

import java.awt.BorderLayout;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;




@SuppressWarnings("serial")
public class Scores extends JFrame {

	public static void create(TreeMap<Integer, String> scores){
		
		String punkte = "<html><h1>score:</h1>";
		String spieler = "<html><h1>Spieler:</h1>";
				
		for(Entry<Integer, String> entry : scores.entrySet()) {
			punkte+="<br>"+entry.getKey();
			spieler+="<br>"+entry.getValue();
			}
		
		punkte += "</html>";
		spieler += "</html>";
		

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

	    dialog.setVisible(true);	
	}


}
