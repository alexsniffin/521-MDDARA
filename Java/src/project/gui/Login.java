package project.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import project.User;

/**
 * Creates a Login box with a username, password, and login button fields. Should return two Strings: Username and password
 * 
 * @project 521_Project
 * @author Alexander Sniffin
 * @date Nov 25, 2015
 */
public class Login extends JFrame {
	
	private MainWindow gui;
	private User user;
	private Dimension size;
	
	private JButton blogin = new JButton("Login");
	private JPanel panel = new JPanel();
	private JTextField txuser = new JTextField(15);
	private JLabel username = new JLabel("Username:");
	private JLabel password = new JLabel("Password:");
	private JPasswordField pass = new JPasswordField(15);
	
	public Login(MainWindow gui, User user, String title, Dimension size) {
		this.gui = gui;
		this.user = user;
		this.size = size;
		this.setTitle(title);
		this.setBackground(new Color(210, 210, 210));
		this.setPreferredSize(size);
		
		panel.setLayout (null); 

		//Getting nice postioning
		txuser.setBounds((size.width/2) - (size.width/2)/2, (size.height/2) - 50, size.width/2, (int) (size.height*.1));
		pass.setBounds((size.width/2) - (size.width/2)/2, (size.height/2) - 7, size.width/2, (int) (size.height*.1));
		blogin.setBounds((size.width/2) - (size.width/2)/2/2, (size.height/2) + 20, size.width/2/2, 20);
		username.setBounds((size.width/2) - (size.width/2)/2, (size.height/2) - 63, 80, 12);
		password.setBounds((size.width/2) - (size.width/2)/2, (size.height/2) - 20, 80, 12);

		panel.add(blogin);
		panel.add(txuser);
		panel.add(pass);
		panel.add(username);
		panel.add(password);
		
		blogin.addActionListener(new GuiActions());
		
		getContentPane().add(panel);
		setVisible(true);
		
		this.setResizable(false);
		this.setVisible(true);
		this.pack();
		
		//Center of screen
		this.setLocationRelativeTo(null);
	}
	
	private class GuiActions implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (!user.getConnection().isConnected()) {
				
				gui.statusText("Attempting to connect with the database...");
				if (user.getConnection().setup()) {
					try {
						Statement st = user.getConnection().getConnection().createStatement();
					
						//getPassword causes some problems..
						ResultSet rs = st.executeQuery("select dbo.login('"+txuser.getText()+"', '"+pass.getText()+"')");
						
						int result = 0;
						while (rs.next()) {
							result = (rs.getString(1) != null) ? Integer.parseInt(rs.getString(1)) : 0;
						}
						
						rs.close();
						
						if (result > 0 ) {
							user.setId(result);
							user.setUsername(txuser.getText());
							user.getUserInfo();
							user.setStartSession(System.currentTimeMillis());
							gui.statusText("Logged in as " + user.getName() + " (" + user.getUsername() + ") on MDDARA Database...");
							gui.menusEnabled(true);
							setVisible(false);
							dispose();
						} else {
							JOptionPane.showMessageDialog(Login.this, 
									"Incorrect username or password!", "Sorry", JOptionPane.INFORMATION_MESSAGE);
							user.getConnection().getConnection().close();
							user.getConnection().setConnected(false);
							gui.statusText("Currently in offline mode...");
						}
					
						st.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				} else {
					gui.statusText("Error connecting, currently in offline mode...");
				}
			}
		}
	}
}
