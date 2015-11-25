package project.connection;


import java.sql.*;

import javax.swing.JOptionPane;

import project.gui.MainWindow;

/**
 * Setup a connection to the SQL Server DBMS
 * 
 * @project Project 1-520
 * @author Alexander Sniffin
 * @date Apr 3, 2015
 */
public class DatabaseCon {

	private boolean connected;
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
	public boolean setup(MainWindow gui) {
		try {
			String connectionUrl = "jdbc:sqlserver://"+ip+":"+port+";" + "databaseName="+dbName+";integratedSecurity=true;";
	        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	        Connection con = DriverManager.getConnection(connectionUrl);
	        connection = con;
	        connected = true;
	        return true;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(gui, 
				"Error connecting to the database, please check connection \n" + 
				"or message an Administrator.", "Error", JOptionPane.ERROR_MESSAGE);
			connected = false;
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

	public boolean isConnected() {
		return connected;
	}

	public void setConnected(boolean connected) {
		this.connected = connected;
	}
}
