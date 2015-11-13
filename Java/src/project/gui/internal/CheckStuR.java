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
 * Check the registered classes of a student
 * 
 * @project Project 1-520
 * @author Alexander Sniffin
 * @date Apr 3, 2015
 */
public class CheckStuR extends FrameType {
	
	private JButton submit;
	private JLabel labelSSN;
	private JTextField ssn;

	public CheckStuR(DatabaseCon connection, String name, int x, int y, int width, int height) {
		super(connection, name, x, y, width, height);
	}

	@Override
	public void createComponants() {
		this.setLayout(new FlowLayout());
		
		labelSSN = new JLabel("Enter a SSN: ");
		
		ssn = new JTextField();
		ssn.setPreferredSize(new Dimension(100, 20));
		
		submit = new JButton("Check");
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
				JOptionPane.showMessageDialog(this, "To check a Student, SSN must be filled in!", "Error", JOptionPane.ERROR_MESSAGE);
			} else {
				ResultSet rs=st.executeQuery("select name, code, year, semester, grade from Student s, Registered r where s.ssn = "+ ssni +" and s.ssn = r.ssn");
	            String result="", name="", code="", year="", semester="", grade="";
	            while (rs.next()) {
	               name=rs.getString(1);
	               code=rs.getString(2).replace(" ", "");
	               year=rs.getString(3).replace(" ", "");
	               semester=rs.getString(4).replace(" ", "");
	               grade=rs.getString(5);
	               result += "Code: " +code+ ", Year: " +year+ ", Semester: " +semester+ ", Grade: " +grade+ "\n"; 
	            }
	            
				JOptionPane.showMessageDialog(this, result, "Result for Student "+ name, JOptionPane.INFORMATION_MESSAGE);
				ssn.setText("");
				rs.close();
			}
			
			st.close();
		} catch (SQLException e1) {
			sqlError(e1.getMessage());
		}
	}

}
