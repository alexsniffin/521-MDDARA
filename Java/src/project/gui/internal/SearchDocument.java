//************************************************ 
//*Program: 521_Project							 *
//*Programmer: Devin Clement					 *
//*Date: November 28, 2015		   				 *
//*Description:	Search for a document		     *
//************************************************

package project.gui.internal;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

import javax.swing.*;

import project.User;
import project.gui.MainWindow;

public class SearchDocument extends FrameType {

	protected ArrayList<Integer>docID;
	protected ArrayList<JButton> buttonArray;

	//declared variables
	private JLabel documentLabel, ssnLabel, displayLabel;
	private JRadioButton doc1Button, doc2Button, doc3Button, doc4Button;
	private JTextField ssnTextField;
	private JButton searchButton;
	private ButtonGroup doc;
	int count = 0;
	
	private MainWindow mainGUI;

	//constructor
	public SearchDocument(User user, String name, int x, int y, int width,
			int height, MainWindow mainGUI) 
	{
		super(user, name, x, y, width, height);
		this.mainGUI = mainGUI;
		createComponants();
		
	}//end SearchRiskCompound

	public void createComponants() 
	{
		//creates page layout
		SearchDocument innerPanel = this;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{10, 10, 10, 10, 10, 10};
		gridBagLayout.rowHeights = new int[]{10, 10, 10, 10, 10, 10, 10, 10, 10, 10};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		innerPanel.getContentPane().setLayout(gridBagLayout);

		//creates description label
		ssnLabel = new JLabel("Enter patient's SSN:");
		GridBagConstraints gbc_ssnLabel = new GridBagConstraints();
		gbc_ssnLabel.anchor = GridBagConstraints.WEST;
		gbc_ssnLabel.insets = new Insets(0, 0, 5, 5);
		gbc_ssnLabel.gridx = 1;
		gbc_ssnLabel.gridy = 2;
		innerPanel.getContentPane().add(ssnLabel, gbc_ssnLabel);

		//creates user input field
		ssnTextField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 3;
		gbc_textField.gridy = 2;
		innerPanel.getContentPane().add(ssnTextField, gbc_textField);
		ssnTextField.setColumns(10);

		documentLabel = new JLabel("Enter Document Type:");
		GridBagConstraints gbc_documentLabel = new GridBagConstraints();
		gbc_documentLabel.insets = new Insets(0, 0, 5, 5);
		gbc_documentLabel.gridx = 1;
		gbc_documentLabel.gridy = 4;
		innerPanel.getContentPane().add(documentLabel, gbc_documentLabel);

		doc1Button = new JRadioButton("Document #1");
		GridBagConstraints gbc_doc1Button = new GridBagConstraints();
		gbc_doc1Button.insets = new Insets(0, 0, 5, 5);
		gbc_doc1Button.gridx = 3;
		gbc_doc1Button.gridy = 4;
		innerPanel.getContentPane().add(doc1Button, gbc_doc1Button);
		
		doc2Button = new JRadioButton("Document #2");
		GridBagConstraints gbc_doc2Button = new GridBagConstraints();
		gbc_doc2Button.insets = new Insets(0, 0, 5, 0);
		gbc_doc2Button.gridx = 4;
		gbc_doc2Button.gridy = 4;
		innerPanel.getContentPane().add(doc2Button, gbc_doc2Button);
		
		doc3Button = new JRadioButton("Document #3");
		GridBagConstraints gbc_doc3Button = new GridBagConstraints();
		gbc_doc3Button.insets = new Insets(0, 0, 5, 5);
		gbc_doc3Button.gridx = 3;
		gbc_doc3Button.gridy = 5;
		innerPanel.getContentPane().add(doc3Button, gbc_doc3Button);
		
		doc4Button = new JRadioButton("Document #4");
		GridBagConstraints gbc_doc4Button = new GridBagConstraints();
		gbc_doc4Button.insets = new Insets(0, 0, 5, 0);
		gbc_doc4Button.gridx = 4;
		gbc_doc4Button.gridy = 5;
		innerPanel.getContentPane().add(doc4Button, gbc_doc4Button);
		
		searchButton = new JButton("Search");
		GridBagConstraints gbc_searchButton = new GridBagConstraints();
		gbc_searchButton.insets = new Insets(0, 0, 5, 5);
		gbc_searchButton.gridx = 1;
		innerPanel.getContentPane().add(searchButton, gbc_searchButton);
		searchButton.addActionListener(this);
		
		//add radio buttons into range group
		doc = new ButtonGroup();
		doc.add(doc1Button);
		doc.add(doc2Button);
		doc.add(doc3Button);
		doc.add(doc4Button);

		//Area to display text returned
		//creates display label
		displayLabel = new JLabel();
		GridBagConstraints gbc_displayLabel = new GridBagConstraints();
		gbc_displayLabel.insets = new Insets(0, 0, 5, 5);
		gbc_displayLabel.gridx = 1;
		gbc_displayLabel.gridy = 6;
		displayLabel.setVisible(false);
		innerPanel.getRootPane().add(displayLabel, gbc_displayLabel);


	}//end createComponants

	public void actionPerformed(ActionEvent arg0) 
	{

		//text user inputs
		String ssn = ssnTextField.getText();

		//selects string based on user input
		String documentSelected = getSelectedRadio(doc);
		int documentType = 0;

		try
		{
			//create statement
			Statement st = user.getConnection().getConnection().createStatement();

			//displays error message if box is left empty.
			if (ssnTextField.getText().replaceAll("\\s+","").equals(""))
			{
				sendWarningDialog("Error", "Please enter patien'ts SSN.");
			}//end if

			else
			{



				if (documentSelected.equals("Document #1"))
					documentType = 0;
				if (documentSelected.equalsIgnoreCase("Document #2"))
					documentType = 1;
				if (documentSelected.equalsIgnoreCase("Document #3"))
					documentType = 2;
				if (documentSelected.equalsIgnoreCase("Document #4"))
					documentType = 3;
				
				docID = new ArrayList<Integer>();
				buttonArray = new ArrayList<JButton>();
				

				ResultSet rs = st.executeQuery("select u.Name, d.Document_ID"
						+ " from Documents d, Users u"
						+ " where d.Patient_ID = u.User_ID AND u.SSN = " +ssn+ " AND d.DocumentType = " +documentType+ "");

				while(rs.next())
				{
					
					rs.getString (1);
					docID.add(rs.getInt(2));
					buttonArray.add(new JButton ("Open Document"));
					count++;
					
				}//end while
				
				
				
				System.out.println(docID.toString());
				
				rs.close();
				st.close();	
				
				mainGUI.createFrame(new LoadSearchDocument(user, "Search Results", 10, 10, 285, 215, this));
			}//end else

		}//end try

		catch (SQLException e1) 
		{
			//catches error
			e1.printStackTrace();
			sendWarningDialog("Error", "Unknown error. Please contact database administrator.");
		}//end catch

	}//end actionPerformed

	private String getSelectedRadio(ButtonGroup bGroup) {
		for (Enumeration<AbstractButton> buttons = bGroup.getElements(); buttons.hasMoreElements();) {
			AbstractButton button = buttons.nextElement();

			if (button.isSelected()) {
				return button.getText();
			}
		}
		return null;
	}

	public ArrayList<Integer> getDocID() {
		return docID;
	}

	public void setDocID(ArrayList<Integer> docID) {
		this.docID = docID;
	}

	public ArrayList<JButton> getButtonArray() {
		return buttonArray;
	}

	public void setButtonArray(ArrayList<JButton> buttonArray) {
		this.buttonArray = buttonArray;
	}
	
	public MainWindow getMainGUI() {
		return mainGUI;
	}

	public void setMainGUI(MainWindow mainGUI) {
		this.mainGUI = mainGUI;
	}
}