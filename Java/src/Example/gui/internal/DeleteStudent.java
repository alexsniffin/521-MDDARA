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
 * Delete a student and remove them from any registered courses
 * 
 * @project Project 1-520
 * @author Alexander Sniffin
 * @date Apr 3, 2015
 */
public class DeleteStudent extends FrameType {
	
	private JButton submit;
	private JLabel labelSSN;
	private JTextField ssn;

	public DeleteStudent(DatabaseCon connection, String name, int x, int y, int width, int height) {
		super(connection, name, x, y, width, height);
	}

	@Override
	public void createComponants() {
		this.setLayout(new FlowLayout());
		
		labelSSN = new JLabel("Enter a SSN: ");
		
		ssn = new JTextField();
		ssn.setPreferredSize(new Dimension(100, 20));
		
		submit = new JButton("Delete");
		submit.addActionListener(this);
		
		this.add(labelSSN);
		this.add(ssn);

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
			
			if(ssn.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "SSN must have a value in it!", "Error", JOptionPane.ERROR_MESSAGE);
			} else {
				st.executeUpdate("delete from Registered where ssn = '" + ssni + "'");
				
				st.executeUpdate("delete from Student where ssn = '" + ssni + "'");
				JOptionPane.showMessageDialog(this, "Successfully deleted Student " + ssni +" from the Database!", "Success", JOptionPane.INFORMATION_MESSAGE);
				ssn.setText("");
			}
			
			st.close();
		} catch (SQLException e1) {
			sqlError(e1.getMessage());
		}
	}

}
