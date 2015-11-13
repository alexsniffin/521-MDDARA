package project.connection;


import java.sql.*;

import javax.swing.JOptionPane;

import project.gui.GUI;

/**
 * Setup a connection to the SQL Server DBMS
 * 
 * @project Project 1-520
 * @author Alexander Sniffin
 * @date Apr 3, 2015
 */
public class DatabaseCon {

	private String ip, dbName;
	private int port;
	private Connection connection;
	
	public DatabaseCon(String ip, int port, String dbName) {
		this.ip = ip;
		this.port = port;
		this.dbName = dbName;
	}
	
	/**
	 * Will try to connect to the DBMS
	 * 
	 * @param gui GUI pointer
	 * @returns true if connection is successful
	 */
	public boolean setup(GUI gui) {
		try {
			String connectionUrl = "jdbc:sqlserver://"+ip+":"+port+";" + "databaseName="+dbName+";integratedSecurity=true;";
	        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	        Connection con = DriverManager.getConnection(connectionUrl);
	        connection = con;
	        return true;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(gui, "Error connecting to the database!", "Error", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		return false;
	}
	
	/**
	 * Closes connection
	 * 
	 * @throws SQLException
	 */
	public void close() throws SQLException {
		connection.close();
	}
	
	/**
	 * Get the connection pointer
	 * 
	 * @return pointer
	 */
	public Connection getConnection() {
		return connection;
	}
}
