import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class BoardPanel{
	private double startTime;
	private Vehicle car;
	private int[][] gameState;
	
	
	public BoardPanel() {
		gameState = new int[6][6];
		
	}

	/**
	 * Make the board for each level
	 * @param f: The frame for the game
	 * @param level: The selected level
	 * @return panel to be rendered
	 */
	public JPanel makeBoardPanel(GameFrame f, Level level) {
		// Entire game panel
		JPanel gamePanel = new JPanel();
		gamePanel.setLayout(new BorderLayout());
		gamePanel.setOpaque(true);
		gamePanel.setBackground(Color.BLACK);
		
		
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
		
		//Timer
		JLabel timerLabel = new JLabel();
		timerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        timerLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        startTime = (System.currentTimeMillis()/1000.0);
        Timer t = new Timer(100, e -> {
            timerLabel.setText(String.format("Time: %.0f", System.currentTimeMillis()/1000.0 - startTime));
        });
        t.start();
        topPanel.add(timerLabel);
        
        //Add some spacing
        topPanel.add(Box.createRigidArea(new Dimension(100, 0)));
        
        //Number of moves
        JLabel movesLabel = new JLabel("Moves: ");
        movesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        movesLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        topPanel.add(movesLabel);
        
        topPanel.setPreferredSize(new Dimension(800, 50));
        
        //Add the topPanel to the main panel
        gamePanel.add(topPanel, BorderLayout.NORTH);
        
        
        
        //GAME BOARD
        JLayeredPane middlePanel = new JLayeredPane();
        middlePanel.setBackground(Color.DARK_GRAY);
        
        
        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(6,6));
        boardPanel.setBounds(100, 40, 600, 600);
        
        //init cells
        for (int i = 0 ; i < 6*6; i++) {
        	JLayeredPane pane = new javax.swing.JLayeredPane();
        	pane.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
            boardPanel.add(pane, javax.swing.JLayeredPane.DEFAULT_LAYER);
        	
        }
        boardPanel.setPreferredSize(new Dimension(600, 600));
        boardPanel.setMaximumSize(boardPanel.getPreferredSize());
        
        
        middlePanel.add(boardPanel, JLayeredPane.DEFAULT_LAYER);
        
        gameState = fillGameState(level);
        
        //PRINTING VEHICLES ON BOARD
        
        for (ArrayList<Vehicle> vrow: level.getRowList()) {
        	if (!(vrow.isEmpty())){
        		
        		car = vrow.get(0);
        		// horizontal
        		
        		if (car.getLength() == 1) {
        			car.setIcon(new ImageIcon(getClass().getResource("carH.png")));
        			car.setBounds(100+car.getStartPos()*100, 40 + car.getIndex()*100, 200, 100 );
        		}
        		else {
        			car.setIcon(new ImageIcon(getClass().getResource("busH.png")));
        			car.setBounds(100+car.getStartPos()*100, 40 + car.getIndex()*100, 300, 100 );
        		}
        		VehicleController mouse = new VehicleController(gameState, f);
    			car.addMouseListener(mouse);
    			car.addMouseMotionListener(mouse);
        		middlePanel.add(car, JLayeredPane.MODAL_LAYER);
        		
        	}
        	
        }
      
        //VERTICAL
        for (ArrayList<Vehicle> vcol: level.getColList()) {
        	if (!(vcol.isEmpty())) {
        		for (int i = 0; i<vcol.size(); i++) {
        			car = vcol.get(i);
        			if (car.getLength() == 1) { // size 2
        				car.setIcon(new ImageIcon(getClass().getResource("carV.png")));
        				car.setBounds(100+car.getIndex()*100, 40 + car.getStartPos()*100, 100, 200 );
        			}
        			else { // size 3
        				car.setIcon(new ImageIcon(getClass().getResource("busV.png")));
        				car.setBounds(100+car.getIndex()*100, 40 + car.getStartPos()*100, 100, 300 );
        			}
        			VehicleController mouse = new VehicleController(gameState, f);
        			car.addMouseListener(mouse);
        			car.addMouseMotionListener(mouse);
        			middlePanel.add(car, JLayeredPane.MODAL_LAYER);

        		}
        	}
        }
        
        gamePanel.add(middlePanel, BorderLayout.CENTER);
        
        //BOTTOM PANEL
        JPanel bottomPanel = new JPanel();
     	bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
        
        //Reset button
        JButton resetBtn = new JButton("Reset");
        resetBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        resetBtn.setFont(new Font("Arial", Font.PLAIN, 24));
        //timer reset
        resetBtn.addActionListener(new ActionListener() { 
        	public void actionPerformed(java.awt.event.ActionEvent e) { 
        		System.out.println("Clicked reset");
        		t.stop();
        		startTime = (System.currentTimeMillis()/1000.0);
        		t.start();
        		//TODO - Reset the level.
        		f.guiClearContainer();
        		f.loadPanel(makeBoardPanel(f, level));
        		
        		
        	} 
      	});
        bottomPanel.add(resetBtn);

        
        //Main Menu button
        JButton menuBtn = new JButton("Main Menu");
        menuBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        menuBtn.setFont(new Font("Arial", Font.PLAIN, 24));
        bottomPanel.add(menuBtn);
        menuBtn.addActionListener(e -> f.loadPanel(f.getMenuPanelObj().makeMenuPanel(f)));
        
        bottomPanel.setPreferredSize(new Dimension(800, 50));
        
        //Add the bottomPanel to the main panel
        gamePanel.add(bottomPanel, BorderLayout.SOUTH);
        

		return gamePanel;
	}

	/**
	 *
	 * @param level: fills the board with vehicles
	 * @return array representation of the board
	 */
	private int[][] fillGameState(Level level) {
		//horizontal
		int[][] gameState = new int[6][6];
		
		for (ArrayList<Vehicle> vrow: level.getRowList()) {
			
        	if (!(vrow.isEmpty())){
        		for (int i = 0; i<vrow.size(); i++) {
        			car = vrow.get(i);
        			for (int j = 0; j < car.getLength()+1; j++) {
        				gameState[car.getIndex()][car.getStartPos()+j] = car.getiD();
        			}
        		}
        		
        	}
        }

        //VERTICAL
        for (ArrayList<Vehicle> vcol: level.getColList()) {
        	if (!(vcol.isEmpty())) {
        		for (int i = 0; i<vcol.size(); i++) {
        			car = vcol.get(i);
        			for (int j = 0; j < car.getLength()+1; j++) {
        				gameState[car.getStartPos()+j][car.getIndex()] = car.getiD();
        			}
        		}
        	}
        }
        System.out.println(Arrays.deepToString(gameState));
		return gameState;
	}
	
	//TODO - for random levels
		/*
		private int[][] randomFillGameState() {
			Generator gen = new Generator();
			ArrayList<Vehicle> vehicles = gen.generate(11);
			int[][] gameState = new int[6][6];
			for (Vehicle v: vehicles) {
				for (int j = 0; j < v.getLength()+1; j++) {
					if (v.getOrientation() == 1) { //horizontal
						
					} else { //vertical
						
					}
					
					
				}
			
			return gameState;
			
		}*/
	
}