package project.gui.internal;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.sql.Statement;

import project.User;
import project.connection.DatabaseCon;

/**
 * Search Window
 * 
 * @project 521_Project
 * @author Alexander Sniffin
 * @date Nov 25, 2015
 */
public class Search extends FrameType {

	public Search(User user, String name, int x, int y, int width, int height) {
		super(user, name, x, y, width, height);
	}

	/**
	 * All GUI stuff goes here
	 */
	@Override
	public void createComponants() {
		this.setLayout(new FlowLayout());
		
		
	}

	/**
	 * Database stuff does here
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
