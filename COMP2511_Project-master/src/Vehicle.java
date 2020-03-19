import java.util.ArrayList;

import javax.swing.JLabel;

public class Vehicle extends JLabel{
	//unique id
	private int id;
	// 0 Vertical 1 Horizontal
	private int orientation; 

	
	private int startPos;
	private int endPos;
	private int index;

	public Vehicle(int orientation, int startPos, int endPos, int index) {
		this.orientation = orientation;
		this.startPos = startPos;
		this.endPos = endPos;
		this.index = index;
	}

	public int getOrientation() {
		return orientation;
	}

	public void setOrientation(int orientation) {
		this.orientation = orientation;
	}

	public int getStartPos() {
		return startPos;
	}

	public void setStartPos(int startPos) {
		this.startPos = startPos;
	}

	public int getEndPos() {
		return endPos;
	}

	public void setEndPos(int endPos) {
		this.endPos = endPos;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	public int getLength() {
		return (this.endPos - this.startPos);
	}
	
	public void setID(int id) {
		this.id = id;
	}
	
	public int getiD() {
		return this.id;
	}

	/**
	 *
	 * @return if current vehicle is the goal vehicle
	 */
	public boolean isGoalVehicle() {
		if (this.orientation == 1 && index == 2) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		return "Vehicle{" +
				"orientation=" + orientation +
				", startPos=" + startPos +
				", endPos=" + endPos +
				", index=" + index +
				'}';
	}
}
