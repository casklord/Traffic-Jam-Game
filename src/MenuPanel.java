import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class MenuPanel{
	private JButton button;
	
	public MenuPanel() {
		
	}
	
	public JPanel makeMenuPanel(GameFrame f) {
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL; 
		c.gridx = 0;
		c.insets = new Insets(10,0,10,0);  //top padding
		c.ipady = 20;
		c.ipadx = 50;
		
		//make menupanel
		JPanel menupanel = new JPanel();
		menupanel.setBackground(Color.BLACK);
		menupanel.setLayout(gridbag); 
		
		button = new JButton("Start");
		gridbag.setConstraints(button, c);
		button.addActionListener(e -> f.loadPanel(f.getLevelPanelObj().makeLevelPanel(f)));
		menupanel.add(button);
		//button = new JButton("Stats");
		//gridbag.setConstraints(button, c);
		
		//button.addActionListener(e -> f.loadPanel(f.getStatsPanelObj().makeStatsPanel(f)));
		//menupanel.add(button);
		button = new JButton("Exit");
		gridbag.setConstraints(button, c);
		button.addActionListener(e -> System.exit(0));
		menupanel.add(button);	
		
		return menupanel;
	}
}
