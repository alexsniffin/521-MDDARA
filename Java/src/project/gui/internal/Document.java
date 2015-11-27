package project.gui.internal;

import project.User;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Document View Window
 * 
 * @project 521_Project
 * @author Alexander Sniffin
 * @date Nov 25, 2015
 */
public class Document extends FrameType {

	public Document(User user, String name, int x, int y, int width, int height) {
		super(user, name, x, y, width, height);
	}

	/**
	 * All GUI stuff goes here
	 */
	@Override
	public void createComponants() {
		this.setLayout(null);
		
		//Include menu bar with menu options we decussed
		
		//Include option for what type of document, upto 4 types
		
		//When type if picked, load in Compounds from compounds class that are required.. 
		//Create an array for each documents required compounds so we know what to pull? (or enumeration?)
		
		//Include text fields for inputted values of each compound
		
		//Include a field for the patient name, and ssn (note the ssn is how we find the patient in our database)
		
		//When we save we want to first make sure the patient exists, if we're offline ignore this for saving locaclly
		
	}

	/**
	 * Database stuff goes here
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			Statement st = user.getConnection().getConnection().createStatement();
			
			
			st.close();
		} catch (SQLException e1) {
			//sqlError(e1.getMessage());
		}
	}
}
