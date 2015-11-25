package project;

/**
 * This is our User Account, depending on the User's privileges will determine what functions we are allowed to do
 * Doctors will have full functionality, while Nurses will have limited functionality, offline will have all offline functionality
 * 
 * @project 521_Project
 * @author Alexander Sniffin
 * @date Nov 25, 2015
 */
public class User {
	
	private String username;
	
	/**
	 * The logged in users rights
	 * -1: Offline
	 * 0: Nurse
	 * 1: Doctor
	 */
	private int userRights;
	
	public User (String username, int userRights) {
		this.setUsername(username);
		this.setUserRights(userRights);
	}

	public int getUserRights() {
		return userRights;
	}

	public void setUserRights(int userRights) {
		this.userRights = userRights;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
