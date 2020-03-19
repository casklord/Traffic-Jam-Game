
public class Coordinate {
	private int xPos;
	private int yPos;
	
	public Coordinate() {
		
	}
	
	public boolean equals(Coordinate c) {
		if (c.xPos == this.xPos && c.yPos == this.yPos) {
			return true;
		} else {
			return false;
		}
	}
	
	public int getXDist(Coordinate c) {
		return this.xPos - c.xPos;
	}
	
	public int getYDist (Coordinate c) {
		return this.yPos - c.yPos;
	}
	
	
	public void setPos(int x, int y) {
		this.xPos = x;
		this.yPos = y;
	}
}
