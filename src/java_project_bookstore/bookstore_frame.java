package java_project_bookstore;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;

public class bookstore_frame extends JFrame {
	private JComboBox<String> combo_frame;
	private String dataModel[];
	
	private JTextField search_content;
	private JButton search_bt, login_bt;
	
	private bookstore_DAO monDB;
	
	bookstore_frame() {	}
	bookstore_frame(bookstore_DAO db) {
		monDB = db;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initForm();
	}
	
	public void initForm() {
		Container cpane = getContentPane();
		JPanel controlPanel = new JPanel();
		JPanel listPanel = new JPanel();
		listPanel.setLayout(new BorderLayout());
		
		JLabel label;
		
		GridBagLayout gbl = new GridBagLayout();
		controlPanel.setLayout(gbl);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		
		setGrid(gbc, 0, 1, 1 ,1);
		label = new JLabel();
		gbl.setConstraints(label, gbc);
		controlPanel.add(label);
		
		setGrid(gbc, 1, 1, 1, 1);
		label = new JLabel();
		gbl.setConstraints(label, gbc);
		controlPanel.add(label);
		
		setGrid(gbc, 1, 1, 3, 1);
		search_content = new JTextField();
		gbl.setConstraints(search_content, gbc);
		controlPanel.add(search_content);
		
		setGrid(gbc, 5, 1, 1, 1);
		search_bt = new JButton("SEARCH");
		gbl.setConstraints(search_bt, gbc);
		controlPanel.add(search_bt);
		
		setGrid(gbc, 5, 2, 1, 1);
		dataModel = new String[20];
		combo_frame = new JComboBox<String>(new DefaultComboBoxModel<String>(dataModel));
		combo_frame.removeAllItems();
		combo_frame.addItem("master");
		combo_frame.addItem("user");
		gbl.setConstraints(combo_frame, gbc);
		controlPanel.add(combo_frame);
		
		setGrid(gbc, 6, 2, 1, 1);
		login_bt = new JButton("LOGIN");
		login_bt.addActionListener(new loginListener());
		gbl.setConstraints(login_bt, gbc);
		controlPanel.add(login_bt);
		
		cpane.add("North", controlPanel);
		pack();
		
	}
	public void setGrid(GridBagConstraints gbc, int dx, int dy, int width, int height) {
		gbc.gridx = dx;
		gbc.gridy = dy;
		gbc.gridwidth = width;
		gbc.gridheight = height;
	}
	
	class loginListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("LOGIN BUTTON WAS PRESSED!!!");
			login_frame log_frm = new login_frame();
			log_frm.setSize(500, 150);
			log_frm.setVisible(true);
		}
	}
	
	class login_frame extends bookstore_frame{
		private JTextField strID;
		private JPasswordField strPasswd;
		private JPanel pan;
		public login_frame() {
			initForm();
		}
		
		public void initForm() {
			Container cpane = getContentPane();
			cpane.setLayout(new FlowLayout());
			pan = new JPanel();
			pan.setLayout(new GridLayout(2, 2));
			
			pan.add(new JLabel("I     D : "));
			strID = new JTextField(16);
			pan.add(strID);
			
			pan.add(new JLabel("Password : "));
			strPasswd = new JPasswordField(16);
			strPasswd.setEchoChar('*');
			pan.add(strPasswd);
			
			
			
			JButton bt = new JButton(" Login ");
			bt.addActionListener(new logginginListener());
			
			cpane.add(pan);
			cpane.add(bt);
		}
		
		class logginginListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				if(strID.getText().equals("root")) { // master account
					if(new String(new String(strPasswd.getPassword())).equals("sj4321")) {
						if(combo_frame.getSelectedItem().equals("master")) {
							//show master's frame
							System.out.println("master account verified!! will show master's frame!!");
							System.out.println(strID.getText() + " " + strPasswd.getPassword() + " " + combo_frame.getSelectedItem());
						}
						else { // user frame
							// show user's frame
							System.out.println("master account verified!! will show user's frame!!");
							System.out.println(strID.getText() + " " + strPasswd.getPassword() + " " + combo_frame.getSelectedItem());
						}
					}
					else {
						//wrong password
						System.out.println("wrong password!");
						System.out.println(strID.getText() + " " + strPasswd.getPassword() + " " + combo_frame.getSelectedItem());
					}
				}
				else if(strID.getText().equals("sj001")) { // user
					if(new String(strPasswd.getPassword()).equals("sj4321")) {
						if(combo_frame.getSelectedItem().equals("user")) {
							//show user's frame
							System.out.println("user account verified!! will show user's frame!!");
							System.out.println(strID.getText() + " " + strPasswd.getPassword() + " " + combo_frame.getSelectedItem());
							System.out.println(new String(strPasswd.getPassword()));
						}
						else { // no permission
							System.out.println("no permission!");
							System.out.println(strID.getText() + " " + strPasswd.getPassword() + " " + combo_frame.getSelectedItem());
						}
					}
					else {
						System.out.println("wrong password!");
						System.out.println(strID.getText() + " " + strPasswd.getPassword() + " " + combo_frame.getSelectedItem());
						System.out.println(new String(strPasswd.getPassword()));
					}
				}
				else { // no account(wrong id)
					System.out.println("wrong ID!");
				}
				
				setVisible(false);
			}
		}
	}

}
