package poi;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;

public class Scores extends JFrame {

	public static void create(){
		String plaetze = "<html><h1>Platz:</h1>";
		String spieler = "<html><h1>Spieler:</h1>";
		
		for (int i = 0; i < 100; i++) {
			plaetze+="<br>stuff";
			spieler+="<br>stuff";
		}
		plaetze += "</html>";
		spieler += "</html>";
		

	    JDialog dialog = new JDialog();
	    dialog.setTitle("Highscores");
	    dialog.setSize(220,400);
	    dialog.setResizable(false);
	
	    

	    JPanel panel = new JPanel(new BorderLayout());

	    panel.add(new JLabel(plaetze),BorderLayout.WEST);
	    panel.add(new JLabel(spieler),BorderLayout.EAST);



	    JScrollPane scrollPane = new JScrollPane (panel, 
	        ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
	        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);


	    dialog.add(scrollPane);

	    dialog.setVisible(true);	
	}


}
