package project.gui.internal;

import project.connection.DatabaseCon;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

/**
 * Parent class of all InternalFrame classes
 * 
 * @project Project 1-520
 * @author Alexander Sniffin
 * @date Apr 3, 2015
 */
public abstract class FrameType extends JInternalFrame implements ActionListener {
	
	/**
	 * Pointer to database connection
	 */
	protected DatabaseCon connection;

	/**
	 * Sets up the new internal frame
	 * 
	 * @param connection Database connection
	 * @param name Name of the window
	 * @param x x position
	 * @param y y position
	 * @param width witdh of the window
	 * @param height height of the window
	 */
	public FrameType(DatabaseCon connection, String name, int x, int y, int width, int height) {
        super(name, true, true, true, true);
        
        this.connection = connection;
        
        this.setSize(new Dimension(width, height));
        this.setBounds(x, y, width, height);
        createComponants();
    }

	/**
	 * Setup the componants of the window
	 */
	public abstract void createComponants();
	
	/**
	 * Display an SQL error to the user
	 * 
	 * @param s Error string
	 */
	public void sqlError(String s) {
		JOptionPane.showMessageDialog(this, "SQL Error: " + s, "Error", JOptionPane.ERROR_MESSAGE);
	}
	
}
