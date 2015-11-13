package project.gui.internal;

import project.connection.DatabaseCon;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * Upload the grades for all students in a course
 * 
 * @project Project 1-520
 * @author Alexander Sniffin
 * @date Apr 3, 2015
 */
public class UploadGrades extends FrameType {

	private JButton submit;
	private JLabel labelC, labelY, labelSemester;
	private JTextField code, year, semester;
	
	public UploadGrades(DatabaseCon connection, String name, int x, int y, int width, int height) {
		super(connection, name, x, y, width, height);
	}

	@Override
	public void createComponants() {
		this.setLayout(new FlowLayout());
		
		labelC = new JLabel("Enter a code: ");
		labelY = new JLabel("Enter a year: ");
		labelSemester = new JLabel("Enter a semester: ");
		
		code = new JTextField();
		code.setPreferredSize(new Dimension(100, 20));
		year = new JTextField();
		year.setPreferredSize(new Dimension(100, 20));
		semester = new JTextField();
		semester.setPreferredSize(new Dimension(100, 20));
		
		submit = new JButton("Upload");
		submit.addActionListener(this);
		
		this.add(labelC);
		this.add(code);
		
		this.add(labelY);
		this.add(year);
		
		this.add(labelSemester);
		this.add(semester);

		this.add(submit);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			Statement st = connection.getConnection().createStatement();
				
			if(code.getText().equals("") || year.getText().equals("") || semester.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "All fields must not be empty!", "Error", JOptionPane.ERROR_MESSAGE);
			} else {
				ResultSet rs=st.executeQuery("select r.ssn, s.name from Student s, Registered r where s.ssn = r.ssn and code = '"+code.getText()+"' and year = '"+year.getText()+"' and semester = '"+semester.getText()+"'");
				String ssn = "", name = "";
				
				if (rs.isBeforeFirst()) {
					//Get each student and put them into a map (SSN, Name)
					HashMap<Integer, String> students = new HashMap<Integer, String>();
					while (rs.next()) {
						ssn = rs.getString(1);
						name = rs.getString(2);
						students.put(Integer.parseInt(ssn), name);
					}
					
					//After getting all students, iterate through the hash and upload each grade
					Iterator<Entry<Integer, String>> map = students.entrySet().iterator();
					while(map.hasNext()) {
						int ssnkey = map.next().getKey();
						String grade = JOptionPane.showInputDialog(this, "Please input grade for " + students.get(ssnkey) + "(" + ssnkey + ")", "Input Grade", JOptionPane.INFORMATION_MESSAGE);
						
						if (grade != null)
							st.executeUpdate("update Registered set grade = '" + grade + "' where ssn = " + ssnkey + " and code = '"+code.getText()+"' and year = '"+year.getText()+"' and semester = '"+semester.getText()+"'");
					}
					JOptionPane.showMessageDialog(this, "Successfully uploaded grades into Database!", "Success", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(this, "No students found in the course selected.", "Empty", JOptionPane.INFORMATION_MESSAGE);
				}
				
				code.setText("");
				year.setText("");
				semester.setText("");
			}
			
			st.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
			sqlError(e1.getMessage());
		}
	}

}
