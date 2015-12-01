package project.gui.internal;

import project.Config;
import project.User;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
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
	
	private int documentType;
	private JTextField patientField;
	private JTextField ssnField;
	private JTextField[] compoundFields;
	private JLabel[] compoundLabels;
	

	public Document(User user, String name, int x, int y, int width, int height, int documentType) {
		super(user, name, x, y, width, height);
		this.documentType = documentType;
        createComponants();
	}

	/**
	 * All GUI stuff goes here
	 */
	@Override
	public void createComponants() {
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
		
		JMenuItem mntmUndo = new JMenuItem("Undo");
		mnView.add(mntmUndo);
		mntmUndo.setActionCommand("undo");
		mntmUndo.addActionListener(this);
		
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
	 */
	public void setDocType(JPanel panel) {
		for (int i = 0; i < Config.COMPOUND_LIST[documentType].length; i++) {
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
					if (patientField.getText().replaceAll("\\s+","").equals("")) {
						sendWarningDialog("Error", "Please enter a patient name.");
						return;
					} else if (ssnField.getText().replaceAll("\\s+","").equals("")) {
						sendWarningDialog("Error", "Please enter a patient name.");
						return;
					}
					
					for (int i = 0; i < compoundFields.length - 1; i++) {
						if (compoundFields[i].getText().replaceAll("\\s+","").equals("")) {
							sendWarningDialog("Error", "Please make sure all compounds have a value.");
							return;
						}
						if (!isNumber(compoundFields[i].getText())) {
							sendWarningDialog("Error", "Please make sure all compounds are numeric.");
							return;
						}
					}
					
					Statement st = null;
					ResultSet rs = null;
					int docID = -1, userID = -1;
					
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
					CallableStatement cstmt =  user.getConnection().getConnection().prepareCall("{call newDoc(?, ?, ?)}");
				    cstmt.registerOutParameter(1, java.sql.Types.INTEGER);
					cstmt.setInt(2, user.getId());
					cstmt.setString(3, ssnField.getText());
				    cstmt.execute();
				    docID = cstmt.getInt(1);
				    cstmt.close();
					
					/* Add all of the new results for our new document */
					for (int i = 0; i < compoundFields.length; i++) {
						st = user.getConnection().getConnection().createStatement();
						st.execute("exec newResult " + docID + ", " + userID + ", " + user.getId() + ", " + Integer.parseInt(compoundFields[i].getText()) + ", '" + compoundLabels[i].getText() + "'");
						st.close();
					}
					break;
				
				case "export":
					break;
				
				case "exit":
					break;
				
				case "undo":
					break;
					
				case "risks":
					break;
			}
		} catch (SQLException e1) {//ignore
			e1.printStackTrace();
		}
	}
}
