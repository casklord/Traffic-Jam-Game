import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.DecimalFormat;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WinPanel {
	public WinPanel() {
		
	}
	
	public JPanel makeWinPanel(GameFrame f, int moves, double time) {
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL; 
		c.gridx = 0;
		c.insets = new Insets(5,0,5,0);  //top padding
		c.ipady = 20;
		c.ipadx = 50;
		
		//make menupanel
		JPanel WinScreen = new JPanel();
		WinScreen.setBackground(new Color(200, 200, 160));
		WinScreen.setLayout(gridbag); 
		
		
		DecimalFormat timeRounded = new DecimalFormat("#.00");
		
		JLabel timeText = new JLabel(timeRounded.format(time) + " was your time");
		gridbag.setConstraints(timeText, c);
		WinScreen.add(timeText);
		
		JLabel movesText = new JLabel("You took " + moves + " moves");
		gridbag.setConstraints(timeText, c);
		WinScreen.add(movesText);
		
		JLabel winText = new JLabel("You Win");
		gridbag.setConstraints(winText, c);
		WinScreen.add(winText);
		
		
		JButton levelsButton = new JButton("Level Menu");
		gridbag.setConstraints(levelsButton, c);
		levelsButton.addActionListener(e -> f.loadPanel(f.getLevelPanelObj().makeLevelPanel(f)));
		
		WinScreen.add(levelsButton);	
		
		return WinScreen;
	}
}