package project.gui.internal;

import project.User;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

/**
 * Document View Window
 * 
 * @project 521_Project
 * @author Alexander Sniffin
 * @date Nov 25, 2015
 */
public class Document extends FrameType {
	
	/**
	 * Holds the IDS to each compound of each of the 4 files
	 */
	private int[][] compounds = {
			{0, 1, 3}, //Doc1
			{4, 5, 6}, //Doc2
			{7, 8, 9}, //Doc3
			{10, 11, 12}  //Doc4
	};
	
	private int documentType;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	public Document(User user, String name, int x, int y, int width, int height, int documentType) {
		super(user, name, x, y, width, height);
		this.documentType = documentType;
	}

	/**
	 * All GUI stuff goes here
	 */
	@Override
	public void createComponants() {
		
		//Include menu bar with menu options we decussed
		
		//Include option for what type of document, upto 4 types
		
		//When type if picked, load in Compounds from compounds class that are required.. 
		//Create an array for each documents required compounds so we know what to pull? (or enumeration?)
		
		//Include text fields for inputted values of each compound
		
		//Include a field for the patient name, and ssn (note the ssn is how we find the patient in our database)
		
		//When we save we want to first make sure the patient exists, if we're offline ignore this for saving locaclly
		
		this.setJMenuBar(createMenu());
		
		
		JPanel panel = new JPanel();
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{10, 10, 10, 10, 10, 10};
		gbl_panel.rowHeights = new int[]{10, 10, 10, 10, 10, 10, 10, 10, 10, 10};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblPatientInformation = new JLabel("Patient Information");
		lblPatientInformation.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblPatientInformation = new GridBagConstraints();
		gbc_lblPatientInformation.anchor = GridBagConstraints.WEST;
		gbc_lblPatientInformation.insets = new Insets(0, 0, 5, 5);
		gbc_lblPatientInformation.gridx = 1;
		gbc_lblPatientInformation.gridy = 1;
		panel.add(lblPatientInformation, gbc_lblPatientInformation);
		
		JLabel lblDate = new JLabel("Date...");
		GridBagConstraints gbc_lblDate = new GridBagConstraints();
		gbc_lblDate.anchor = GridBagConstraints.WEST;
		gbc_lblDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblDate.gridx = 3;
		gbc_lblDate.gridy = 1;
		panel.add(lblDate, gbc_lblDate);
		
		JLabel lblPatientName = new JLabel("Patient Name:");
		GridBagConstraints gbc_lblPatientName = new GridBagConstraints();
		gbc_lblPatientName.anchor = GridBagConstraints.WEST;
		gbc_lblPatientName.insets = new Insets(0, 0, 5, 5);
		gbc_lblPatientName.gridx = 1;
		gbc_lblPatientName.gridy = 3;
		panel.add(lblPatientName, gbc_lblPatientName);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridx = 3;
		gbc_textField.gridy = 3;
		panel.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JLabel lblSsn = new JLabel("SSN:");
		GridBagConstraints gbc_lblSsn = new GridBagConstraints();
		gbc_lblSsn.insets = new Insets(0, 0, 5, 5);
		gbc_lblSsn.anchor = GridBagConstraints.WEST;
		gbc_lblSsn.gridx = 1;
		gbc_lblSsn.gridy = 4;
		panel.add(lblSsn, gbc_lblSsn);
		
		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 3;
		gbc_textField_1.gridy = 4;
		panel.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		JLabel lblCompoundInformation = new JLabel("Compound Information");
		lblCompoundInformation.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblCompoundInformation = new GridBagConstraints();
		gbc_lblCompoundInformation.insets = new Insets(0, 0, 5, 5);
		gbc_lblCompoundInformation.gridx = 1;
		gbc_lblCompoundInformation.gridy = 6;
		panel.add(lblCompoundInformation, gbc_lblCompoundInformation);
		
		JLabel lblCompound = new JLabel("Compound1");
		GridBagConstraints gbc_lblCompound = new GridBagConstraints();
		gbc_lblCompound.anchor = GridBagConstraints.WEST;
		gbc_lblCompound.insets = new Insets(0, 0, 0, 5);
		gbc_lblCompound.gridx = 1;
		gbc_lblCompound.gridy = 8;
		panel.add(lblCompound, gbc_lblCompound);
		
		textField_2 = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 0, 5);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 3;
		gbc_textField_2.gridy = 8;
		panel.add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);
		
		setDocType(panel);
		
		this.add(panel);
		
	}
	
	/**
	 * Create menu bar
	 * 
	 * @return
	 */
	public JMenuBar createMenu() {
		JMenuBar menuBar = new JMenuBar();
		
		JMenu mnFile = new JMenu("File");
		mnFile.setMnemonic('F');
		menuBar.add(mnFile);
		
		JMenuItem mntmRefresh = new JMenuItem("Export");
		mnFile.add(mntmRefresh);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mnFile.add(mntmSave);
		
		JSeparator separator = new JSeparator();
		mnFile.add(separator);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);
		
		JMenu mnView = new JMenu("Edit");
		mnView.setMnemonic('E');
		menuBar.add(mnView);
		
		JMenuItem mntmUndo = new JMenuItem("Undo");
		mnView.add(mntmUndo);
		
		JMenuItem mntmShowRisks = new JMenuItem("Show Risks");
		mnView.add(mntmShowRisks);
		
		return menuBar;
	}
	
	/**
	 * Set up the unique document types compounds
	 * 
	 * @param panel
	 */
	public void setDocType(JPanel panel) {
		switch (documentType) {
			case 0:
				//Doc1
				break;
				
			case 1:
				//Doc2
				break;
				
			case 2:
				//Doc3
				break;
				
			case 3:
				//Doc4
				break;
		}
	}

	/**
	 * Database stuff goes here
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			switch (e.getActionCommand()) {
				//Switch through menu options
			}
			//Statement st = user.getConnection().getConnection().createStatement();
			//st.close();
		} catch (SQLException e1) {//ignore
			//sqlError(e1.getMessage());
		}
	}
}
