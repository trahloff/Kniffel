package kniff;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ScOption extends Screen
{
	private JLabel lbTitle = new JLabel("Spieleinstellungen");
	DesignerButton btnStart = new DesignerButton("Start", ButtonDesignType.menuButton);
	DesignerButton btnAdd, btnRmv;

	public ScOption()
	{
		this.setLayout(null);
		this.setName("option");
		
		lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitle.setBounds(10, 11, 724, 100);
		lbTitle.setFont(new Font("Ebrima", Font.PLAIN, 40));
		this.add(lbTitle);
		
		btnStart = new DesignerButton("Starten", ButtonDesignType.menuButton);
		btnStart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ArrayList<Player> players = new ArrayList<Player>();
				Controller.startGame(players);
				Controller.show(Controller.scGame);
			}
		});
		btnStart.setBounds(292, 594, 150, 50);
		this.add(btnStart);
		
		btnAdd = new DesignerButton("Spieler hinzuf\u00FCgen", ButtonDesignType.menuButton);
		btnAdd.setBounds(422, 346, 120, 30);
		this.add(btnAdd);
		
		btnRmv = new DesignerButton("Spieler entfernen", ButtonDesignType.menuButton);
		btnRmv.setBounds(292, 346, 120, 30);
		this.add(btnRmv);
	}

}
