package kniff;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ScSettings extends Screen
{
	private JComboBox cBoxColor;
	
	public ScSettings() {
		setLayout(null);
		
		this.setName("settings");
		
		JLabel lblNewLabel = new JLabel("Einstellungen");
		lblNewLabel.setFont(new Font("OCR A Extended", Font.PLAIN, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 10, 750, 50);
		add(lblNewLabel);
		
		final JComboBox<ColorScheme> cBoxColor = new JComboBox<ColorScheme>();
		cBoxColor.setModel(new DefaultComboBoxModel<ColorScheme>(ColorScheme.values()));
		cBoxColor.setSelectedIndex(0);
		cBoxColor.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
		cBoxColor.setBounds(290, 151, 200, 35);
		add(cBoxColor);
		
		JLabel lbInfoA = new JLabel("Auswahl des Farbschemas");
		lbInfoA.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
		lbInfoA.setBounds(240, 110, 250, 30);
		add(lbInfoA);
		
		KniffButton btnBack = new KniffButton("zur\u00FCck");
		btnBack.bdt = ButtonDesignType.menuButton;
		btnBack.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				Controller.show(Controller.scStart);
			}
		});
		btnBack.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
		btnBack.setBounds(240, 450, 120, 40);
		add(btnBack);
		
		KniffButton btnAnwenden = new KniffButton("anwenden");
		btnAnwenden.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)
			{
				Design.setColorScheme((ColorScheme)cBoxColor.getSelectedItem());
			}
		});
		btnAnwenden.bdt = ButtonDesignType.menuButton;
		btnAnwenden.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
		btnAnwenden.setBounds(370, 450, 120, 40);
		add(btnAnwenden);
		
		JComboBox cBoxDesign = new JComboBox();
		cBoxDesign.setModel(new DefaultComboBoxModel<Object>(new String[] {"default", "edgy", "triggi"}));
		cBoxDesign.setSelectedIndex(0);
		cBoxDesign.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
		cBoxDesign.setBounds(290, 238, 200, 35);
		add(cBoxDesign);
		
		JLabel lblAuswahlDesDesignschemas = new JLabel("Auswahl des Designschemas");
		lblAuswahlDesDesignschemas.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
		lblAuswahlDesDesignschemas.setBounds(240, 197, 250, 30);
		add(lblAuswahlDesDesignschemas);
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
	}
}
