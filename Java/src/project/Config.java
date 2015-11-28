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
	
	/**
	 * Static immutable data to hold the IDS to each compound of each of the 4 files,
	 * this data is fixed and won't be needed to change.
	 */
	public static final int[][] COMPOUND_LIST = {
			{0, 1, 3}, //Doc1
			{4, 5, 6}, //Doc2
			{7, 8, 9}, //Doc3
			{10, 11, 12}  //Doc4
	};
			
}
