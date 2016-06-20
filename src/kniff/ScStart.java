package kniff;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.event.ActionEvent;

public class ScStart extends Screen
{
	public KniffButton btnStart, btnEnd;
	JLabel lbTitle, lbMessage;
	
	public ScStart()
	{
		this.setLayout(null);
		
		this.setName("start");
		
		// Title-Label
		lbTitle = new JLabel("Kniffelig");
		lbTitle.setBounds(10, 150, 650, 150);
		lbTitle.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 90));
		lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(lbTitle);
		
		// Message-Label
		lbMessage = new JLabel(getRandomMessage());	
		lbMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lbMessage.setBounds(-20, 708, 724, 71);
		lbMessage.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
		this.add(lbMessage);
		
		// Start-Button
		btnStart = new KniffButton("Spielen");
		btnStart.setFont(Design.getFont());
		btnStart.setBounds(240, 345, 200, 200);
		btnStart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Controller.show(Controller.scOption);
			}
		});
		btnStart.bdt = ButtonDesignType.startButton;
		this.add(btnStart);
		
		// End-Button
		btnEnd = new KniffButton("Beenden");
		btnEnd.setFont(Design.getFont());
		btnEnd.setBounds(250, 630, 180, 50);
		btnEnd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0);
			}
		});
		btnEnd.bdt = ButtonDesignType.menuButton;
		this.add(btnEnd);
		
		KniffButton btnSettings = new KniffButton("Einstellungen");
		btnSettings.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)
			{
				Controller.show(Controller.scSettings);
			}
		});
		btnSettings.setFont(null);
		btnSettings.bdt = ButtonDesignType.menuButton;
		btnSettings.setBounds(250, 570, 180, 50);
		add(btnSettings);
	}
	
	private String getRandomMessage()
	{
		switch ((int) (Math.random() * 10))
		{
		case 0:
			return "Hallo Du da!";
		case 1:
			return "die Würfel sind gefallen";
		case 2:
			return "wir sind nicht allein";
		case 3:
			return "gute Arbeit, gutes Spiel";
		case 4:
			return "es wird Zeit für ein Spiel";
		case 5:
			return "6er Pasch!!!";
		case 6:
			return "los jetzt!";
		case 7:
			return "Sonnenschein und am PC";
		case 8:
			return "Hier könnte Ihre Werbung stehen!";
		case 9:
			return "Es war ein mal ein Algorithmus.";
		default:
			return "[...]";
		}
	}
	
	public void writeMessage(String s)
	{
		lbMessage.setText(s);
	}
	
	public void writeRandomMessage()
	{
		lbMessage.setText(getRandomMessage());
	}
}
