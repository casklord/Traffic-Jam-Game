import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WinPanel {
	public WinPanel() {
		
	}

	/**
	 *
	 * @param f: frame for the game
	 * @return win screen panel when completing a level
	 */
	public JPanel makeWinPanel(GameFrame f) {
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL; 
		c.gridx = 0;
		c.insets = new Insets(5,0,5,0);  //top padding
		c.ipady = 20;
		c.ipadx = 50;
		
		//make menupanel
		JPanel menupanel = new JPanel();
		Color cl = new Color(100, 150, 200);
		menupanel.setBackground(cl);
		menupanel.setLayout(gridbag); 
		
		JLabel winText = new JLabel("You Win");
		menupanel.add(winText);
		
		JButton levelsButton = new JButton("Level Menu");
		levelsButton.addActionListener(e -> f.loadPanel(f.getLevelPanelObj().makeLevelPanel(f)));
		
		menupanel.add(levelsButton);	
		
		return menupanel;
	}
}
