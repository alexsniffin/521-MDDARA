package project.gui.internal;

import project.User;
import project.connection.DatabaseCon;
import project.gui.MainWindow;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

/**
 * Parent class of all InternalFrame classes
 * 
 * @project Project 1-521
 * @author Alexander Sniffin
 * @date Apr 3, 2015
 */
public abstract class FrameType extends JInternalFrame implements ActionListener {
	
	/**
	 * Pointer to user
	 */
	protected User user;
	
	protected Dimension size;

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
	public FrameType(User user, String name, int x, int y, int width, int height) {
        super(name, true, true, true, true);
        
        this.user = user;
        this.setPreferredSize(this.size = new Dimension(width, height));
        this.setBounds(x, y, width, height);
        createComponants();
        this.setVisible(true);
        this.pack();
    }

	/**
	 * Setup the componants of the window
	 */
	public abstract void createComponants();
	
	/**
	 * Send a warning dialog message
	 */
	public void sendWarningDialog(String title, String message) {
		JOptionPane.showMessageDialog(this, 
				message, title, JOptionPane.INFORMATION_MESSAGE);
	}
}
