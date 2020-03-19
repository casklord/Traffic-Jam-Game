import java.util.ArrayList;
import java.util.concurrent.*;

public class Generator {
	private int[][] grid = new int[6][6];
	private boolean isHor;
	private int length;
	private int start;
	private int otherAxis;
	public Generator() {
		
	}
	
	/**
	 * prints current board
	 */
	private void printGrid() {
		for(int j = 0; j < 36; ++j) {
			System.out.print(grid[(int)j/6][j%6]);
			System.out.print(", ");
			if((j+1)%6 == 0) {
				System.out.println();
			}
		}
	}
	/**
	 * Places a viable car, or returns false
	 * @return whether the current car is viable on the board, determined by basic rules
	 */
	private boolean generateCar() {
		
		//Randomly generate car
		isHor = ThreadLocalRandom.current().nextBoolean();
		length = ThreadLocalRandom.current().nextInt(2, 4);
		start = ThreadLocalRandom.current().nextInt(0, 4-length+3);
		otherAxis = ThreadLocalRandom.current().nextInt(0, 6);
		
		//If too many cars in row/column, return false
		int count = 0;
		for(int c = 0; c < 6; ++c) {
			if(isHor) {
				if(grid[otherAxis][c] > 0) {
					count++;
				}
			} else {
				if(grid[c][otherAxis] > 0) {
					count++;
				}
			}
		}
		if(count+length >= 6) {return false;}
		
		//Attempt to place
		if(isHor) {
			
			//if same row as goal car (not solvable), return false
			if(otherAxis == 2) {
				otherAxis = 3;
			}
			for(int i = start; i < length+start; ++i) {
				//If other car in the way, return false
				if(grid[otherAxis][i] > 0) {
					return false;
				}
			}
		} else {
			if(length == 3) {
				for(int i = 0; i < 6; ++i) {
					if(grid[i][otherAxis] > 0) {
						return false;
					}
				}
			}
			for(int i = start; i < length+start; ++i) {
				//If other car in the way, return false
				if(grid[i][otherAxis] > 0) {
					return false;
				}
			}
		}
		//Tests passed, return true
		return true;
	}
	
	/**
	 * Generates a random board, calling generateCars nCars number of times
	 * @param nCars: number of cars required to generate on board
	 * @return a list of vehicles generated on the board
	 */
	public ArrayList<Vehicle> generate(int nCars){
		grid = new int[6][6];
		ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
		for(int i = 0; i < nCars; ++i) {
			if(i == 0) {
				int randStart = ThreadLocalRandom.current().nextInt(0, 3);
				grid[2][randStart] = 1;
				grid[2][randStart+1] = 1;
				continue;
			}
			int c = 0;
			while(!generateCar()) { 
				if(c > 100) {
					return generate(nCars);
				} 
			}
			for(int j = start; j < start+length; ++j) {
				if(isHor) {
					Vehicle v = new Vehicle(1, j, otherAxis, i);
					vehicles.add(v);
					grid[otherAxis][j] = i+1;
				} else {
					Vehicle v = new Vehicle(1, j, otherAxis, i);
					vehicles.add(v);
					grid[j][otherAxis] = i+1;
				}
			}
			
			//PRINT
			/*printGrid();
			System.out.println("");
			System.out.println("~~~~~");
			System.out.println("");*/
		}
		
		return vehicles;
	}
}