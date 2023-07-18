package java_project_bookstore;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;

public class bookstore_frame extends JFrame {
	private JComboBox<String> combo_frame;
	private String dataModel[];
	
	private JTextField search_content;
	private JButton search_bt, login_bt, left_bt, right_bt;
	private JPanel book1_panel, book2_panel, book3_panel;
	
	private bookstore_DAO monDB;
	
	bookstore_frame() {	}
	bookstore_frame(bookstore_DAO db) {
		monDB = db;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initForm();
	}
	
	public void initForm() {
		Container cpane = getContentPane();
		cpane.setLayout(new BoxLayout(cpane, BoxLayout.Y_AXIS));
		
		JPanel controlPanel = new JPanel();
		controlPanel.setBackground(Color.cyan);
		JPanel searchPanel = new JPanel();
		searchPanel.setBackground(Color.green);
		JPanel listPanel = new JPanel();
		listPanel.setBackground(Color.gray);
		
		
		controlPanel.setLayout(new FlowLayout(2));
		
		dataModel = new String[20];
		combo_frame = new JComboBox<String>(new DefaultComboBoxModel<String>(dataModel));
		combo_frame.removeAllItems();
		combo_frame.addItem("master");
		combo_frame.addItem("user");
		controlPanel.add(combo_frame);
		
		login_bt = new JButton("LOGIN");
		login_bt.addActionListener(new loginListener());
		controlPanel.add(login_bt);
		
		
		GridBagLayout gbl = new GridBagLayout();
		searchPanel.setLayout(gbl);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1.0;
		
		search_content = new JTextField("Book title...");
		gbc.gridx = 0;
		gbc.gridwidth = 3;
		gbl.setConstraints(search_content, gbc);
		searchPanel.add(search_content);
		
		search_bt = new JButton("SEARCH");
		gbc.gridx = 3;
		gbc.gridwidth = 1;
		gbl.setConstraints(search_bt, gbc);
		searchPanel.add(search_bt);
		
		
		listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.X_AXIS));
		left_bt = new JButton("<");
		listPanel.add(left_bt);
		
		book1_panel = new JPanel();
		book1_panel.setBackground(Color.yellow);
		listPanel.add(book1_panel);
		
		book2_panel = new JPanel();
		book2_panel.setBackground(Color.blue);
		listPanel.add(book2_panel);
		
		book3_panel = new JPanel();
		book3_panel.setBackground(Color.red);
		listPanel.add(book3_panel);
		
		right_bt = new JButton(">");
		listPanel.add(right_bt);

		add(controlPanel);
		add(searchPanel);
		add(listPanel);
		
		
		
		
		
		
		
		
		
		
		
		
//		
//		listPanel.setLayout(new BorderLayout());
//		
//		
//		JLabel label;
//		
//		GridBagLayout gbl = new GridBagLayout();
//		controlPanel.setLayout(gbl);
//		GridBagConstraints gbc = new GridBagConstraints();
//		gbc.fill = GridBagConstraints.BOTH;
//		gbc.weightx = 1;
//		gbc.weighty = 1;
//		
//		setGrid(gbc, 0, 1, 1 ,1);
//		label = new JLabel();
//		gbl.setConstraints(label, gbc);
//		controlPanel.add(label);
//		
//		setGrid(gbc, 1, 1, 1, 1);
//		label = new JLabel();
//		gbl.setConstraints(label, gbc);
//		controlPanel.add(label);
		
//		setGrid(gbc, 1, 1, 3, 1);
//		search_content = new JTextField();
//		gbl.setConstraints(search_content, gbc);
//		controlPanel.add(search_content);
		
//		setGrid(gbc, 5, 1, 1, 1);
//		search_bt = new JButton("SEARCH");
//		gbl.setConstraints(search_bt, gbc);
//		controlPanel.add(search_bt);
		
//		setGrid(gbc, 5, 2, 1, 1);
//		dataModel = new String[20];
//		combo_frame = new JComboBox<String>(new DefaultComboBoxModel<String>(dataModel));
//		combo_frame.removeAllItems();
//		combo_frame.addItem("master");
//		combo_frame.addItem("user");
//		gbl.setConstraints(combo_frame, gbc);
//		controlPanel.add(combo_frame);
		
//		setGrid(gbc, 6, 2, 1, 1);
//		login_bt = new JButton("LOGIN");
//		login_bt.addActionListener(new loginListener());
//		gbl.setConstraints(login_bt, gbc);
//		controlPanel.add(login_bt);
		
//		cpane.add("North", controlPanel);
//		pack();
		
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
