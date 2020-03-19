import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class GameFrame extends JFrame{
	//private static final long serialVersionUID = 1L;
	
	private MenuPanel menuPanelObj;
	private LevelPanel levelPanelObj;
	private StatsPanel statsPanelObj;
	private BoardPanel boardPanelObj;
	private WinPanel winPanelObj;
	
	
	
	ArrayList<Level> levels;
	private JButton button;
	private Container mainContainer;
	//int numlevels;
	//String[][] levelstats;
	//private JTextField textfield;
	
	public GameFrame() {
		
	}

	public GameFrame(ArrayList<Level> levels) {
		mainContainer = getContentPane();
		//mainContainer.setBackground(Color.BLUE);
		
		menuPanelObj = new MenuPanel();
		levelPanelObj = new LevelPanel();
		statsPanelObj = new StatsPanel();
		boardPanelObj = new BoardPanel();
		winPanelObj = new WinPanel();
		
		this.levels = levels;
	}

	/**
	 * Stars the game window
	 */
	public void start() {
		
		
		// this will add menu screen to gui
		add(menuPanelObj.makeMenuPanel(this));
		setTitle("Gridlock");
		setLocationRelativeTo(null); //Place the window at the centre of the screen
		setSize(800, 800);  
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	    
	//https://stackoverflow.com/questions/6811247/drawing-in-jlayeredpane-over-exising-jpanels?rq=1


	//possible implement later
	/*
		JScrollPane scrollPane = new JScrollPane(levelpanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(50, 30, 300, 50);
	 */
	
	public int guiPanelCount() {
		return mainContainer.getComponentCount();
	}
	
	public void guiClearContainer() {
		mainContainer.remove(mainContainer.getComponent(0));
	}
	
	public void loadPanel(JPanel p) {
		if(guiPanelCount() != 0) guiClearContainer();		

		mainContainer.add(p);
		getContentPane().revalidate();
		getContentPane().repaint();
	}
	
	/*public void loadLevel(ActionEvent e) {
		if(mainContainer.getComponentCount() != 0) mainContainer.remove(mainContainer.getComponent(0));
		//TODO
		//Load level
		//The following line of code will return a string in the format "Lv X" where X is the level number
		//it will be useful to make the datafile of the level have th same name
		String levelid = ((JButton) e.getSource()).getText();
		String levelname = "level" + levelid;
		System.out.println(levelname);
		mainContainer.add(boardPanelObj.makeBoardPanel(this, getLevel(levelid)));
		getContentPane().revalidate();
		getContentPane().repaint();
	}*/

	/**
	 *	Searches levels
	 * @param levelid: which level to load
	 * @return: returns the requested level
	 */
	protected Level getLevel(int levelid) {
		for (Level l : levels) {
			if (l.getLevelID() == levelid) return l;
		}
		return null;
	}


	/*private class buttonHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent event) {
			//System.out.println("Hi!");
			//System.out.println(((JButton) event.getSource()).getText());
			//if(((JButton) event.getSource()).getText().equals("Start")) 
			//callLevelMenu();
			System.out.println("Hi!");
		}
		
	}*/
	
	/*private class mainHandlerClass implements MouseListener, MouseMotionListener{
		//pressed and released
		public void mouseClicked(MouseEvent event) {
			statusbar.setText(String.format("Clicked at %d, %d", event.getX(), event.getY()));
		}
		
		public void mousePressed(MouseEvent event) {
			statusbar.setText(String.format("You pressed down at %d,%d", event.getX(), event.getY()));			
		}
		
		public void mouseReleased (MouseEvent event) {
			statusbar.setText(String.format("You released mouse at %d,%d", event.getX(), event.getY()));
		}
		
		public void mouseEntered(MouseEvent event) {
			statusbar.setText(String.format("You entered mouse area"));
			//mousepanel.setBackground(Color.RED);						
		}
		
		public void mouseExited (MouseEvent event) {
			statusbar.setText("The mouse left the window");
			//mainpanel.setBackground(Color.BLACK);
		}
		
		// these are mouse motion event
		public void mouseDragged(MouseEvent event) {
			statusbar.setText(String.format("You be dragging mouse at %d,%d", event.getX(), event.getY()));
		}
		
		public void mouseMoved (MouseEvent event) {
			statusbar.setText(String.format("You moved mouse "));
		}
	}*/


	public MenuPanel getMenuPanelObj() {
		return this.menuPanelObj;
	}
	public LevelPanel getLevelPanelObj() {
		return this.levelPanelObj;
	}
	public StatsPanel getStatsPanelObj() {
		return this.statsPanelObj;
	}
	public BoardPanel getBoardPanelObj() {
		return this.boardPanelObj;
	}
	public WinPanel getWinPanelObj() {
		return this.winPanelObj;
	}
}
