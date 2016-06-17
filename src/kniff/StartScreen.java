package kniff;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class StartScreen extends Screen
{
	public DesignerButton btnStart, btnEnd;
	JLabel lbTitle, lbMessage;
	
	public StartScreen()
	{
		this.setBackground(Design.getColor(Colors.bg_dark));
		this.setLayout(null);
		
		// Title-Label
		lbTitle = new JLabel("Kniffelig");
		lbTitle.setBounds(10, 156, 724, 157);
		lbTitle.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 90));
		lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(lbTitle);
		
		// Message-Label
		lbMessage = new JLabel(getRandomMessage());
		lbMessage.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
		lbMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lbMessage.setBounds(10, 629, 724, 71);
		this.add(lbMessage);
		
		// Start-Button
		btnStart = new DesignerButton("Spielen", "gameStartButton");
		btnStart.setFont(new Font("OCR A Extended", Font.BOLD, 20));
		btnStart.setBounds(295, 332, 150, 150);
		btnStart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Controller.Show(Controller.scOption);
			}
		});
		this.add(btnStart);
		
		// End-Button
		btnEnd = new DesignerButton("Beenden", "menuButton");
		btnEnd.setFont(new Font("OCR A Extended", Font.BOLD, 15));
		btnEnd.setBounds(295, 555, 150, 50);
		btnEnd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0);
			}
		});
		this.add(btnEnd);
	}
	
	private String getRandomMessage()
	{
		switch ((int) (Math.random() * 10))
		{
		case 0:
			return "Hallo Du da!";
		case 1:
			return "die W�rfel sind gefallen";
		case 2:
			return "wir sind nicht allein";
		case 3:
			return "gute Arbeit, gutes Spiel";
		case 4:
			return "es wird Zeit f�r ein Spiel";
		case 5:
			return "6er Pasch!!!";
		case 6:
			return "los jetzt!";
		case 7:
			return "Sonnenschein und am PC";
		case 8:
			return "Hier k�nnte Ihre Werbung stehen!";
		case 9:
			return "Es war ein mal ein Algorithmus.";
		default:
			return "[...]";
		}
	}
	
	public void show()
	{
		super.show();
		lbMessage = new JLabel(getRandomMessage());
	}
}
