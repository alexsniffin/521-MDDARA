package project;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	 * How long should a login session be?
	 */
	public static final int SESSION_TIME = 1800000; //30 minutes
	
	/**
	 * Time to hold the start of our session
	 */
	private long startSession;
	
	/**
	 * Pointer to our database connection
	 */
	private DatabaseCon connection;
	
	/**
	 * Username
	 */
	private String username, name, title;

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
	
	public void getUserInfo() {
		try {
			Statement st = connection.getConnection().createStatement();
			ResultSet rs = st.executeQuery("select u.Name, hc.Title, hc.Privileges from Users u, HCProf hc where u.User_ID = "+id+" and u.User_ID = hc.User_ID");
			
			while (rs.next()) {
				name = rs.getString(1).replaceAll("\\s+","");
				title = rs.getString(2).replaceAll("\\s+","");
				userRights = rs.getInt(3);
			}
			System.out.println(name + ", " + title + ", " + id);
			
			rs.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public long getStartSession() {
		return startSession;
	}

	public void setStartSession(long startSession) {
		this.startSession = startSession;
	}
}
