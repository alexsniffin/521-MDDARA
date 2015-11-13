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
 * Delete a course and remove all registered students from it
 * 
 * @project Project 1-520
 * @author Alexander Sniffin
 * @date Apr 3, 2015
 */
public class DeleteCourse extends FrameType {
	
	private JButton submit;
	private JLabel labelC;
	private JTextField code;

	public DeleteCourse(DatabaseCon connection, String name, int x, int y, int width, int height) {
		super(connection, name, x, y, width, height);
	}

	@Override
	public void createComponants() {
		this.setLayout(new FlowLayout());
		
		labelC = new JLabel("Enter a code: ");
		
		code = new JTextField();
		code.setPreferredSize(new Dimension(100, 20));
		
		submit = new JButton("Delete");
		submit.addActionListener(this);
		
		this.add(labelC);
		this.add(code);

		this.add(submit);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			Statement st = connection.getConnection().createStatement();
				
			if(code.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Code must have a value in it!", "Error", JOptionPane.ERROR_MESSAGE);
			} else {
				st.executeUpdate("delete from Registered where code = '" + code.getText() + "'");
				
				st.executeUpdate("delete from Course where code = '" + code.getText() + "'");
				JOptionPane.showMessageDialog(this, "Successfully deleted Course " + code.getText() +" from the Database!", "Success", JOptionPane.INFORMATION_MESSAGE);
				code.setText("");
			}
			
			st.close();
		} catch (SQLException e1) {
			sqlError(e1.getMessage());
		}
	}

}
