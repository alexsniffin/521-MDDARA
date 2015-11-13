package project;

import java.awt.Dimension;

import project.connection.DatabaseCon;
import project.gui.GUI;

/**
 * Connects to the Database and loads the GUI
 * 
 * @project Project 1-520
 * @author Alexander Sniffin
 * @date Apr 3, 2015
 */
public class Driver {
	
	/**
	 * Database name and IP address
	 */
	public static final String DB = "Project1", IP = "localhost";
	
	/**
	 * Port number
	 */
	public static final int PORT = 1433; //1433

	/**
	 * Setup the database connection and load the GUI
	 * 
	 * @param args command line arguements
	 */
    public static void main(String[] args) {
        try {
        	GUI gui = new GUI("University Registration System", new Dimension(800, 600));
        	
        	DatabaseCon connection = new DatabaseCon(IP, PORT, DB);
        	
        	if (connection.setup(gui)) {
        		gui.statusText("Connected to the database!");
        		gui.setDB(connection);
        		gui.menusEnabled(true);
        	}
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
