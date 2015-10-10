package com.HowardDunn.CovenantClientConsole;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.UIManager;

import java.awt.Font;
import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.JCheckBox;

public class SignIn {

	public static JFrame frame;
	private static JTextField userName;
	private static JPasswordField password;
	public static JLabel message = new JLabel("");
	public static JCheckBox setDefault;
	public static CovenantClientPreferences prefer = new  CovenantClientPreferences();
	public static JCheckBox autosignin ;
	public static int signintimes = 0;
	private JLabel loading1;
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
					SignIn window = new SignIn();
					//window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SignIn() {
		initialize();
	}
	


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("res\\covenantlogo.png"));
		frame.setBounds(100, 100, 301, 257);
		frame.setTitle("Covenant Client Console");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		ImageIcon loading = new ImageIcon("res\\ajax-loader.gif");
	    frame.getContentPane().add(new JLabel("loading... ", loading, JLabel.CENTER));
		userName = new JTextField();
		userName.setBounds(104, 70, 116, 20);
		frame.getContentPane().add(userName);
		userName.setColumns(10);
		EventHandler handler = new EventHandler();
		
		
		JButton signIn = new JButton("Sign In");
		signIn.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		signIn.setBounds(104, 194, 116, 23);
		signIn.addActionListener(handler);
		frame.getContentPane().add(signIn);
		
		JLabel lblUsername = new JLabel("username:");
		lblUsername.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblUsername.setBounds(43, 72, 64, 14);
		frame.getContentPane().add(lblUsername);
		
		password = new JPasswordField();
		password.setBounds(104, 114, 116, 20);
		frame.getContentPane().add(password);
		password.setActionCommand("Go");
		password.addActionListener(handler);
		JLabel lblPassword = new JLabel("password:");
		lblPassword.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblPassword.setBounds(43, 116, 64, 14);
		frame.getContentPane().add(lblPassword);
		
		
		 autosignin = new JCheckBox("Automatic Sign in");
		 autosignin.setToolTipText("Sets whether or not you automatically sign in when program starts");
		 autosignin.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		 autosignin.setBounds(104, 141, 116, 23);
		 frame.getContentPane().add(autosignin);
			
		userName.setText(prefer.getPreference("user"));
		password.setText(prefer.getPreference("password"));
		autosignin.setSelected(prefer.getPreferenceBool("autosignin"));
		
		
		message.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		message.setForeground(Color.RED);
		message.setBounds(104, 45, 116, 14);
		frame.getContentPane().add(message);
		message.setText("");
		
		JLabel lblNewLabel = new JLabel("Please sign into the Covenant Client Console");
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblNewLabel.setBounds(28, 28, 239, 14);
		frame.getContentPane().add(lblNewLabel);
		
		setDefault = new JCheckBox("Set default Sign in ");
		setDefault.setSelected(true);
		setDefault.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		setDefault.setBounds(104, 164, 129, 23);
		frame.getContentPane().add(setDefault);
		setDefault.setToolTipText("This sets the default username and password");
		
		loading1 = new JLabel("Signing In\r\n\r\n");
		loading1.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		loading1.setIcon(new ImageIcon("res\\ajax-loader.gif"));
		loading1.setBounds(29, 141, 64, 65);
		loading1.setVisible(false);
		loading1.setHorizontalTextPosition(JLabel.CENTER);
		loading1.setVerticalTextPosition(JLabel.BOTTOM);
		frame.getContentPane().add(loading1);
		
		
		frame.setVisible(true);
		
if(autosignin.isSelected() && signintimes == 0){
			
	Thread t1 = new Thread(new Runnable() {
	     public void run() {
	          // code goes here.
	    	 
	    	 message.setText("");
	    	 loading1.setVisible(true);
			    signIntoCCConsole();
			    loading1.setVisible(false);
	     }
	});  
	t1.start();
			
		}


	}
	
	
	public static void signIntoCCConsole(){
		
	//	 SignIn.lblNewLabel_1.setVisible(true);
		 if(setDefault.isSelected()){
			 
			
			 prefer.setPreference(userName.getText(), password.getText(),autosignin.isSelected());
		 }
		 
		 else{
			 
			 prefer.setPreference(userName.getText(), "",autosignin.isSelected());
		 }
		 message.setText("");
		SQL.SignIn(userName.getText(), password.getText());
		
		if(SQL.signedIn){
			signintimes++;
		Menu menu = new Menu();
		message.setText(SQL.message);
		message.setForeground(Color.GREEN);
		frame.setVisible(false);
		menu.frame.setVisible(true);
		}
		else{
			
	//		 SignIn.lblNewLabel_1.setVisible(false);
			message.setText(SQL.message);
		}
		
	}
}
