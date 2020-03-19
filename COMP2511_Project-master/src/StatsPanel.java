import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.*;

public class StatsPanel{
	private JButton button;
	private JTextField textfield;
	private int numLevels;
	String[][] levelstats;

	public StatsPanel() {

	}

	/**
	 *
	 * @param frame for the game
	 * @return stats panel
	 */
	public JPanel makeStatsPanel(GameFrame f) {
		//make stats level
		this.numLevels = 5;
		Scanner sc = null;
		try {
			sc = new Scanner(new File("./levelData/Stats"));    //args[0] is the first command line argument
			sc.useDelimiter("\n");
			while (sc.hasNext()) {
				String line = sc.next();
				this.readLineStats(line);
			}
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} finally {
			if (sc != null) sc.close();
		}

		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.insets = new Insets(5, 0, 5, 0);  //top padding
		c.ipady = 20;
		c.ipadx = 50;

		//scrollpane = new JScrollPane();
		JPanel statspanel = new JPanel();
		statspanel.setBackground(Color.BLACK);
		statspanel.setLayout(gridbag);
		//scrollpane.add(statspanel);

		for (int i = 0; i < this.numLevels; i++) {
			textfield = new JTextField("Level: " + (i + 1) + " Best moves: " + this.levelstats[i][1] + " Best time: " + this.levelstats[i][2]);
			gridbag.setConstraints(textfield, c);
			statspanel.add(textfield);
		}

		button = new JButton("Return");
		gridbag.setConstraints(button, c);
		button.addActionListener(e -> f.loadPanel(f.getMenuPanelObj().makeMenuPanel(f)));
		statspanel.add(button);

		return statspanel;
	}

	public void readLineStats(String line) {
		if (line.equals("") || line.equals("\n")) return;
		Scanner sc = new Scanner(line);
		//System.out.println("Read " + line);
		String command = sc.next();
		if (command.equals("Num")) {
			this.numLevels = Integer.parseInt(sc.next());
			this.levelstats = new String[this.numLevels][3];
		} else {
			int lvnum = Integer.parseInt(command);
			this.levelstats[lvnum - 1][0] = command;
			this.levelstats[lvnum - 1][1] = sc.next();
			this.levelstats[lvnum - 1][2] = sc.next();
		}
		if (sc != null) sc.close();
		return;
	}
}
