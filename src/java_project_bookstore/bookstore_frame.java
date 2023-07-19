package java_project_bookstore;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;

import java.awt.image.*;

public class bookstore_frame extends JFrame {
	private bookstore_frame mainframe;
	
//	private JComboBox<String> combo_frame;
//	private String dataModel[];
	
	private JTextField search_content;
	private JButton search_bt, login_bt, mypage_bt;
	private JButton left_bt, right_bt; // buttons for swiping book list
	private JButton writeReview_bt;
	
	public JPanel controlPanel, searchPanel, listPanel;
	public JPanel leftPanel, middlePanel, rightPanel; // panel that consists searchPanel
	public JPanel book1_panel, book2_panel, book3_panel; // panel that consists listPanel
	protected JPanel search_result_panel;
	
	private bookstore_DAO monDB;
	
	bookstore_frame() {	}
	bookstore_frame(bookstore_DAO db) {
		mainframe = this;
		
		monDB = db;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		basic_initForm();
	}
	
	public void basic_initForm() { // basic frame
		Container cpane = getContentPane();
		setLayout(new BoxLayout(cpane, BoxLayout.Y_AXIS));
		
		cpane.removeAll();
		
		controlPanel = new JPanel();
		
		searchPanel = new JPanel();
		middlePanel = new JPanel();
		
		listPanel = new JPanel();
		
		
		control_initForm();
		
		search_initForm();

		list_initForm();
		

		cpane.add(controlPanel);
		cpane.add(searchPanel);
		cpane.add(listPanel);
	}

	
	public void master_initForm() { // master frame
		Container cpane = getContentPane();
		setLayout(new BoxLayout(cpane, BoxLayout.Y_AXIS));
		
		cpane.removeAll();
		
		controlPanel = new JPanel();
		
		middlePanel = new JPanel();
		
		
		control_logged_master_Form();
		
		search_master_Form();
		
		cpane.add(controlPanel);
		cpane.add(searchPanel);	
//		cpane.add(listPanel);
	}
	
	public void user_initForm() { // user frame
		Container cpane = getContentPane();
		setLayout(new BoxLayout(cpane, BoxLayout.Y_AXIS));
		
		cpane.removeAll();
		
		controlPanel = new JPanel();
		
		searchPanel = new JPanel();
		middlePanel = new JPanel();
		
		listPanel = new JPanel();
		
		
		control_logged_user_Form();
		
		search_initForm();

		list_initForm();
		

		cpane.add(controlPanel);
		cpane.add(searchPanel);
		cpane.add(listPanel);
	}
	
	public void myPage_initForm() { // my page frame
		Container cpane = getContentPane();
		setLayout(new BoxLayout(cpane, BoxLayout.Y_AXIS));
		
		cpane.removeAll();
		
		controlPanel = new JPanel();
		
		middlePanel = new JPanel();
		
		
		control_logged_user_Form();
		
		search_myPage_Form();
		
		cpane.add(controlPanel);
		cpane.add(searchPanel);		
	}
	
	public void search_result_Form() { // search result(4-1, 4-2) frame
		Container cpane = getContentPane();
		setLayout(new BoxLayout(cpane, BoxLayout.Y_AXIS));
		
		cpane.removeAll();
		
		controlPanel = new JPanel();
		
		searchPanel = new JPanel();
		middlePanel = new JPanel();
		
		listPanel = new JPanel();
		
		
		control_logged_user_Form();
		
		search_initForm();
		
		if(result_cnt > 0) {
			list_result_Form();
		}
		else {
			
		}
		list_result_Form();
		

		cpane.add(controlPanel);
		cpane.add(searchPanel);
		cpane.add(listPanel);
		validate();
	}
	
	
	public void control_initForm() { // basic login part
		controlPanel.setLayout(new FlowLayout(2)); // FlowLayout.RIGHT?
		
//		dataModel = new String[20];
//		combo_frame = new JComboBox<String>(new DefaultComboBoxModel<String>(dataModel));
//		combo_frame.removeAllItems();
//		combo_frame.addItem("master");
//		combo_frame.addItem("user");
//		controlPanel.add(combo_frame);
		
		login_bt = new JButton("LOGIN");
		login_bt.addActionListener(new loginListener());
		controlPanel.add(login_bt);		
	}
	
	public void control_logged_master_Form() { // when logged-in
		controlPanel.setLayout(new FlowLayout(2));
		
		login_bt = new JButton("LOGOUT");
		login_bt.addActionListener(new logoutListener());
		controlPanel.add(login_bt);
	}
	
	public void control_logged_user_Form() { // when logged-in(for user)
		controlPanel.setLayout(new FlowLayout(2));
		
		mypage_bt = new JButton("MyPage");
		mypage_bt.addActionListener(new myPageListener());
		controlPanel.add(mypage_bt);
		
		login_bt = new JButton("LOGOUT");
		login_bt.addActionListener(new logoutListener());
		controlPanel.add(login_bt);
	}
	
	public void control_review_Form() { // when leaving reviews
		controlPanel.setLayout(new FlowLayout(2));
		
		login_bt = new JButton("LOGOUT");
		login_bt.addActionListener(new logoutListener());
		controlPanel.add(login_bt);
		
		writeReview_bt = new JButton("WRITE");
		writeReview_bt.addActionListener(new writeListener());
		controlPanel.add(writeReview_bt);
	}
	
	
	public void search_initForm() { // basic search part
		searchPanel.setLayout(new GridLayout(1, 3));
		
		leftPanel = new JPanel();
		searchPanel.add(leftPanel);
		
		GridBagLayout gbl = new GridBagLayout();
		middlePanel.setLayout(gbl);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;

		JLabel noname1, noname2, noname3, noname4; // to create 4 columns
		
		noname1 = new JLabel();
		gbl.setConstraints(noname1, gbc);
		middlePanel.add(noname1);
		noname2 = new JLabel();
		gbl.setConstraints(noname2, gbc);
		middlePanel.add(noname2);
		noname3 = new JLabel();
		gbl.setConstraints(noname3, gbc);
		middlePanel.add(noname3);
		
		gbc.gridwidth = GridBagConstraints.REMAINDER; // to end current row and create new one
		noname4 = new JLabel();
		gbl.setConstraints(noname4, gbc);
		middlePanel.add(noname4);
		
		search_content = new JTextField("Book title...");
		gbc.gridwidth = GridBagConstraints.RELATIVE;
		gbl.setConstraints(search_content, gbc);
		middlePanel.add(search_content);

		search_bt = new JButton("SEARCH");
		search_bt.addActionListener(new searchListener());
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbl.setConstraints(search_bt, gbc);
		middlePanel.add(search_bt);
		
//		searchBar.setBackground(Color.yellow);
		searchPanel.add(middlePanel);
		
		rightPanel = new JPanel();
		searchPanel.add(rightPanel);
	}
	
	public void search_master_Form() { // master's search part
		searchPanel.removeAll();
		
		JButton userManage_bt = new JButton("manage user");
		
		leftPanel.add(userManage_bt);
		
		searchPanel.add(leftPanel);
		
		JButton showResult_bt = new JButton("show result");
		
		middlePanel.add(showResult_bt);
		
		searchPanel.add(middlePanel);
		
		JButton wishList_bt = new JButton("show wish list");
		
		rightPanel.add(wishList_bt);

		searchPanel.add(rightPanel);
	}
	
	public void search_myPage_Form() { // my page's search part
		searchPanel.removeAll();
		
		JButton myInfo_bt = new JButton("my info");
		
		leftPanel.add(myInfo_bt);
		
		searchPanel.add(leftPanel);
		
		JButton deliveryList_bt = new JButton("delivery list");
		
		middlePanel.add(deliveryList_bt);
		
		searchPanel.add(middlePanel);
		
		JButton readingStatus_bt = new JButton("reading status");
		
		rightPanel.add(readingStatus_bt);
		
		searchPanel.add(rightPanel);
	}
	
	
	public void list_initForm() { // basic booklist part
		listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.X_AXIS));
		left_bt = new JButton("<");
		listPanel.add(left_bt);
		
		book1_panel = new JPanel();
		book1_panel.setBorder(BorderFactory.createLineBorder(Color.black));
//		book1_panel.setBackground(Color.yellow);
		listPanel.add(book1_panel);
		
		book2_panel = new JPanel();
//		book2_panel.setBackground(Color.blue);
		book2_panel.setBorder(BorderFactory.createLineBorder(Color.black));
		listPanel.add(book2_panel);
		
		book3_panel = new JPanel();
//		book3_panel.setBackground(Color.red);
		book3_panel.setBorder(BorderFactory.createLineBorder(Color.black));
		listPanel.add(book3_panel);
		
		right_bt = new JButton(">");
		listPanel.add(right_bt);
	}
	
	public void list_result_Form() { // search result list part
		listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
		
		search_result_panel = new JPanel();
		search_result_panel.setBorder(BorderFactory.createLineBorder(Color.black));
		listPanel.add(search_result_panel);
		
		search_result_panel = new JPanel();
		search_result_panel.setBorder(BorderFactory.createLineBorder(Color.black));
		listPanel.add(search_result_panel);
		
		search_result_panel = new JPanel();
		search_result_panel.setBorder(BorderFactory.createLineBorder(Color.black));
		listPanel.add(search_result_panel);
		
		search_result_panel = new JPanel();
		search_result_panel.setBorder(BorderFactory.createLineBorder(Color.black));
		listPanel.add(search_result_panel);
	}
	

	class loginListener implements ActionListener { // show login frame
		public void actionPerformed(ActionEvent e) {
			System.out.println("LOGIN BUTTON WAS PRESSED!!!");
			login_frame log_frm = new login_frame();
			log_frm.setSize(500, 150);
			log_frm.setVisible(true);
		}
	}
	
	class logoutListener implements ActionListener { // show basic frame
		public void actionPerformed(ActionEvent e) {
			System.out.println("\nLOGOUT BUTTON WAS PRESSED!!!");
			bookstore_frame.this.basic_initForm();
			bookstore_frame.this.validate();
		}
	}	
	
	class login_frame extends bookstore_frame implements ActionListener { // login frame
		private bookstore_frame mainFrame;
		
		private JTextField strID;
		private JPasswordField strPasswd;
		private JPanel pan;
		
		private JComboBox<String> combo_frame;
		private String dataModel[];
		
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
			bt.addActionListener(this);
			
			dataModel = new String[20];
			combo_frame = new JComboBox<String>(new DefaultComboBoxModel<String>(dataModel));
			combo_frame.removeAllItems();
			combo_frame.addItem("master");
			combo_frame.addItem("user");

			
			cpane.add(pan);
			cpane.add(bt);
			cpane.add(combo_frame);
		}
		
		public void actionPerformed(ActionEvent e) {
			if(strID.getText().equals("root")) { // master account
				if(new String(new String(strPasswd.getPassword())).equals("sj4321")) {
					if(combo_frame.getSelectedItem().equals("master")) {
						//bookstore_frame.this.repaint();
						bookstore_frame.this.master_initForm(); //show master's frame
						bookstore_frame.this.validate();
						
						System.out.println("master account verified!! will show master's frame!!");
						System.out.println(strID.getText() + " " + strPasswd.getPassword() + " " + combo_frame.getSelectedItem());
					}
					else { // user frame
							
						bookstore_frame.this.user_initForm(); // show user's frame
						bookstore_frame.this.validate();
							
						System.out.println("master account verified!! will show user's frame!!");
						System.out.println(strID.getText() + " " + strPasswd.getPassword() + " " + combo_frame.getSelectedItem());
					}
				}
				else { //wrong password or the account already exists
						
					System.out.println("wrong password or the account already exists!!!");
					System.out.println(strID.getText() + " " + strPasswd.getPassword() + " " + combo_frame.getSelectedItem());
				}
			}
			else if(strID.getText().equals("sj001")) { // user
				if(new String(strPasswd.getPassword()).equals("sj4321")) {
					if(combo_frame.getSelectedItem().equals("user")) {
						//show user's frame
							
						bookstore_frame.this.user_initForm(); // show user's frame
						bookstore_frame.this.validate();
							
						System.out.println("user account verified!! will show user's frame!!");
						System.out.println(strID.getText() + " " + strPasswd.getPassword() + " " + combo_frame.getSelectedItem());
						System.out.println(new String(strPasswd.getPassword()));
					}
					else { // no permission or master account already exists
						System.out.println("no permission or master account already exists!!!");
						System.out.println(strID.getText() + " " + strPasswd.getPassword() + " " + combo_frame.getSelectedItem());
					}
				}
				else { // wrong password or the account already exists
					System.out.println("wrong password or the account already exists!!!");
					System.out.println(strID.getText() + " " + strPasswd.getPassword() + " " + combo_frame.getSelectedItem());
					System.out.println(new String(strPasswd.getPassword()));
				}
			}
			else { // no account(wrong id) --> create account
				System.out.println("wrong ID!");
			}
				
			setVisible(false);
		}
	}
	
	
	class searchListener implements ActionListener { // show search result
		public void actionPerformed(ActionEvent e) {
			System.out.println("\nSEARCH BUTTON WAS PRESSED!!!");
			bookstore_frame.this.search_result_Form();
			repaint();
		}
	}
	
	class bookInfo_panel extends JPanel { // book info panel
		BufferedImage off;
		Graphics offG;
		
		public bookInfo_panel() {
			initForm();
		}
		
		public void initForm() {
			offG = off.getGraphics();
		}
		public void paint(Graphics g) {
			super.paint(g);
			g.drawImage(off, 0, 0, this);
		}
	}
	

	
	class myPageListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("MYPAGE BUTTON WAS PRESSED!!!");
			bookstore_frame.this.myPage_initForm();
			bookstore_frame.this.validate();
		}
	}
	
	
	class writeListener implements ActionListener { // add review
		public void actionPerformed(ActionEvent e) {
			
		}
	}

	public void paint(Graphics g) {
		super.paint(g);
	}






}