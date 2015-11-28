package project.gui;

import project.Config;
import project.Driver;
import project.User;
import project.connection.DatabaseCon;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import project.gui.internal.*;

/**
 * Creates the user interface with all of the needed menu options
 * 
 * @project Project 1-520
 * @author Alexander Sniffin
 * @date Apr 3, 2015
 */
public class MainWindow extends JFrame {
	
	/**
	 * Our user
	 */
	private static User user;

	private JPanel body;
	private JDesktopPane desktop;
	private JMenuBar menu;
	private JMenu file, view, help, search, database;
	private JMenuItem exit, about, connect, disconnect, switchUser, importFile, createPatient, createBlankDoc, saveAll, searchU, searchD, searchR;
	private JLabel statusBar;
	
	/**
	 * Initilize the GUI
	 * @param connection 
	 * @param user 
	 * @param title Title of the window
	 * @param size Size of the window
	 */
	public MainWindow(User user, String title, Dimension size) {
		this.user = user;
		this.setTitle(title);
		this.setBackground(new Color(210, 210, 210));
		this.setPreferredSize(size);
		
		this.setJMenuBar(createMenu());
		
		body = new JPanel();
		body.setLayout(new BorderLayout());
		
        desktop = new JDesktopPane();       
		statusBar = new JLabel("Currently in offline mode...");
        statusBar.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        
        body.add(statusBar, BorderLayout.SOUTH);
        body.add(desktop, BorderLayout.CENTER);
        this.add(body);
        
        this.setResizable(true);
		this.setVisible(true);
		this.pack();
		this.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        try {
		        	if (MainWindow.user.getConnection().isConnected())
		        		MainWindow.user.getConnection().close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		        System.exit(0);
		    }
		});
		
		JOptionPane.showMessageDialog(body, 
				"You are currently in offline mode, please connect to the server to access database \n"
				+ "records and have risk detection enabled.", "Attention",
				JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * Sets up the menu and menu options
	 * 
	 * @return menu
	 */
	public JMenuBar createMenu() {
		menu = new JMenuBar();
		file = new JMenu("File");
		view = new JMenu("View");
		help = new JMenu("Help");
		file.setMnemonic(KeyEvent.VK_F);
		view.setMnemonic(KeyEvent.VK_I);
		help.setMnemonic(KeyEvent.VK_H);
		menu.add(file);
		menu.add(view);
		menu.add(help);
		
		/**
		 * Create new menu objects
		 */
		//FILE
		database = new JMenu("Database");
		exit = new JMenuItem("Exit", KeyEvent.VK_X);
		connect = new JMenuItem("Connect to Database", KeyEvent.VK_C);
		disconnect = new JMenuItem("Disconnect from the Database", KeyEvent.VK_D);
		switchUser = new JMenuItem("Switch Account", KeyEvent.VK_S);
		importFile = new JMenuItem("Import Document", KeyEvent.VK_I);
		createPatient = new JMenuItem("Create New Patient", KeyEvent.VK_P);
		createBlankDoc = new JMenuItem("Create Blank Document", KeyEvent.VK_B);
		saveAll = new JMenuItem("Save All Documents", KeyEvent.VK_V);
		
		//VIEW
		search = new JMenu("Search");
		searchU = new JMenuItem("User", KeyEvent.VK_A);
		searchD = new JMenuItem("Document", KeyEvent.VK_A);
		searchR = new JMenuItem("Risk", KeyEvent.VK_A);
		
		//HELP
		about = new JMenuItem("About", KeyEvent.VK_A);
		
		/**
		 * Tool tips
		 */
		//FILE
		database.setToolTipText("Database options");
		exit.setToolTipText("Exit application");
		connect.setToolTipText("Connect to the database");
		disconnect.setToolTipText("Disconnect from the database");
		switchUser.setToolTipText("Switch user account");
		importFile.setToolTipText("Import a local file");
		createPatient.setToolTipText("Create a new patient");
		createBlankDoc.setToolTipText("Create a blank document");
		saveAll.setToolTipText("Save all open documents");
		
		//VIEW
		search.setToolTipText("Search given a specific field");
		searchU.setToolTipText("Search for a specific User");
		searchD.setToolTipText("Search for a specific Document");
		searchR.setToolTipText("Search based on a Risk type");
		
		//HELP
		about.setToolTipText("About this program");
		
		/**
		 * Set action listener commands
		 */
		//FILE
		exit.setActionCommand("exit");
		connect.setActionCommand("connect");
		disconnect.setActionCommand("disconnect");
		switchUser.setActionCommand("switchUser");
		importFile.setActionCommand("importFile");
		createPatient.setActionCommand("createPatient");
		createBlankDoc.setActionCommand("createBlankDoc");
		saveAll.setActionCommand("saveAll");
		
		//VIEW
		searchU.setActionCommand("searchU");
		searchD.setActionCommand("searchD");
		searchR.setActionCommand("searchR");
		
		//HELP
		about.setActionCommand("about");
		
		/**
		 * Create the listener
		 */
		exit.addActionListener(new GuiActions());
		about.addActionListener(new GuiActions());
		connect.addActionListener(new GuiActions());
		disconnect.addActionListener(new GuiActions());
		switchUser.addActionListener(new GuiActions());
		importFile.addActionListener(new GuiActions());
		createPatient.addActionListener(new GuiActions());
		createBlankDoc.addActionListener(new GuiActions());
		saveAll.addActionListener(new GuiActions());
		searchU.addActionListener(new GuiActions());
		searchD.addActionListener(new GuiActions());
		searchR.addActionListener(new GuiActions());
		
		/**
		 * Add to the correct menus
		 */
		file.add(database);
		database.add(connect); //If connected don't display
		database.add(disconnect); //If not connected don't display
		database.add(switchUser); //Display only if logged in
		database.addSeparator();
		database.add(createPatient);
		database.add(createBlankDoc);
		database.add(saveAll); //Display only if logged in
		file.addSeparator();
		file.add(importFile);
		file.addSeparator();
		file.add(exit);
		
		view.add(search);
		search.add(searchU);
		search.add(searchD);
		search.add(searchR);
		
		help.add(about);
		
		menusEnabled(false);
		
		return menu;
	}
	
	/**
	 * Enable or disable the database menus depending if connected or not
	 * 
	 * @param enabled True to enable menus
	 */
	public void menusEnabled(boolean enabled) {
		connect.setEnabled(!enabled);
		createPatient.setEnabled(enabled);
		createBlankDoc.setEnabled(enabled);
		disconnect.setEnabled(enabled);
		switchUser.setEnabled(enabled);
		saveAll.setEnabled(enabled);
		searchU.setEnabled(enabled);
		searchD.setEnabled(enabled);
		searchR.setEnabled(enabled);
	}
	
	/**
	 * Creates a new internal frame
	 * 
	 * @param type Frame class
	 */
    public void createFrame(FrameType type) {
        type.setVisible(true);
        type.setResizable(false);
        desktop.add(type);
        try {
            type.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {
        	e.printStackTrace();
        }
    }
    
	/**
	 * Changes the status bars text
	 * 
	 * @param s String in which to set the status bar too
	 */
	public void statusText(String s) {
		statusBar.setText(s);
	}
	
	public static User getUser() {
		return user;
	}

	public static void setUser(User user) {
		MainWindow.user = user;
	}
	
	/**
	 * Action Listener class
	 * 
	 * @author Alexander
	 */
	private class GuiActions implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			switch (e.getActionCommand()) {
				case "exit":
					try {
						if (user.getConnection().isConnected())
							user.getConnection().close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
			        System.exit(0);
					break;
				
				case "about":
					JOptionPane.showMessageDialog(body, 
							Config.ABOUT, "About",
							JOptionPane.INFORMATION_MESSAGE);
					break;
					
				case "connect":
					//Create a Login GUI box and set up a connection using the Login user, if logged in, enabled menus
					Login login = new Login(MainWindow.this, user, "MDDARA Login Authentication", new Dimension(350, 200));
					
					break;
					
				case "disconnect":
					//Close connection with the database and disable menus
					try {
						user.getConnection().close();
						user.getConnection().setConnected(false);
						user = new User("Local", -1);
						Driver.setUser(user);
						statusText("Currently in offline mode...");
						menusEnabled(false);
						JOptionPane.showMessageDialog(MainWindow.this, 
								"Disconnected succesfully from MDDARA Database.", "Signed out", JOptionPane.INFORMATION_MESSAGE);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					break;
					
				case "switchUser":
					//Close connection with the database and create a Login GUI box
					break;
					
				case "importFile":
					//Load in a file using the file selection API, convert the format to the correct Document View
					break;
					
				case "createPatient":
					createFrame(new CreatePatient(user, "Create New Patient", 10, 10, 360, 520));
					break;
					
				case "createBlankDoc":
					String[] options = new String[] {"Doc1", "Doc2", "Doc3", "Doc4"};
				    int response = JOptionPane.showOptionDialog(MainWindow.this, "Please pick a document format to use.", "Choose a Document",
				        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
				        null, options, options[0]);
				    
					createFrame(new Document(user, "Blank Document", 10, 10, 400, 500, response));
					break;
					
				case "saveAll":
					//Save all open documents
					break;
					
				case "searchU":
					//Search for a user based on name
					break;
					
				case "searchD":
					//Search for a document
					break;
					
				case "searchR":
					//Search for a risk
					break;
			}
		}
	}
}
