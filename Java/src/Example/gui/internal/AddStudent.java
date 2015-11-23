package project.gui.internal;

import project.connection.DatabaseCon;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * Add a new nonexistant student into the database
 * 
 * @project Project 1-520
 * @author Alexander Sniffin
 * @date Apr 3, 2015
 */
public class AddStudent extends FrameType {
	
	private JButton submit;
	private JLabel labelSSN, labelName, labelAdd, labelMajor;
	private JTextField ssn, name, address, major;

	public AddStudent(DatabaseCon connection, String name, int x, int y, int width, int height) {
		super(connection, name, x, y, width, height);
	}

	@Override
	public void createComponants() {
		this.setLayout(new FlowLayout());
		
		labelSSN = new JLabel("Enter a SSN: ");
		labelName = new JLabel("Enter a name: ");
		labelAdd = new JLabel("Enter a address: ");
		labelMajor = new JLabel("Enter a major: ");
		
		ssn = new JTextField();
		ssn.setPreferredSize(new Dimension(100, 20));
		name = new JTextField();
		name.setPreferredSize(new Dimension(100, 20));
		address = new JTextField();
		address.setPreferredSize(new Dimension(100, 20));
		major = new JTextField();
		major.setPreferredSize(new Dimension(100, 20));
		
		submit = new JButton("Create");
		submit.addActionListener(this);
		
		this.add(labelSSN);
		this.add(ssn);
		
		this.add(labelName);
		this.add(name);
		
		this.add(labelAdd);
		this.add(address);
		
		this.add(labelMajor);
		this.add(major);

		this.add(submit);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			Statement st = connection.getConnection().createStatement();
			
			int ssni = 0;
			try {
				ssni = Integer.parseInt(ssn.getText());
			} catch (Exception q) {
				sqlError("SSN must be a numeric value!");
				return;
			}
				
			if(ssn.getText().equals("") || name.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "SSN and Name must not be empty!", "Error", JOptionPane.ERROR_MESSAGE);
			} else {
				st.executeUpdate("insert into Student values('"+ssni+"', '"+name.getText()+"', '"+address.getText()+"', '"+major.getText()+"')");
				JOptionPane.showMessageDialog(this, "Successfully added " +name.getText()+ " into Database!", "Success", JOptionPane.INFORMATION_MESSAGE);
				ssn.setText("");
				address.setText("");
				name.setText("");
				major.setText("");
			}
			
			st.close();
		} catch (SQLException e1) {
			sqlError(e1.getMessage());
		}
	}

}
