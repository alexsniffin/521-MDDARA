//************************************************ 
//*Program: 521_Project							 *
//*Programmer: Devin Clement					 *
//*Date: November 28, 2015		   				 *
//*Description:	Search for a user by name	     *
//************************************************

package project.gui.internal;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import project.User;

public class SearchUser extends FrameType 
{

	//declared variables
	private JLabel nameLabel;
	private JTextArea textArea;
	private JTextField nameTextField;
	private JButton searchButton;

	//constructor
	public SearchUser(User user, String name, int x, int y, int width,
			int height) 
	{
		super(user, name, x, y, width, height);
		createComponants();

	}//end SearchRiskCompound

	public void createComponants() 
	{
		//creates page layout
		SearchUser innerPanel = this;
		FlowLayout flowLayout = new FlowLayout();
		innerPanel.getRootPane().setLayout(flowLayout);

		//creates description label
		nameLabel = new JLabel ("Enter patient's social security number: ");
		innerPanel.getRootPane().add(nameLabel);
		
		//creates user input field
		nameTextField = new JTextField();
		innerPanel.getRootPane().add(nameTextField);
		nameTextField.setColumns(10);

		//creates search button
		searchButton = new JButton("Search");
		innerPanel.getRootPane().add(searchButton);
		searchButton.addActionListener(this);

		//Area to display text returned
		textArea = new JTextArea();
		innerPanel.getRootPane().add(textArea);
		textArea.setColumns(30);
		textArea.setVisible(false);
		
	}//end createComponants

	public void actionPerformed(ActionEvent arg0) 
	{

		//text user inputs
		String patientName = nameTextField.getText();

		try
		{
			//create statement
			Statement st = user.getConnection().getConnection().createStatement();

			//displays error message if box is left empty.
			if (nameTextField.getText().replaceAll("\\s+","").equals(""))
			{
				sendWarningDialog("Error", "Please enter patient's full name.");
			}//end if

			else
			{
				textArea.setVisible(true);
				ResultSet rs = st.executeQuery("select *"
						+ " from Users u, Patients p "
						+ " where p.User_ID = u.User_ID AND u.SSN = ('" + patientName+ "') ");

				String  userID, name, address, phone, dob, ssn, gender, height, weight, race, bloodType, insurance, recentVisit;

				while (rs.next())
				{
					userID = rs.getString(1);
					name=rs.getString(2);
					address=rs.getString(3);
					ssn=rs.getString(4);
					phone=rs.getString(5);
					dob=rs.getString(6);
					gender=rs.getString(8);
					height=rs.getString(9);
					weight=rs.getString(10);
					race=rs.getString(11);
					bloodType=rs.getString(12);
					insurance=rs.getString(13);
					recentVisit=rs.getString(14);

					//Displays text into text box
					textArea.setText ("   Name:                 " + name + "\n" +
							"   Patient's ID:        " + userID+ "\n" +
							"   SSN:                    " + ssn + "\n" +
							"   Address:             " + address + "\n" +
							"   Phone:                " + phone + "\n" +
							"   Date of birth:      " + dob + "\n" +
							"   Gender:              " + gender + "\n" +
							"   Height:                " + height + "\n" +
							"   Weight:               " + weight + "\n" +
							"   Race:                  " + race + "\n" +
							"   Blood Type:        " + bloodType + "\n" +
							"   Insurance:         " + insurance + "\n" +
							"   Recent visit:      " + recentVisit + "\n" );

				}//end while


				rs.close();
				st.close();
			}//end else

		}//end try

		catch (SQLException e1) 
		{
			//catches error
			e1.printStackTrace();
			sendWarningDialog("Error", "Patient does not exist in the database.");
		}//end catch

	}//end actionPerformed




}//end class