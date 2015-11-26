package project;

import project.connection.DatabaseCon;

/**
 * This is our User Account, depending on the User's privileges will determine what functions we are allowed to do
 * Doctors will have full functionality, while Nurses will have limited functionality, offline will have all offline functionality
 * 
 * @project 521_Project
 * @author Alexander Sniffin
 * @date Nov 25, 2015
 */
public class User {
	
	/**
	 * Pointer to our database connection
	 */
	private DatabaseCon connection;
	
	/**
	 * Username
	 */
	private String username;
	
	/**
	 * Id used for SQL calls
	 */
	private int id;
	
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
		connection = new DatabaseCon(Config.IP, Config.PORT, Config.DB);
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

	public DatabaseCon getConnection() {
		return connection;
	}

	public void setConnection(DatabaseCon connection) {
		this.connection = connection;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
