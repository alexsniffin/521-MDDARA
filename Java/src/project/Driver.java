package project;

import java.awt.Dimension;

import project.connection.DatabaseCon;
import project.gui.MainWindow;

/**
 * Connects to the Database and loads the GUI
 * 
 * @project Project 1-521
 * @author Alexander Sniffin
 * @date Apr 3, 2015
 */
public class Driver {

	/**
	 * Setup the database connection and load the GUI
	 * 
	 * @param args command line arguements
	 */
    public static void main(String[] args) {
        try {
        	//Set up user
        	User user = new User("Local", -1);
        	
        	//Setup our main gui window and pass user and connection
        	MainWindow gui = new MainWindow(user, Config.NAME, new Dimension(800, 600)); 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
