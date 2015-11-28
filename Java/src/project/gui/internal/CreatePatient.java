package project.gui.internal;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import project.User;

/**
 * Create a Patient Window
 * 
 * @project 521_Project
 * @author Alexander Sniffin
 * @date Nov 25, 2015
 */
public class CreatePatient extends FrameType {
	
	private ButtonGroup genders, races;
	private JLabel lblName, lblssn, lbladd, lblphone;
	private JLabel lblGender;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblRace;
	private JLabel lblBloodType;
	private JLabel lblInsurance;
	private JTextField textField, ssn, add, phone;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JRadioButton rdbtnMale;
	private JRadioButton rdbtnFemale;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnCaucsian;
	private JRadioButton rdbtnNativeAmerican;
	private JRadioButton rdbtnNewRadioButton_1;
	private JRadioButton rdbtnNewRadioButton_2;
	private JRadioButton rdbtnHispanic;
	private JButton btnCreate;
	private JLabel lblInches;
	private JLabel lblLbs;

	public CreatePatient(User user, String name, int x, int y, int width, int height) {
		super(user, name, x, y, width, height);
	}

	/**
	 * All GUI stuff goes here
	 */
	@Override
	public void createComponants() {
		
		CreatePatient innerPanel = this;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{10, 10, 10, 10, 10, 10};
		gridBagLayout.rowHeights = new int[]{10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, Double.MIN_VALUE};
		innerPanel.getRootPane().setLayout(gridBagLayout);
		
		lblName = new JLabel("Full name: ");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.WEST;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 1;
		gbc_lblName.gridy = 1;
		innerPanel.getRootPane().add(lblName, gbc_lblName);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 3;
		gbc_textField.gridy = 1;
		innerPanel.getRootPane().add(textField, gbc_textField);
		textField.setColumns(10);
		
		//move stuff down grid
		lbladd = new JLabel("Address: ");
		GridBagConstraints gbc_lbladd = new GridBagConstraints();
		gbc_lbladd.anchor = GridBagConstraints.WEST;
		gbc_lbladd.insets = new Insets(0, 0, 5, 5);
		gbc_lbladd.gridx = 1;
		gbc_lbladd.gridy = 2;
		innerPanel.getRootPane().add(lbladd, gbc_lbladd);
		
		add = new JTextField();
		GridBagConstraints gbc_add = new GridBagConstraints();
		gbc_add.insets = new Insets(0, 0, 5, 5);
		gbc_add.fill = GridBagConstraints.HORIZONTAL;
		gbc_add.gridx = 3;
		gbc_add.gridy = 2;
		innerPanel.getRootPane().add(add, gbc_add);
		add.setColumns(10);
		
		lblssn = new JLabel("SSN: ");
		GridBagConstraints gbc_lblssn = new GridBagConstraints();
		gbc_lblssn.anchor = GridBagConstraints.WEST;
		gbc_lblssn.insets = new Insets(0, 0, 5, 5);
		gbc_lblssn.gridx = 1;
		gbc_lblssn.gridy = 3;
		innerPanel.getRootPane().add(lblssn, gbc_lblssn);
		
		ssn = new JTextField();
		GridBagConstraints gbc_ssn = new GridBagConstraints();
		gbc_ssn.insets = new Insets(0, 0, 5, 5);
		gbc_ssn.fill = GridBagConstraints.HORIZONTAL;
		gbc_ssn.gridx = 3;
		gbc_ssn.gridy = 3;
		innerPanel.getRootPane().add(ssn, gbc_ssn);
		ssn.setColumns(10);
		
		lblphone = new JLabel("Phone: ");
		GridBagConstraints gbc_lblphone = new GridBagConstraints();
		gbc_lblphone.anchor = GridBagConstraints.WEST;
		gbc_lblphone.insets = new Insets(0, 0, 5, 5);
		gbc_lblphone.gridx = 1;
		gbc_lblphone.gridy = 4;
		innerPanel.getRootPane().add(lblphone, gbc_lblphone);
		
		phone = new JTextField();
		GridBagConstraints gbc_phone = new GridBagConstraints();
		gbc_phone.insets = new Insets(0, 0, 5, 5);
		gbc_phone.fill = GridBagConstraints.HORIZONTAL;
		gbc_phone.gridx = 3;
		gbc_phone.gridy = 4;
		innerPanel.getRootPane().add(phone, gbc_phone);
		phone.setColumns(10);
		
		lblGender = new JLabel("Gender:");
		GridBagConstraints gbc_lblGender = new GridBagConstraints();
		gbc_lblGender.anchor = GridBagConstraints.WEST;
		gbc_lblGender.insets = new Insets(0, 0, 5, 5);
		gbc_lblGender.gridx = 1;
		gbc_lblGender.gridy = 5;
		innerPanel.getRootPane().add(lblGender, gbc_lblGender);
		
		rdbtnMale = new JRadioButton("Male");
		GridBagConstraints gbc_rdbtnMale = new GridBagConstraints();
		gbc_rdbtnMale.anchor = GridBagConstraints.WEST;
		gbc_rdbtnMale.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnMale.gridx = 3;
		gbc_rdbtnMale.gridy = 5;
		innerPanel.getRootPane().add(rdbtnMale, gbc_rdbtnMale);
		
		rdbtnFemale = new JRadioButton("Female");
		GridBagConstraints gbc_rdbtnFemale = new GridBagConstraints();
		gbc_rdbtnFemale.anchor = GridBagConstraints.WEST;
		gbc_rdbtnFemale.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnFemale.gridx = 3;
		gbc_rdbtnFemale.gridy = 6;
		innerPanel.getRootPane().add(rdbtnFemale, gbc_rdbtnFemale);
		
		lblNewLabel = new JLabel("Height:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 7;
		innerPanel.getRootPane().add(lblNewLabel, gbc_lblNewLabel);
		
		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 3;
		gbc_textField_1.gridy = 7;
		innerPanel.getRootPane().add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		lblInches = new JLabel("inches");
		GridBagConstraints gbc_lblInches = new GridBagConstraints();
		gbc_lblInches.anchor = GridBagConstraints.WEST;
		gbc_lblInches.insets = new Insets(0, 0, 5, 0);
		gbc_lblInches.gridx = 4;
		gbc_lblInches.gridy = 7;
		innerPanel.getRootPane().add(lblInches, gbc_lblInches);
		
		lblNewLabel_1 = new JLabel("Weight:");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 8;
		innerPanel.getRootPane().add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		textField_2 = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 3;
		gbc_textField_2.gridy = 8;
		innerPanel.getRootPane().add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);
		
		lblLbs = new JLabel("lbs");
		GridBagConstraints gbc_lblLbs = new GridBagConstraints();
		gbc_lblLbs.anchor = GridBagConstraints.WEST;
		gbc_lblLbs.insets = new Insets(0, 0, 5, 0);
		gbc_lblLbs.gridx = 4;
		gbc_lblLbs.gridy = 8;
		innerPanel.getRootPane().add(lblLbs, gbc_lblLbs);
		
		lblRace = new JLabel("Race:");
		GridBagConstraints gbc_lblRace = new GridBagConstraints();
		gbc_lblRace.anchor = GridBagConstraints.WEST;
		gbc_lblRace.insets = new Insets(0, 0, 5, 5);
		gbc_lblRace.gridx = 1;
		gbc_lblRace.gridy = 9;
		innerPanel.getRootPane().add(lblRace, gbc_lblRace);
		
		rdbtnNewRadioButton = new JRadioButton("African American");
		GridBagConstraints gbc_rdbtnNewRadioButton = new GridBagConstraints();
		gbc_rdbtnNewRadioButton.anchor = GridBagConstraints.WEST;
		gbc_rdbtnNewRadioButton.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnNewRadioButton.gridx = 3;
		gbc_rdbtnNewRadioButton.gridy = 9;
		innerPanel.getRootPane().add(rdbtnNewRadioButton, gbc_rdbtnNewRadioButton);
		
		rdbtnCaucsian = new JRadioButton("Caucasian");
		GridBagConstraints gbc_rdbtnCaucsian = new GridBagConstraints();
		gbc_rdbtnCaucsian.anchor = GridBagConstraints.WEST;
		gbc_rdbtnCaucsian.insets = new Insets(0, 0, 5, 0);
		gbc_rdbtnCaucsian.gridx = 4;
		gbc_rdbtnCaucsian.gridy = 9;
		innerPanel.getRootPane().add(rdbtnCaucsian, gbc_rdbtnCaucsian);
		
		rdbtnNativeAmerican = new JRadioButton("Native American");
		GridBagConstraints gbc_rdbtnNativeAmerican = new GridBagConstraints();
		gbc_rdbtnNativeAmerican.anchor = GridBagConstraints.WEST;
		gbc_rdbtnNativeAmerican.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnNativeAmerican.gridx = 3;
		gbc_rdbtnNativeAmerican.gridy = 10;
		innerPanel.getRootPane().add(rdbtnNativeAmerican, gbc_rdbtnNativeAmerican);
		
		rdbtnNewRadioButton_1 = new JRadioButton("Asian");
		GridBagConstraints gbc_rdbtnNewRadioButton_1 = new GridBagConstraints();
		gbc_rdbtnNewRadioButton_1.anchor = GridBagConstraints.WEST;
		gbc_rdbtnNewRadioButton_1.insets = new Insets(0, 0, 5, 0);
		gbc_rdbtnNewRadioButton_1.gridx = 4;
		gbc_rdbtnNewRadioButton_1.gridy = 10;
		innerPanel.getRootPane().add(rdbtnNewRadioButton_1, gbc_rdbtnNewRadioButton_1);
		
		rdbtnHispanic = new JRadioButton("Hispanic");
		GridBagConstraints gbc_rdbtnHispanic = new GridBagConstraints();
		gbc_rdbtnHispanic.anchor = GridBagConstraints.WEST;
		gbc_rdbtnHispanic.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnHispanic.gridx = 3;
		gbc_rdbtnHispanic.gridy = 11;
		innerPanel.getRootPane().add(rdbtnHispanic, gbc_rdbtnHispanic);
		
		rdbtnNewRadioButton_2 = new JRadioButton("Other");
		GridBagConstraints gbc_rdbtnNewRadioButton_2 = new GridBagConstraints();
		gbc_rdbtnNewRadioButton_2.anchor = GridBagConstraints.WEST;
		gbc_rdbtnNewRadioButton_2.insets = new Insets(0, 0, 5, 0);
		gbc_rdbtnNewRadioButton_2.gridx = 4;
		gbc_rdbtnNewRadioButton_2.gridy = 11;
		innerPanel.getRootPane().add(rdbtnNewRadioButton_2, gbc_rdbtnNewRadioButton_2);
		
		lblBloodType = new JLabel("Blood Type:");
		GridBagConstraints gbc_lblBloodType = new GridBagConstraints();
		gbc_lblBloodType.anchor = GridBagConstraints.WEST;
		gbc_lblBloodType.insets = new Insets(0, 0, 5, 5);
		gbc_lblBloodType.gridx = 1;
		gbc_lblBloodType.gridy = 12;
		innerPanel.getRootPane().add(lblBloodType, gbc_lblBloodType);
		
		textField_3 = new JTextField();
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.insets = new Insets(0, 0, 5, 5);
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.gridx = 3;
		gbc_textField_3.gridy = 12;
		innerPanel.getRootPane().add(textField_3, gbc_textField_3);
		textField_3.setColumns(10);
		
		lblInsurance = new JLabel("Insurance:");
		GridBagConstraints gbc_lblInsurance = new GridBagConstraints();
		gbc_lblInsurance.insets = new Insets(0, 0, 5, 5);
		gbc_lblInsurance.anchor = GridBagConstraints.WEST;
		gbc_lblInsurance.gridx = 1;
		gbc_lblInsurance.gridy = 13;
		innerPanel.getRootPane().add(lblInsurance, gbc_lblInsurance);
		
		textField_4 = new JTextField();
		GridBagConstraints gbc_textField_4 = new GridBagConstraints();
		gbc_textField_4.insets = new Insets(0, 0, 5, 5);
		gbc_textField_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_4.gridx = 3;
		gbc_textField_4.gridy = 13;
		innerPanel.getRootPane().add(textField_4, gbc_textField_4);
		textField_4.setColumns(13);
		
		btnCreate = new JButton("Create");
		GridBagConstraints gbc_btnCreate = new GridBagConstraints();
		gbc_btnCreate.insets = new Insets(0, 0, 0, 5);
		gbc_btnCreate.gridx = 1;
		gbc_btnCreate.gridy = 15;
		innerPanel.getRootPane().add(btnCreate, gbc_btnCreate);
		
		btnCreate.addActionListener(this);
		
		genders = new ButtonGroup();
		genders.add(rdbtnMale);
		genders.add(rdbtnFemale);
		
		races = new ButtonGroup();
		races.add(rdbtnCaucsian);
		races.add(rdbtnHispanic);
		races.add(rdbtnNativeAmerican);
		races.add(rdbtnNewRadioButton);
		races.add(rdbtnNewRadioButton_1);
		races.add(rdbtnNewRadioButton_2);
	}

	/**
	 * Database stuff does here
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			/*
			 	@name CHAR(32),
				@address CHAR(32),
				@ssn INT,
				@phone INT,
				@dob DATE,
				@prov CHAR(32),
				@gender CHAR(5),
				@height INT,
				@weight INT,
				@race CHAR(16),
				@blood CHAR(3),
				@insur CHAR(16),
				@recent DATE
			 */
			Statement st = user.getConnection().getConnection().createStatement();
			
			String genderSelected = getSelectedRadio(genders);
			String raceSelected = getSelectedRadio(races);
			
			//Check nulls
			//Check integers
			//Check out of bounds
			
			if (textField.getText().replaceAll("\\s+","").equals(""))
				sendWarningDialog("Error", "Please enter a full name.");
			else if (add.getText().replaceAll("\\s+","").equals(""))
				sendWarningDialog("Error", "Please enter an address.");
			else if (ssn.getText().replaceAll("\\s+","").equals(""))
				sendWarningDialog("Error", "Please enter an SSN.");
			else if (phone.getText().replaceAll("\\s+","").equals(""))
				sendWarningDialog("Error", "Please enter a phone number.");
			else if (genderSelected == null)
				sendWarningDialog("Error", "Please select a gender.");
			else if (textField_1.getText().replaceAll("\\s+","").equals(""))
				sendWarningDialog("Error", "Please enter a height.");
			else if (textField_2.getText().replaceAll("\\s+","").equals(""))
				sendWarningDialog("Error", "Please enter a weight.");
			else if (raceSelected == null)
				sendWarningDialog("Error", "Please select a race.");
			else if (textField_3.getText().replaceAll("\\s+","").equals(""))
				sendWarningDialog("Error", "Please enter a blood type.");
			else if (textField_4.getText().replaceAll("\\s+","").equals(""))
				sendWarningDialog("Error", "Please enter an insurance, if none, enter 'None'.");
			else if (!isNumber(ssn.getText()))
				sendWarningDialog("Error", "Please only enter a numeric value for SSN.");
			else if (!isNumber(phone.getText()))
				sendWarningDialog("Error", "Please only enter a numeric value for phone number.");
			else if (!isNumber(textField_1.getText()))
				sendWarningDialog("Error", "Please only enter a numeric value for height.");
			else if (!isNumber(textField_2.getText()))
				sendWarningDialog("Error", "Please only enter a numeric value for weight.");
			else
				st.execute("execute dbo.NewPatient '"+ textField.getText() +"', '"+ add.getText() +"', "+ ssn.getText()+", "+ phone.getText()+", NULL, NULL, "
					+ "'"+ genderSelected +"', "+ textField_1.getText() +", "+ textField_2.getText() +", '"+ raceSelected +"', '"+ textField_3.getText() +"',"
					+ " '"+ textField_4.getText() +"', NULL");
			
			JOptionPane.showMessageDialog(this, 
					"Patient successfully created on the Database.", "Success!", JOptionPane.INFORMATION_MESSAGE);
			
			st.close();
			setVisible(false);
			dispose();
		} catch (SQLException e1) {
			e1.printStackTrace();
			sendWarningDialog("Error", "Unknown error, please check with an Administrator.");
		}
	}

	private String getSelectedRadio(ButtonGroup bGroup) {
		for (Enumeration<AbstractButton> buttons = bGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText();
            }
        }
		return null;
	}
	
	private boolean isNumber(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
