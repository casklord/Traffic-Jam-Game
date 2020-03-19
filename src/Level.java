import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Level {
	private int levelID;
	private ArrayList<ArrayList<Vehicle>> rowList = new ArrayList<>(6);
	private ArrayList<ArrayList<Vehicle>> colList = new ArrayList<>(6);
    boolean completed = false;
    int moves = 0;

	public Level () {
	    // Make space for the row/col lists
        for (int index = 0; index < 6; index++) {
            this.rowList.add(new ArrayList<>());
            this.colList.add(new ArrayList<>());
        }
	}

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public int getMoves() {
        return moves;
    }

    public void setMoves(int moves) {
        this.moves = moves;
    }

    public int getLevelID() {
        return levelID;
    }

    public void setLevelID(int levelID) {
        this.levelID = levelID;
    }

    public ArrayList<ArrayList<Vehicle>> getRowList() {
        return rowList;
    }

    public void setRowList(ArrayList<ArrayList<Vehicle>> rowList) {
        this.rowList = rowList;
    }

    public ArrayList<ArrayList<Vehicle>> getColList() {
        return colList;
    }

    public void setColList(ArrayList<ArrayList<Vehicle>> colList) {
        this.colList = colList;
    }

    @Override
    public String toString() {
        return "Level{" +
                "levelID=" + levelID +
                ", rowList=" + rowList +
                ", colList=" + colList +
                ", completed=" + completed +
                ", moves=" + moves +
                '}';
    }
}
