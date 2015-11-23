package project.gui.internal;

import project.connection.DatabaseCon;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * Check the grades of a student
 * 
 * @project Project 1-520
 * @author Alexander Sniffin
 * @date Apr 3, 2015
 */
public class CheckGrades extends FrameType {
	
	private JButton submit;
	private JLabel labelName, labelSSN, labelYr, labelSemester;
	private JTextField name, ssn, year, semester;

	public CheckGrades(DatabaseCon connection, String name, int x, int y, int width, int height) {
		super(connection, name, x, y, width, height);
	}

	@Override
	public void createComponants() {
		this.setLayout(new FlowLayout());
		
		labelName = new JLabel("Enter a name: ");
		labelSSN = new JLabel("Enter a SSN: ");
		labelYr = new JLabel("Enter a year: ");
		labelSemester = new JLabel("Enter a semester: ");
		
		name = new JTextField();
		name.setPreferredSize(new Dimension(100, 20));
		ssn = new JTextField();
		ssn.setPreferredSize(new Dimension(100, 20));
		year = new JTextField();
		year.setPreferredSize(new Dimension(100, 20));
		semester = new JTextField();
		semester.setPreferredSize(new Dimension(100, 20));
		
		submit = new JButton("Check");
		submit.addActionListener(this);
		
		this.add(labelName);
		this.add(name);
		
		this.add(labelSSN);
		this.add(ssn);
		
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
				
			if(name.getText().equals("") || year.getText().equals("") || semester.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "All fields must not be empty!", "Error", JOptionPane.ERROR_MESSAGE);
			} else {
				ResultSet rs=st.executeQuery("select code, grade from Student s, Registered r where s.ssn = "+ ssni +" and r.ssn = "+ ssni +" and s.name = '"+ name.getText() +"' and r.year = '"+year.getText()+"' and r.semester = '"+semester.getText()+"'");
	            String result = "", code = "", grade = "";

	            //if(rs.isBeforeFirst()) {
	            	while (rs.next()) {
	            		code=rs.getString(1).replace(" ", "");
	            		grade=rs.getString(2);
	            		result+="Course: " + code + ", Year: " + year.getText() + ", Semester: " + semester.getText() + ", Grade: " + grade + " \n";
	            	}
				//} else
					//nothing
	            
	            JOptionPane.showMessageDialog(this, result, "Result for Student " + name.getText(), JOptionPane.INFORMATION_MESSAGE);
	            
				name.setText("");
				ssn.setText("");
				year.setText("");
				semester.setText("");
			}
			
			st.close();
		} catch (SQLException e1) {
			sqlError(e1.getMessage());
		}
	}


}
