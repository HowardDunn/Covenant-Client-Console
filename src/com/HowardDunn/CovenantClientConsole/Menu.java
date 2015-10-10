package com.HowardDunn.CovenantClientConsole;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.JSplitPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.ScrollPane;
import java.awt.Label;
import java.awt.Panel;

import javax.swing.JScrollPane;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTabbedPane;

import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class Menu {

public static JFrame frame;
public static JPanel panel_1 = new JPanel();
public static JPanel panel_2 = new JPanel();
public static JPanel panel_3 = new JPanel();
public static JPanel panel_4 = new JPanel();
public static JPanel panel  = new JPanel();
public static boolean initializedClientList = false;
public static boolean signedOut = false;
public static JLabel loading;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					try{
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					}
					catch(Exception e){
						
					}
					Menu window = new Menu();
				//	Menu.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Menu() {
		initialize();
		
		Thread t1 = new Thread(new Runnable() {
		     public void run() {
		          // code goes here.
		    	 
		    	 SQL.getNotificationsToday();
		 		SQL.getNotificationsTomorrow();
		 		checkPanels();
		 		System.out.println("Finsihed Loading Notifications");
		 		loading.setVisible(false);
		     }
		});  
		t1.start();
		
		
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	public static void refresh(){
		
		panel.removeAll();
		panel_2.removeAll();
		panel_3.removeAll();
		panel_4.removeAll();
		panel_1.removeAll();
		panel.revalidate();
		panel.repaint();
		panel_1.revalidate();
		panel_1.repaint();
		panel_2.revalidate();
		panel_2.repaint();
		panel_3.revalidate();
		panel_3.repaint();
		panel_4.revalidate();
		panel_4.repaint();
		loading.setVisible(true);
		Thread t1 = new Thread(new Runnable() {
		     public void run() {
		          // code goes here.
		    	 
		    	 SQL.getNotificationsToday();
		 		SQL.getNotificationsTomorrow();
		 		checkPanels();
		 		System.out.println("Finsihed Loading Notifications");
		 		loading.setVisible(false);
		     }
		});  
		t1.start();
		
		
	}
	
	public static void checkPanels(){
		
		if(panel.getComponentCount() == 0 ){
			
			panel.add(new JLabel("No License Expirations found."));
		}
		
		if(panel_1.getComponentCount() == 0 ){
			
			panel_1.add(new JLabel("No Policy Renewals Expiration notifications found."));
		}
		
		if(panel_2.getComponentCount() == 0 ){
			
			panel_2.add(new JLabel("No Fitness Expiration notifications found."));
		}
		
		if(panel_3.getComponentCount() == 0 ){
			
			panel_3.add(new JLabel("No Birthday notifications found."));
		}
		
		if(panel_4.getComponentCount() == 0 ){
			
			panel_4.add(new JLabel("No Registration Cert notifications found."));
		}
		
	}
	
	public static void addNotificationLicense(String notif){
		
		
		JLabel testLabel = new JLabel(notif);
		testLabel.setFont(new Font("Segoe UI", Font.PLAIN, 10));
		testLabel.setToolTipText(SQL.lNumber);
		
		
		
		panel.add(testLabel);
		//panel_1.add(testLabel);
		
		
	}
	
	public static void addNotificationFitness(String notif){
		
		
		JLabel testLabel = new JLabel(notif);
		testLabel.setFont(new Font("Segoe UI", Font.PLAIN, 10));
		testLabel.setToolTipText(SQL.fNumber);
		
		
		
		panel_2.add(testLabel);
		//panel_1.add(testLabel);
		
		
	}
	
public static void addNotificationPolicy(String notif){
		
		
		JLabel testLabel = new JLabel(notif);
		testLabel.setFont(new Font("Segoe UI", Font.PLAIN, 10));
		testLabel.setToolTipText(SQL.pNumber);
	
		
		
		panel_1.add(testLabel);
		//panel_1.add(testLabel);
		
		
	}

public static void addNotificationBirthday(String notif){
	
	
	JLabel testLabel = new JLabel(notif);
	testLabel.setFont(new Font("Segoe UI", Font.PLAIN, 10));
	testLabel.setToolTipText(SQL.bNumber);
	
	
	
	panel_3.add(testLabel);
	//panel_1.add(testLabel);
	
	
}

public static void addNotificationRegistration(String notif){
	
	
	JLabel testLabel = new JLabel(notif);
	testLabel.setFont(new Font("Segoe UI", Font.PLAIN, 10));
	testLabel.setToolTipText(SQL.rNumber);

	
	
	
	panel_4.add(testLabel);
	//panel_1.add(testLabel);
	
	
}
	
	private void initialize() {
		
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("res\\covenantlogo.png"));
		frame.getContentPane().setFont(new Font("Segoe UI", Font.PLAIN, 11));
		frame.setBounds(100, 100, 800, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Covenant Client Console");
		 
	
		
		loading = new JLabel("Loading Data Please Wait ...");
		loading.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		loading.setIcon(new ImageIcon("res\\ajax-loader.gif"));
		loading.setBounds(270, 200, 203, 40);
		frame.getContentPane().add(loading);
		
		
		EventHandler handler = new EventHandler();
		
		JLabel lblEmployee = new JLabel("Employee: ");
		lblEmployee.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblEmployee.setBounds(10, 11, 63, 14);
		frame.getContentPane().add(lblEmployee);
		
		JLabel emFname = new JLabel("");
		emFname.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		emFname.setBounds(83, 11, 53, 14);
		frame.getContentPane().add(emFname);
		
		JLabel emLname = new JLabel("");
		emLname.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		emLname.setBounds(146, 11, 106, 14);
		frame.getContentPane().add(emLname);
		
		emFname.setText(SQL.userFname);
		emLname.setText(SQL.userLname);
		
		JButton vClient = new JButton("View Client List");
		vClient.setToolTipText("View Client List and also edit entries");
		vClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		vClient.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		vClient.setBounds(260, 365, 235, 65);
		vClient.addActionListener(handler);
		vClient.setActionCommand("vClientList");
		frame.getContentPane().add(vClient);
		
		JButton createClient = new JButton("Create Client");
		createClient.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		createClient.setBounds(10, 365, 235, 65);
		createClient.setActionCommand("createClient");
		createClient.addActionListener(handler);
		frame.getContentPane().add(createClient);
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		   System.out.println(dateFormat.format(date));
		   
		   
		JLabel lblDate = new JLabel("Date:");
		lblDate.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblDate.setBounds(10, 36, 46, 14);
		frame.getContentPane().add(lblDate);
		
		JLabel dateLabel = new JLabel(dateFormat.format(date));
		dateLabel.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		dateLabel.setBounds(48, 36, 73, 14);
		frame.getContentPane().add(dateLabel);
		
		JLabel lblNotifications = new JLabel("Notification Center");
		lblNotifications.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNotifications.setBounds(330, 23, 213, 14);
		frame.getContentPane().add(lblNotifications);
		
	
		JLabel lblNewLabel = new JLabel("License Expirations");
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblNewLabel.setBounds(10, 75, 111, 14);
		frame.getContentPane().add(lblNewLabel);
		
		
		
		
		JLabel lblPolicyRenewals = new JLabel("Policy Renewals");
		lblPolicyRenewals.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblPolicyRenewals.setBounds(260, 75, 100, 14);
		frame.getContentPane().add(lblPolicyRenewals);
		panel_1.setPreferredSize(new Dimension(200, 500));
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(260, 96, 235, 258);
		frame.getContentPane().add(scrollPane);
		
		FlowLayout flowLayout2 = (FlowLayout) panel_1.getLayout();
		flowLayout2.setAlignment(FlowLayout.LEFT);
		
		FlowLayout flowLayout  = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel_1.setBackground(Color.WHITE);
		scrollPane.setViewportView(panel_1);
		panel .setPreferredSize(new Dimension(200, 500));
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 96, 235, 258);
		frame.getContentPane().add(scrollPane_1);
		
		
		panel.setBackground(Color.WHITE);
		scrollPane_1.setViewportView(panel);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(505, 96, 235, 129);
		frame.getContentPane().add(scrollPane_2);
		
		FlowLayout flowLayout3  = (FlowLayout) panel_2.getLayout();
		flowLayout3.setAlignment(FlowLayout.LEFT);
		panel_2.setPreferredSize(new Dimension(200,500));
		panel_2.setBackground(Color.WHITE);
		scrollPane_2.setViewportView(panel_2);
		
		JLabel lblFitnessExpirations = new JLabel("Fitness Expirations");
		lblFitnessExpirations.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblFitnessExpirations.setBounds(505, 76, 106, 14);
		frame.getContentPane().add(lblFitnessExpirations);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(505, 236, 235, 194);
		frame.getContentPane().add(tabbedPane);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		tabbedPane.addTab("Birthdays", null, scrollPane_3, null);
		
		FlowLayout flowLayout4  = (FlowLayout) panel_3.getLayout();
		flowLayout4.setAlignment(FlowLayout.LEFT);
		panel_3.setBackground(Color.WHITE);
		panel_3.setPreferredSize(new Dimension(200,500));
		scrollPane_3.setViewportView(panel_3);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		tabbedPane.addTab("Registration Certification", null, scrollPane_4, null);
		
		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_4.setViewportView(scrollPane_5);
		
		FlowLayout flowLayout5  = (FlowLayout) panel_4.getLayout();
		flowLayout5.setAlignment(FlowLayout.LEFT);
		panel_4.setBackground(Color.WHITE);
		panel_4.setPreferredSize(new Dimension(200,500));
		scrollPane_5.setViewportView(panel_4);
		
		JButton signOut = new JButton("Sign Out");
		signOut.setToolTipText("Sign out of CCConsole\r\n");
		signOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int result = JOptionPane.showConfirmDialog(null, 
						   "Are you sure you wish to sign out?",null, JOptionPane.YES_NO_OPTION);
						if(result == JOptionPane.YES_OPTION) {
							SignIn signin = new SignIn();
							signedOut = true;
							signin.frame.setVisible(true);
							frame.setVisible(false);
						} 
					
			}
		});
		signOut.setBounds(622, 21, 89, 23);
		frame.getContentPane().add(signOut);
		
		JButton btnNewButton = new JButton("Refresh");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refresh();
			}
		});
		btnNewButton.setBounds(340, 48, 89, 23);
		frame.getContentPane().add(btnNewButton);
		frame.setVisible(true);
		
	
		
		
		
		
			}
}
