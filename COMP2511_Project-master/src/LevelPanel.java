import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class LevelPanel extends GameFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton button;
	
	public LevelPanel() {
		
	}

	/**
	 * Makes a panel from the level object
	 * @param f: frame for the game
	 * @return the panel for the level
	 */
	public JPanel makeLevelPanel(GameFrame f) {
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL; 
		c.gridx = 0;
		c.insets = new Insets(5,0,5,0);  //top padding
		c.ipady = 20;
		c.ipadx = 50;

		//make levelpanel
		JPanel levelPanel = new JPanel();
		levelPanel.setBackground(Color.BLACK);
		levelPanel.setLayout(gridbag); 

		for(int i = 0; i < 10; i ++) {
			button = new JButton("Lv" + (i + 1));
			gridbag.setConstraints(button, c);
			button.addActionListener(e -> {
                int levelNumber = Integer.parseInt(e.getActionCommand().substring(2)); //Remove the first 2 letters ("Lv")
                System.out.println("Level "+levelNumber+" selected");
                f.loadPanel(f.getBoardPanelObj().makeBoardPanel(f, f.getLevel(levelNumber)));
            });
			levelPanel.add(button);
		}	
		button = new JButton("Return");//
		gridbag.setConstraints(button, c);
		button.addActionListener(e -> f.loadPanel(f.getMenuPanelObj().makeMenuPanel(f)));
		levelPanel.add(button);	

		return levelPanel;
		
		
	}
}
