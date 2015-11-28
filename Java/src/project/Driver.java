package project;

import java.awt.Dimension;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import project.connection.DatabaseCon;
import project.gui.MainWindow;

/**
 * The main class for MDDARA, sets up the User object,
 * the GUI object, and starts a new thread to keep track
 * of our session when the user conencts with the database.
 * 
 * @project 521_Project
 * @author Alexander Sniffin
 * @date Nov 28, 2015
 */
public class Driver {
	
	/**
	 * Our User object
	 */
	private static User user;

	/**
	 * Our GUI object
	 */
	private static MainWindow gui;

	/**
	 * Setup the database connection and load the GUI
	 * 
	 * @param args command line arguements
	 */
    public static void main(String[] args) {
        try {
        	//Set up user
        	user = new User("Local", -1);
        	
        	//Setup our main gui window and pass user and connection
        	gui = new MainWindow(user, Config.NAME, new Dimension(800, 600)); 
        	
        	//Set up a new thread to keep track of our session when the user connects with the database
        	new Thread() {
        	    public void run() {
        	        while(true) {
        	        	try {
							Thread.sleep(1000);
							if (user.getConnection().isConnected()) {
								if (user.getStartSession() + User.SESSION_TIME < System.currentTimeMillis()) {
									//Kill the user and disable database functionality till the user logs back in
									
									user.getConnection().close();
									user.getConnection().setConnected(false);
									user = new User("Local", -1);
									MainWindow.setUser(user);//make sure driver and mainwindow have the same reference
									gui.statusText("Currently in offline mode...");
									gui.menusEnabled(false);
									JOptionPane.showMessageDialog(gui, 
											"Disconnected from MDDARA Database because of security timeout, please sign\n"
											+ "back in to enable database funtionality again.", "Signed out", JOptionPane.INFORMATION_MESSAGE);
								}
							}
						} catch (InterruptedException e) {
							e.printStackTrace();
						} catch (SQLException e) {
							e.printStackTrace();
						}
        	        }
        	    }
        	}.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
	public static User getUser() {
		return user;
	}

	public static void setUser(User user) {
		Driver.user = user;
	}
}
