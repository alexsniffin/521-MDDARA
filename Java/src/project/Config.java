package project;

/**
 * Config file to hold static immutable global variables 
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
			+ "and inform the Doctor or Nurse of the risk. \n\n"
			+ "Created by Alexander Sniffin, Devin LastName, William Craft";
	
	/**
	 * Static immutable data to hold the IDS to each compound of each of the 4 files,
	 * this data is fixed when inserted into the database and won't be needed to change.
	 */
	public static final int[][] COMPOUND_LIST = {
			{0, 1, 2, 3, 4, 5, 6, 7}, //Doc1
			{8, 9, 10}, //Doc2
			{11, 12, 13, 14}, //Doc3
			{15, 16, 17, 18, 19, 20}  //Doc4
	};
			
}
