import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameManager {
    private static ArrayList<Level> levels = new ArrayList<>();

    // TESTING ONLY
    private static GameFrame frame;

	public static void main(String[] args) {
	    // Instantiate main to call our level parse method
	    GameManager main = new GameManager();

	    // Parse the levels from the levelData directory
        try {
        	System.out.println("in try");
            Files.newDirectoryStream(Paths.get("./levelData"),
                    path -> path.toString().startsWith("." + File.separator +
                            "levelData" + File.separator
                            + "level")).forEach(main::levelParse);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Parse the single lvl data for testing purposes
        //main.levelParse(Paths.get("./levelData/DataLv1"));

	    frame = new GameFrame(levels);
	    frame.setResizable(false);
	    frame.start();
	    //EventQueue.invokeLater(frame::start)
	    
	}

	private void levelParse(Path levelPath) {

        Scanner sc = null;

        try {
            sc = new Scanner(new File(levelPath.toString()));
            //System.out.println("Read " + levelPath.toString());
            int iD = 1;
            // Create a new level
            Level level = new Level();
            // Read input from the scanner here
            while (sc.hasNextLine()) {
                // System.out.println(sc.nextLine());
                String[] command = sc.nextLine().split("#"); // split on # and whitespace
                if (command.length > 0 && command[0].trim().length() != 0) { // Line is not just a comment or empty

                    // Get arguments from command
                    // arguments[0] is the command name
                    String[] arguments = command[0].split("\\s+");

                    switch (arguments[0].toLowerCase()) {

                        case "id":
                            // Set the levelID
                            if (arguments.length == 2) level.setLevelID(Integer.parseInt(arguments[1]));
                            else System.out.println("Usage: ID <levelID>");
                            break;

                        case "vehicle":
                            if (arguments.length == 5) {
                                // Construct a new vehicle
                                int Orientation = Integer.parseInt(arguments[1]);
                                int startPos = Integer.parseInt(arguments[2]);
                                int endPos = Integer.parseInt(arguments[3]);
                                int index = Integer.parseInt(arguments[4]);
                                Vehicle v = new Vehicle(Orientation, startPos, endPos, index);
                                //System.out.println(v);
                                v.setID(iD);
                                iD ++;
                                
                                // Add vehicle to the appropriate list
                                // [1] Horizontal orientation -> rows
                                if (Orientation == 1) level.getRowList().get(index).add(v);
                                else level.getColList().get(index).add(v);

                            } else System.out.println("Usage: Vehicle <Orientation> <startPos> <endPos> <index>");
                            break;

                        default:
                            System.out.println(command[0] + " is not a valid command!");
                    }
                }
            }
            levels.add(level); // Add level to our list
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } finally {
            // Close the scanner
            if (sc != null) sc.close();
        }
	}
}
