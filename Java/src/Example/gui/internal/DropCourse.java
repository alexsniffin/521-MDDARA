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
 * Drop a course for a registered student
 * 
 * @project Project 1-520
 * @author Alexander Sniffin
 * @date Apr 3, 2015
 */
public class DropCourse extends FrameType {
	
	private JButton submit;
	private JLabel labelSSN, labelC, labelYr, labelSemester;
	private JTextField ssn, code, year, semester;

	public DropCourse(DatabaseCon connection, String name, int x, int y, int width, int height) {
		super(connection, name, x, y, width, height);
	}

	@Override
	public void createComponants() {
		this.setLayout(new FlowLayout());
		
		labelSSN = new JLabel("Enter a SSN: ");
		labelC = new JLabel("Enter a code: ");
		labelYr = new JLabel("Enter a year: ");
		labelSemester = new JLabel("Enter a semester: ");
		
		ssn = new JTextField();
		ssn.setPreferredSize(new Dimension(100, 20));
		code = new JTextField();
		code.setPreferredSize(new Dimension(100, 20));
		year = new JTextField();
		year.setPreferredSize(new Dimension(100, 20));
		semester = new JTextField();
		semester.setPreferredSize(new Dimension(100, 20));
		
		submit = new JButton("Drop");
		submit.addActionListener(this);
		
		this.add(labelSSN);
		this.add(ssn);
		
		this.add(labelC);
		this.add(code);
		
		this.add(labelYr);
		this.add(year);
		
		this.add(labelSemester);
		this.add(semester);

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
				
			if(ssn.getText().equals("") || code.getText().equals("") || year.getText().equals("") || semester.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "All fields must not be empty!", "Error", JOptionPane.ERROR_MESSAGE);
			} else {
				st.executeUpdate("delete from Registered where ssn = "+ssni+" and code = '"+code.getText()+"' and year = '"+year.getText()+"' and semester = '"+semester.getText()+"'");
				JOptionPane.showMessageDialog(this, "Successfully dropped " +code.getText()+ " for " +ssni+ " from Database!", "Success", JOptionPane.INFORMATION_MESSAGE);
				ssn.setText("");
				code.setText("");
				year.setText("");
				semester.setText("");
			}
			
			st.close();
		} catch (SQLException e1) {
			sqlError(e1.getMessage());
		}
	}

}
