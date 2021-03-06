import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

public class VehicleController extends MouseAdapter {
	private Coordinate mouseStartPos;
	private int startX;
	private int startY;
	private int[][] gameState;
	private GameFrame frame;
	private BoardPanel b;
	
	public VehicleController() {
		
	}
	
	public VehicleController(int[][] gameState, GameFrame f, BoardPanel b) {
		mouseStartPos = new Coordinate();
		this.gameState = gameState;
		this.frame = f;
		this.b = b;
	}

	public void mousePressed(MouseEvent event) {
		mouseStartPos.setPos(event.getX(), event.getY());
		
		Vehicle selectedCar = (Vehicle) event.getSource();
		startX = (int)selectedCar.getBounds().getX();
		startY = (int)selectedCar.getBounds().getY();
	}
	
	public void mouseReleased (MouseEvent event) {
		//TODO if the move is invalid, return set the position of the vehicle back to mouseStartPos
		
		// If it is valid, use the getXDist function if it is a horizontal vehicle, or getYDist if it is a vertical vehicle
		// A positive value indicates moving down/right. Negative means up/left.
		
		//Logic for snap-to-grid
		Coordinate temp = new Coordinate();
		temp.setPos(event.getX(), event.getY());
		
		Vehicle selectedCar = (Vehicle) event.getSource();
		int x = (int)selectedCar.getBounds().getX();
		int y = (int)selectedCar.getBounds().getY();
		int h = (int)selectedCar.getBounds().getHeight();
		int w = (int)selectedCar.getBounds().getWidth();
		
		if (selectedCar.getOrientation() == 1) { //horizontal
			x = (int)(Math.round(x/100.0)*100);
		} else { //vertical
			y -= 40;
			y = (int)(Math.round(y/100.0)*100);
			y += 40;
		}
		
		selectedCar.setBounds(new Rectangle(x, y, w, h));
		b.incrementNumMoves();
	}	
	
	public void mouseMoved (MouseEvent event) {
		//Do nothing. Needs to be declared for the MouseMotionListener.
	}
	
	public void mouseDragged(MouseEvent event) {
		
		Coordinate temp = new Coordinate();
		temp.setPos(event.getX(), event.getY());
		
		Vehicle selectedCar = (Vehicle) event.getSource();
		int x = (int)selectedCar.getBounds().getX();
		int y = (int)selectedCar.getBounds().getY();
		int h = (int)selectedCar.getBounds().getHeight();
		int w = (int)selectedCar.getBounds().getWidth();
		
		//Move the vehicle
		if (selectedCar.getOrientation() == 1) { //horizontal
			x += temp.getXDist(mouseStartPos);
		} else { //vertical
			y += temp.getYDist(mouseStartPos);
		}
		
		//Stop the vehicle from going out of bounds
		int boardLength = 600;
		int xOffset = 100; 
		int yOffset = 40;
		if (selectedCar.getOrientation() == 1) { //horizontal
			if (x > boardLength + xOffset - w) {
				x = boardLength + xOffset - w;
			} else if (x < xOffset) {
				x = xOffset;
			} 
		} else { //vertical
			if (y < yOffset) {
				y = yOffset;
			} else if (y > boardLength + yOffset - h) {
				y = boardLength + yOffset - h;
			}
		}

		int lowerBound;
		int upperBound;
		int carLength = selectedCar.getLength();
		//Stop the vehicle from colliding
		if (selectedCar.getOrientation() == 1) {
			int rowNum = selectedCar.getIndex();
			int i = 0;
			//Find the lower bound
			while (true) {
				if (gameState[rowNum][selectedCar.getStartPos() - i] == 0 || 
						gameState[rowNum][selectedCar.getStartPos()  - i] == selectedCar.getID()) {
					i++;
					if (i > selectedCar.getStartPos()) {
						i--;
						break;
					}
				} else {
					i--;
					break;
				}
			}
			lowerBound = selectedCar.getStartPos() - i;
			System.out.println("lowerbound = " + lowerBound);
			
			//Find the upper bound
			i = 0;
			while (true) {
				if (gameState[rowNum][selectedCar.getStartPos() + carLength +  i] == 0 || 
						gameState[rowNum][selectedCar.getStartPos() + carLength + i] == selectedCar.getID()) {
					i++;
					if (selectedCar.getStartPos() + carLength + i > 5) {
						i--;
						break;
					}
				} else {
					i--;
					break;
				}
			}
			upperBound = selectedCar.getStartPos() + carLength + i;
			System.out.println("upperbound = " + upperBound);
			System.out.println("x = " + x);
			
			if (x > xOffset + (upperBound+1)*100 - w) {
				x = xOffset + (upperBound+1)*100 - w;
			} else if (x < xOffset + (lowerBound)*100) {
				x = xOffset + lowerBound * 100;
			} 
			
			
		} else {
			int colNum = selectedCar.getIndex();
			int i = 0;
			//Find the lower bound
			while (true) {
				if (gameState[selectedCar.getStartPos() - i][colNum] == 0 || 
						gameState[selectedCar.getStartPos() - i][colNum] == selectedCar.getID()) {
					i++;
					if (selectedCar.getStartPos() - i < 0) {
						i--;
						break;
					}
				} else {
					i--;
					break;
				}
			}
			lowerBound = selectedCar.getStartPos() - i;
			System.out.println("lowerbound = " + lowerBound);
			
			//Find the upper bound
			i = 0;
			while (true) {
				if (gameState[selectedCar.getStartPos() + carLength +  i][colNum] == 0 || 
						gameState[selectedCar.getStartPos() + carLength +  i][colNum] == selectedCar.getID()) {
					i++;
					if (selectedCar.getStartPos() + carLength + i > 5) {
						i--;
						break;
					}
				} else {
					i--;
					break;
				}
			}
			upperBound = selectedCar.getStartPos() + carLength + i;
			System.out.println("upperbound = " + upperBound);
			
			if (y > yOffset + (upperBound+1)*100 - h) {
				y = yOffset + (upperBound+1)*100 - h;
			} else if (y < yOffset + (lowerBound)*100) {
				y = yOffset + (lowerBound)*100;
			} 
			
			
		}
		
		//Update the position of the vehicle in gameState
		for (int j = 0; j < 6; j++) {
			for (int k = 0; k < 6; k++) {
				if (gameState[j][k] == selectedCar.getID()) {
					gameState[j][k] = 0;
				}
			}
		}
		
		int xCoord = (x - xOffset)/100;
		int yCoord = (y - yOffset)/100;
		
		
		System.out.println("xCoord = " + xCoord + " yCoord = " + yCoord);
		System.out.println(Arrays.deepToString(gameState));
		
		for (int j = 0; j < selectedCar.getLength() + 1; j++) {
			if (selectedCar.getOrientation() == 1) { //horizontal
				gameState[yCoord][xCoord+j] = selectedCar.getID();
			} else { //vertical
				gameState[yCoord+j][xCoord] = selectedCar.getID();
			}
			
		}
		
		//Check if the goal car has reached the end
		if (selectedCar.isGoalVehicle()) {
			if (x == xOffset + 400) {
				frame.loadPanel(frame.getWinPanelObj().makeWinPanel(frame, b.getMoves(), (System.currentTimeMillis()/1000.0) - b.getStartTime()));
				System.out.println("you win!");
			}
		}
		
		selectedCar.setBounds(new Rectangle(x, y, w, h));
	}	
}
