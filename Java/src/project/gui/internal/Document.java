package project.gui.internal;

import project.Config;
import project.User;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.sql.CallableStatement;
import java.sql.ResultSet;
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
	
	private boolean blank;
	private int documentType, ssn, docID;
	private String username;
	private String[] compoundNames, compoundMeasurements;
	private int[] compoundValues, resultIDs;
	private JTextField patientField;
	private JTextField ssnField;
	private JTextField[] compoundFields;
	private JLabel[] compoundLabels;
	
	/**
	 * Constructor for loading in an existing file from a local file or database
	 * 
	 * @param user
	 * @param name
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param documentType
	 */
	public Document(User user, String name, int x, int y, int width, int height, int docID, int documentType, 
			int ssn, String username, String [] compoundNames, int [] compoundValues, String [] compoundMeasurements) {
		super(user, name, x, y, width, height);
		this.docID = docID;
		this.ssn = ssn;
		this.username = username;
		this.compoundNames = compoundNames;
		this.compoundValues = compoundValues;
		this.compoundMeasurements = compoundMeasurements;
		this.documentType = documentType;
		this.blank = false;
        createComponants(blank);
	}
	
	/**
	 * Constructor for a blank document
	 * 
	 * @param user
	 * @param name
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param documentType
	 */
	public Document(User user, String name, int x, int y, int width, int height, int documentType) {
		super(user, name, x, y, width, height);
		this.documentType = documentType;
		this.blank = true;
        createComponants(blank);
	}

	/**
	 * All GUI stuff goes here
	 */
	public void createComponants(boolean blank) {
		compoundFields = new JTextField[Config.COMPOUND_LIST[documentType].length];
		compoundLabels = new JLabel[Config.COMPOUND_LIST[documentType].length];
		this.setJMenuBar(createMenu());
		JPanel panel = new JPanel();
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{10, 10, 10, 10, 10, 10, 10};
		int rowHeights[] = new int[10 + Config.COMPOUND_LIST[documentType].length];
		double[] rowWeights = new double[10 + Config.COMPOUND_LIST[documentType].length];
		for (int i = 0; i < rowHeights.length; i++) {
			rowHeights[i] = 10;
			rowWeights[i] = 0.0;
		}
		rowWeights[10 + Config.COMPOUND_LIST[documentType].length - 1] = Double.MIN_VALUE;
		gbl_panel.rowHeights = rowHeights;
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = rowWeights;
		panel.setLayout(gbl_panel);
		
		JLabel lblPatientInformation = new JLabel("Patient Information");
		lblPatientInformation.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblPatientInformation = new GridBagConstraints();
		gbc_lblPatientInformation.anchor = GridBagConstraints.WEST;
		gbc_lblPatientInformation.insets = new Insets(0, 0, 5, 5);
		gbc_lblPatientInformation.gridx = 1;
		gbc_lblPatientInformation.gridy = 1;
		panel.add(lblPatientInformation, gbc_lblPatientInformation);
		
		JLabel lblDate = new JLabel("Date: 11/"+ (((int) Math.random() * 30) + 1) + "/ 2015");
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
		
		patientField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridx = 3;
		gbc_textField.gridy = 3;
		panel.add(patientField, gbc_textField);
		patientField.setColumns(10);
		
		JLabel lblSsn = new JLabel("SSN:");
		GridBagConstraints gbc_lblSsn = new GridBagConstraints();
		gbc_lblSsn.insets = new Insets(0, 0, 5, 5);
		gbc_lblSsn.anchor = GridBagConstraints.WEST;
		gbc_lblSsn.gridx = 1;
		gbc_lblSsn.gridy = 4;
		panel.add(lblSsn, gbc_lblSsn);
		
		ssnField = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 3;
		gbc_textField_1.gridy = 4;
		panel.add(ssnField, gbc_textField_1);
		ssnField.setColumns(10);
		
		JLabel lblCompoundInformation = new JLabel("Compound Information");
		lblCompoundInformation.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblCompoundInformation = new GridBagConstraints();
		gbc_lblCompoundInformation.insets = new Insets(0, 0, 5, 5);
		gbc_lblCompoundInformation.gridx = 1;
		gbc_lblCompoundInformation.gridy = 6;
		panel.add(lblCompoundInformation, gbc_lblCompoundInformation);
		
		setDocType(panel, blank);
		
		if (!blank) {
			patientField.setText(username);
			ssnField.setText("" + ssn);
			patientField.setEditable(false);
			ssnField.setEditable(false);
		}
		
		
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
		
		JMenuItem mntmExport = new JMenuItem("Export Document");
		mnFile.add(mntmExport);
		mntmExport.setActionCommand("export");
		mntmExport.addActionListener(this);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mnFile.add(mntmSave);
		mntmSave.setActionCommand("save");
		mntmSave.addActionListener(this);
		
		JSeparator separator = new JSeparator();
		mnFile.add(separator);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);
		mntmExit.setActionCommand("exit");
		mntmExit.addActionListener(this);
		
		JMenu mnView = new JMenu("Edit");
		mnView.setMnemonic('E');
		menuBar.add(mnView);
		
		JMenuItem mntmShowRisks = new JMenuItem("Show Risks");
		mnView.add(mntmShowRisks);
		mntmShowRisks.setActionCommand("risks");
		mntmShowRisks.addActionListener(this);
		
		return menuBar;
	}
	
	/**
	 * Set up the unique document types compounds
	 * 
	 * @param panel
	 * @param blank 
	 */
	public void setDocType(JPanel panel, boolean blank) {
		for (int i = 0; i < Config.COMPOUND_LIST[documentType].length; i++) {
			if (blank) {
				try {
					Statement st = user.getConnection().getConnection().createStatement();
					ResultSet rs = st.executeQuery("select CompoundName, MeasurementType from Compound where Com_ID = " + Config.COMPOUND_LIST[documentType][i]);
					while (rs.next()) {
						createCompoundGUI(panel, rs.getString(1), rs.getString(2), 9 + i, i);
					}
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else {
				createCompoundGUI(panel, compoundNames[i], compoundMeasurements[i], 9 + i, i);
				compoundFields[i].setText("" + compoundValues[i]);
			}
		}
	}
	
	public void createCompoundGUI(JPanel panel, String name, String measurement, int y, int i) {
		compoundLabels[i] = new JLabel(name);
		GridBagConstraints gbc_lblCompound = new GridBagConstraints();
		gbc_lblCompound.anchor = GridBagConstraints.WEST;
		gbc_lblCompound.insets = new Insets(0, 0, 5, 5);
		gbc_lblCompound.gridx = 1;
		gbc_lblCompound.gridy = y;
		panel.add(compoundLabels[i], gbc_lblCompound);
		
		compoundFields[i] = new JTextField();
		GridBagConstraints gbc_compoundFields = new GridBagConstraints();
		gbc_compoundFields.insets = new Insets(0, 0, 5, 5);
		gbc_compoundFields.fill = GridBagConstraints.HORIZONTAL;
		gbc_compoundFields.gridx = 3;
		gbc_compoundFields.gridy = y;
		panel.add(compoundFields[i], gbc_compoundFields);
		compoundFields[i].setColumns(10);
		
		JLabel lblMeasurement = new JLabel(measurement);
		GridBagConstraints gbc_lblMeasurement = new GridBagConstraints();
		gbc_lblMeasurement.anchor = GridBagConstraints.WEST;
		gbc_lblMeasurement.insets = new Insets(0, 0, 5, 5);
		gbc_lblMeasurement.gridx = 4;
		gbc_lblMeasurement.gridy = y;
		panel.add(lblMeasurement, gbc_lblMeasurement);
	}

	/**
	 * Action listoner stuff goes here
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			switch (e.getActionCommand()) {
				case "save":
					if (user.getConnection().isConnected()) {
						Statement st = null;
						ResultSet rs = null;
						
						for (int i = 0; i < compoundFields.length - 1; i++) {
							if (compoundFields[i] != null) {
							if (compoundFields[i].getText().replaceAll("\\s+","").equals("")) {
								sendWarningDialog("Error", "Please make sure all compounds have a value.");
								return;
							}
							if (!isNumber(compoundFields[i].getText())) {
								sendWarningDialog("Error", "Please make sure all compounds are numeric.");
								return;
							}
							}
						}
						
						if (blank) {
							if (patientField.getText().replaceAll("\\s+","").equals("")) {
								sendWarningDialog("Error", "Please enter a patient name.");
								return;
							} else if (ssnField.getText().replaceAll("\\s+","").equals("")) {
								sendWarningDialog("Error", "Please enter a patient name.");
								return;
							}
							
							docID = -1;
							int userID = -1;
							
							/* Get the User_ID using the SSN */
							st = user.getConnection().getConnection().createStatement();
							rs = st.executeQuery("select User_ID from Users where SSN = " + ssnField.getText());
							while (rs.next()) {
								userID = rs.getInt(1);
							}
							rs.close();
							st.close();
							
							if (userID == -1) {
								sendWarningDialog("Error", "Please make sure the SSN value is correct, or the patient exists in the database.");
								return;
							}
							
							/* Create the new doc and get the docID */
							CallableStatement cstmt =  user.getConnection().getConnection().prepareCall("{call newDoc(?, ?, ?, ?)}");
						    cstmt.registerOutParameter(1, java.sql.Types.INTEGER);
							cstmt.setInt(2, user.getId());
							cstmt.setString(3, ssnField.getText());
							cstmt.setInt(4, documentType);
						    cstmt.execute();
						    docID = cstmt.getInt(1);
						    cstmt.close();
							
							/* Add all of the new results for our new document */
							for (int i = 0; i < compoundFields.length; i++) {
								st = user.getConnection().getConnection().createStatement();
								st.execute("exec newResult " + docID + ", " + userID + ", " + user.getId() + ", " + Integer.parseInt(compoundFields[i].getText()) + ", '" + compoundLabels[i].getText() + "'");
								st.close();
							}
						} else {
							/* Get all of the result ID's */
							int[] resultIDSet = new int[Config.COMPOUND_LIST[documentType].length];
							st = user.getConnection().getConnection().createStatement();
							rs = st.executeQuery("select Result_ID from Results where Document_ID = " + docID + "");
							int i = 0;
							while (rs.next()) {
								resultIDSet[i++] = rs.getInt(1);
							}
							rs.close();
							st.close();
							
							for (int j = 0; j < compoundFields.length; j++) {
								st = user.getConnection().getConnection().createStatement();
								st.execute("exec saveResult " + resultIDSet[j] + ", " + Integer.parseInt(compoundFields[j].getText()));
								st.close();
							}
						}
					} else {
						sendWarningDialog("Error", "Please connect with the Database to save this file. \n"
								+ "To save locally, use the export option.");
					}
					break;
				
				case "export":
					
					break;
				
				case "exit":
					setVisible(false);
					dispose();
					break;
					
				case "risks":
					//<html><span bgcolor=\"yellow\">This is the label text</span></html>
					int[] comids = new int[50];
					int[] severity = new int[50];
					
					Statement st = null;
					ResultSet rs = null;
					
					//return compundid and severity 
					
					st = user.getConnection().getConnection().createStatement();
					rs = st.executeQuery("select Com_ID, Severity from Risks where Document_ID = " + docID + " and Doctor_ID = " + user.getId());
					int i = 0;
					while (rs.next()) {
						comids[i] = rs.getInt(1);
						
						severity[i] = rs.getInt(2);//System.out.println(comids[i] + ", " + severity[i]);
					}
					rs.close();
					st.close();
					
					String[] comNames = new String[50];
					for (int j = 0; j < comids.length; j++) {
						st = user.getConnection().getConnection().createStatement();
						rs = st.executeQuery("select CompoundName from Compound where Com_ID = " + comids[j]);
						while (rs.next()) {
							comNames[j] = rs.getString(1);
							//System.out.println(comNames[j]);
						}
						rs.close();
						st.close();
					}
					
					for (int k = 0; k < compoundLabels.length; k++) {
						for (int q = 0; q < comNames.length; q++) {
							//System.out.println(compoundLabels[k].getText() + ", " + comNames[q]);
							if (compoundLabels[k].getText().equals(comNames[q])) {
								compoundLabels[k].setText("<html><span bgcolor=\"yellow\">"+compoundLabels[k].getText() +"</span></html>");
							}
						}
					}
					break;
			}
		} catch (SQLException e1) {//ignore
			e1.printStackTrace();
		}
	}

	@Override
	public void createComponants() {
		// TODO Auto-generated method stub
		
	}
}
