package project;

/**
 * Config file to hold static global variables 
 * 
 * @project 521_Project
 * @author Alexander Sniffin
 * @date Nov 25, 2015
 */
public final class Config {
	
	/**
	 * Name of the application
	 */
	public static final String NAME = "MDDARA - Medical Document Database Viewer and Automated Risk Analysis Detection";
			
	/**
	 * Database name and IP address
	 */
	public static final String DB = "521Project", IP = "localhost";
	
	/**
	 * Port number
	 */
	public static final int PORT = 1433; //1433
	
	/**
	 * About the application
	 */
	public static final String ABOUT = 
			"MDDARA is a tool used to view and detect anomalies for patient data \n"
			+ "and inform the Doctor or Nurse of the risk. \n"
			+ "Created by Alexander Sniffin, Devin _, William Craft";
			
}
