package project.gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

/**
 * Creates a Login box with a username, password, and login button fields. Should return two Strings: Username and password
 * 
 * @project 521_Project
 * @author Alexander Sniffin
 * @date Nov 25, 2015
 */
public class Login extends JFrame {
	
	public Login(String title, Dimension size) {
		this.setTitle(title);
		this.setBackground(new Color(210, 210, 210));
		this.setPreferredSize(size);
		
		//Create Login username textfield, password textfield and login button
		//When the user clicks the login button, attempt to set up a connection, close the window and change the statusBar.text
		//of MainWindow to "Attempting to connect with the database...", if connection is true we need to enable the database menus
		//if the connection fails we need to JOptionPanel (like the about case statement) to inform the connection failed.
	}
}
