package project.gui;

import project.connection.DatabaseCon;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import project.gui.internal.*;

/**
 * Creates the user interface with all of the needed menu options
 * 
 * @project Project 1-520
 * @author Alexander Sniffin
 * @date Apr 3, 2015
 */
public class GUI extends JFrame {
	
	/**
	 * Pointer to our database connection
	 */
	private DatabaseCon connection;
	
	private JPanel body;
	private JDesktopPane desktop;
	private JMenuBar menu;
	private JMenu file, edit, view, help;
	private JMenuItem exit, about, addCourse, deleteCourse, addStudent, deleteStudent, register, drop, checkRegistration, upload, checkGrade;
	private JLabel statusBar;
	
	/**
	 * Initilize the GUI
	 * 
	 * @param title Title of the window
	 * @param size Size of the window
	 */
	public GUI(String title, Dimension size) {
		this.setTitle(title);
		this.setBackground(new Color(210, 210, 210));
		this.setPreferredSize(size);
		
		this.setJMenuBar(createMenu());
		
		body = new JPanel();
		body.setLayout(new BorderLayout());
		
        desktop = new JDesktopPane();       
		statusBar = new JLabel("Setting up connection with the database...");
        statusBar.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        
        body.add(statusBar, BorderLayout.SOUTH);
        body.add(desktop, BorderLayout.CENTER);
        this.add(body);
        
        this.setResizable(true);
		this.setVisible(true);
		this.pack();
		this.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		        System.exit(0);
		    }
		});
		
		JOptionPane.showMessageDialog(body, 
				"Welcome to the University Registration System! Use the menus to get started.", "Hello!",
				JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * Sets up the menu
	 * 
	 * @return menu
	 */
	public JMenuBar createMenu() {
		menu = new JMenuBar();
		file = new JMenu("File");
		edit = new JMenu("Edit");
		view = new JMenu("View");
		help = new JMenu("Help");
		file.setMnemonic(KeyEvent.VK_F);
		edit.setMnemonic(KeyEvent.VK_E);
		view.setMnemonic(KeyEvent.VK_I);
		help.setMnemonic(KeyEvent.VK_H);
		menu.add(file);
		menu.add(edit);
		menu.add(view);
		menu.add(help);
		
		exit = new JMenuItem("Exit", KeyEvent.VK_X);
		about = new JMenuItem("About", KeyEvent.VK_A);
		addCourse = new JMenuItem("Add Course", KeyEvent.VK_C);
		deleteCourse = new JMenuItem("Delete Course", KeyEvent.VK_L);
		addStudent = new JMenuItem("Add Student", KeyEvent.VK_S);
		deleteStudent = new JMenuItem("Delete Student", KeyEvent.VK_T);
		register = new JMenuItem("Register Course", KeyEvent.VK_R);
		drop = new JMenuItem("Drop Course", KeyEvent.VK_D);
		checkRegistration = new JMenuItem("Check Student Registration", KeyEvent.VK_C);
		upload = new JMenuItem("Upload Grades", KeyEvent.VK_U);
		checkGrade = new JMenuItem("Check Grades", KeyEvent.VK_H);
		
		exit.setToolTipText("Exit application");
		about.setToolTipText("About this program");
		addCourse.setToolTipText("Add a new course");
		deleteCourse.setToolTipText("Delete an existing course");
		addStudent.setToolTipText("Add a new student");
		deleteStudent.setToolTipText("Delete an existing student");
		register.setToolTipText("Register a student to a course");
		drop.setToolTipText("Drop a students course");
		checkRegistration.setToolTipText("Check a students registration");
		upload.setToolTipText("Upload a students grades");
		checkGrade.setToolTipText("Check a students grades");
		
		exit.setActionCommand("exit");
		about.setActionCommand("about");
		addCourse.setActionCommand("add");
		deleteCourse.setActionCommand("delete");
		addStudent.setActionCommand("addS");
		deleteStudent.setActionCommand("deleteS");
		register.setActionCommand("register");
		drop.setActionCommand("drop");
		checkRegistration.setActionCommand("checkR");
		upload.setActionCommand("upload");
		checkGrade.setActionCommand("checkG");
		
		exit.addActionListener(new GuiActions());
		about.addActionListener(new GuiActions());
		addCourse.addActionListener(new GuiActions());
		deleteCourse.addActionListener(new GuiActions());
		addStudent.addActionListener(new GuiActions());
		deleteStudent.addActionListener(new GuiActions());
		register.addActionListener(new GuiActions());
		drop.addActionListener(new GuiActions());
		checkRegistration.addActionListener(new GuiActions());
		upload.addActionListener(new GuiActions());
		checkGrade.addActionListener(new GuiActions());
		
		file.add(addCourse);
		file.add(addStudent);
		file.add(deleteCourse);
		file.add(deleteStudent);
		file.addSeparator();
		file.add(exit);
		
		edit.add(register);
		edit.add(drop);
		edit.add(upload);
		
		view.add(checkRegistration);
		view.add(checkGrade);
		
		help.add(about);
		
		menusEnabled(false);
		
		return menu;
	}
	
	/**
	 * Enable all or disable all of the menus
	 * 
	 * @param enabled True to enable menus
	 */
	public void menusEnabled(boolean enabled) {
		addCourse.setEnabled(enabled);
		deleteCourse.setEnabled(enabled);
		addStudent.setEnabled(enabled);
		deleteStudent.setEnabled(enabled);
		register.setEnabled(enabled);
		drop.setEnabled(enabled);
		checkRegistration.setEnabled(enabled);
		upload.setEnabled(enabled);
		checkGrade.setEnabled(enabled);
	}
	
	/**
	 * Creates a new internal frame
	 * 
	 * @param type Frame class
	 */
    public void createFrame(FrameType type) {
        type.setVisible(true);
        type.setResizable(false);
        desktop.add(type);
        try {
            type.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {
        	e.printStackTrace();
        }
    }
    
	/**
	 * Changes the status bars text
	 * 
	 * @param s String in which to set the status bar too
	 */
	public void statusText(String s) {
		statusBar.setText(s);
	}
	
	/**
	 * Set the pointer for the database connection
	 * 
	 * @param connection
	 */
	public void setDB(DatabaseCon connection) {
		this.connection = connection;
	}
	
	/**
	 * Action Listener class
	 * 
	 * @author Alexander
	 */
	private class GuiActions implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			switch (e.getActionCommand()) {
				case "exit":
					try {
						connection.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
			        System.exit(0);
					break;
				
				case "about":
					JOptionPane.showMessageDialog(body, 
							"University Registration System Program can add or drop courses,\n"
							+ "add or drop students and preform many other useful functions.\n"
							+ "Created by Alexander Sniffin", "About",
							JOptionPane.INFORMATION_MESSAGE);
					break;
					
				case "add":
					createFrame(new AddCourse(connection, "Add a Course", 10, 10, 260, 130));
					break;
					
				case "delete":
					createFrame(new DeleteCourse(connection, "Delete a Course", 10, 10, 200, 100));
					break;
					
				case "addS":
					createFrame(new AddStudent(connection, "Add a Student", 10, 10, 220, 180));
					break;
					
				case "deleteS":
					createFrame(new DeleteStudent(connection, "Delete a Student", 10, 10, 200, 100));
					break;
				
				case "register":
					createFrame(new RegisterCourse(connection, "Register a Course", 10, 10, 250, 180));
					break;
					
				case "drop":
					createFrame(new DropCourse(connection, "Drop a Course", 10, 10, 250, 180));
					break;
					
				case "checkR":
					createFrame(new CheckStuR(connection, "Check Student Registration", 10, 10, 200, 100));
					break;
					
				case "upload":
					createFrame(new UploadGrades(connection, "Upload Grades", 10, 10, 240, 150));
					break;
					
				case "checkG":
					createFrame(new CheckGrades(connection, "Check Grades", 10, 10, 230, 180));
					break;
			}
		}
	}
}
