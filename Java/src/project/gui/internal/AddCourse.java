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
 * Add a new nonexistant course into the database
 * 
 * @project Project 1-520
 * @author Alexander Sniffin
 * @date Apr 3, 2015
 */
public class AddCourse extends FrameType {

	private JButton submit;
	private JLabel labelC, labelT;
	private JTextField code, title;
	
	public AddCourse(DatabaseCon connection, String name, int x, int y, int width, int height) {
		super(connection, name, x, y, width, height);
	}

	@Override
	public void createComponants() {
		this.setLayout(new FlowLayout());
		
		labelC = new JLabel("Enter a course code: ");
		labelT = new JLabel("Enter a title: ");
		
		code = new JTextField();
		code.setPreferredSize(new Dimension(100, 20));
		title = new JTextField();
		title.setPreferredSize(new Dimension(100, 20));
		
		submit = new JButton("Create");
		submit.addActionListener(this);
		
		this.add(labelC);
		this.add(code);
		
		this.add(labelT);
		this.add(title);

		this.add(submit);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			Statement st = connection.getConnection().createStatement();
			
			if(code.getText().equals("") || title.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "One or both text fields are empty!", "Error", JOptionPane.ERROR_MESSAGE);
			} else {
				st.executeUpdate("insert into Course values('"+code.getText()+"', '"+title.getText()+"')");
				JOptionPane.showMessageDialog(this, "Successfully added " + title.getText() + " into Database!", "Success", JOptionPane.INFORMATION_MESSAGE);
				code.setText("");
				title.setText("");
			}
			
			st.close();
		} catch (SQLException e1) {
			sqlError(e1.getMessage());
		}
	}
}
