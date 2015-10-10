package com.HowardDunn.CovenantClientConsole;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Window;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JFormattedTextField;

import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Observer;

import javax.swing.JButton;

import com.qt.datapicker.DatePicker;

import javax.swing.ImageIcon;

import org.jdatepicker.JDatePanel;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.UtilDateModel;
import java.awt.Toolkit;
import javax.swing.JProgressBar;

public class MainWindow {

	public static JFrame frame;
	private static JTextField fName;
	private static JTextField lName;
	private JLabel lblCreateClient;
	private static JTextField email;
	private static JTextField tele;
	private static JTextField balance;
	public static ObservingTextField polRenewal;
	public static JButton btnSelectPolicyRenewal;
	public static ObservingTextField fitExpiration;
	private JButton btnFitExp;
	public static ObservingTextField licExpiration;
	private JButton btnLicExp;
	public static ObservingTextField birthDate;
	private JButton btnBithDate;
	public static ObservingTextField regCert;
	private JButton btnRegCert;
	private JButton Submit;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	public static JLabel proglabel;
	public static JProgressBar progressBar;
	public static JLabel lblSubmittingInformation;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					try{
						UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					//	UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
						//UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
						}
						catch(Exception e){
						}
					
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}
	
	public static void callClientSubmit() throws ParseException{

		
		SQL.creatClient(fName.getText(), lName.getText(), birthDate.getText(), polRenewal.getText(), licExpiration.getText(), fitExpiration.getText(),
				email.getText(), tele.getText(), regCert.getText(), balance.getText());
	}
	
	public static void clearEntries(){
		
		fName.setText(null); lName.setText(null); birthDate.setText(null); polRenewal.setText(null); licExpiration.setText(null); fitExpiration.setText(null);
		email.setText(null); tele.setText(null); regCert.setText(null); balance.setText(null);
		try{
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				//UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
			//UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
				}
				catch(Exception e){
				}
		
		Menu.panel.removeAll();
		Menu.panel_1.removeAll();
		Menu.panel_2.removeAll();
		Menu.panel_3.removeAll();
		Menu.panel_4.removeAll();
		
		SQL.getNotificationsToday();
		SQL.getNotificationsTomorrow();
		JOptionPane.showMessageDialog(null, "Successfully Submitted Information", "InfoBox: " + "Client Successfully Created", JOptionPane.INFORMATION_MESSAGE);
		frame.setVisible(false);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("res\\covenantlogo.png"));
	
		frame.setBounds(100, 100, 641, 338);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Covenant Client Console");
		fName = new JTextField();
		fName.setBounds(75, 43, 148, 20);
		frame.getContentPane().add(fName);
		fName.setColumns(10);
		
		
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblFirstName.setBounds(10, 44, 88, 17);
		frame.getContentPane().add(lblFirstName);
		
		lName = new JTextField();
		lName.setBounds(75, 74, 148, 20);
		frame.getContentPane().add(lName);
		lName.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblLastName.setBounds(10, 76, 62, 14);
		frame.getContentPane().add(lblLastName);
		
		lblCreateClient = new JLabel("CREATE CLIENT");
		lblCreateClient.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		lblCreateClient.setBounds(261, 11, 103, 14);
		frame.getContentPane().add(lblCreateClient);
		
		email = new JTextField();
		email.setBounds(75, 105, 148, 20);
		frame.getContentPane().add(email);
		email.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblEmail.setBounds(10, 107, 46, 14);
		frame.getContentPane().add(lblEmail);
		
		tele = new JTextField();
		tele.setBounds(75, 136, 148, 20);
		frame.getContentPane().add(tele);
		tele.setColumns(10);
		
		JLabel lblTelephone = new JLabel("Telephone:");
		lblTelephone.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblTelephone.setBounds(10, 138, 62, 14);
		frame.getContentPane().add(lblTelephone);
		
		balance = new JTextField();
		balance.setBounds(75, 166, 148, 20);
		frame.getContentPane().add(balance);
		balance.setColumns(10);
		
		JLabel lblBalance = new JLabel("Balance:");
		lblBalance.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblBalance.setBounds(10, 168, 46, 14);
		frame.getContentPane().add(lblBalance);
		
		
		EventHandler handler = new EventHandler();
		btnSelectPolicyRenewal = new JButton("");
		ImageIcon img = new ImageIcon();
		Image im = null;
		try {
			
			im = ImageIO.read(new File("res\\calendar.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		img.setImage(im.getScaledInstance(23, 23, java.awt.Image.SCALE_SMOOTH));
		btnSelectPolicyRenewal.setIcon(img);
	
		btnSelectPolicyRenewal.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnSelectPolicyRenewal.setActionCommand("selectDatePolicyRenewal");
		btnSelectPolicyRenewal.addActionListener(handler);
		btnSelectPolicyRenewal.setBounds(566, 42, 30, 23);
		frame.getContentPane().add(btnSelectPolicyRenewal);
		

		
		polRenewal = new ObservingTextField();
		polRenewal.setBounds(408, 42, 148, 20);
		frame.getContentPane().add(polRenewal);
		polRenewal.setColumns(10);
		
		fitExpiration = new ObservingTextField();
		fitExpiration.setBounds(408, 73, 148, 20);
		frame.getContentPane().add(fitExpiration);
		fitExpiration.setColumns(10);
		
		btnFitExp = new JButton("");
		btnFitExp.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnFitExp.setBounds(566, 73, 30, 23);
		btnFitExp.addActionListener(handler);
		btnFitExp.setActionCommand("selectDateFitnessExpiry");
		btnFitExp.setIcon(img);
		frame.getContentPane().add(btnFitExp);
		
		licExpiration = new ObservingTextField();
		licExpiration.setBounds(408, 104, 148, 20);
		frame.getContentPane().add(licExpiration);
		licExpiration.setColumns(10);
		
		btnLicExp = new JButton("");
		btnLicExp.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnLicExp.setBounds(566, 101, 30, 23);
		btnLicExp.addActionListener(handler);
		btnLicExp.setActionCommand("selectDateLicenseExpiry");
		btnLicExp.setIcon(img);
		frame.getContentPane().add(btnLicExp);
		
		birthDate = new ObservingTextField();
		birthDate.setBounds(408, 135, 148, 20);
		frame.getContentPane().add(birthDate);
		birthDate.setColumns(10);
		
		btnBithDate = new JButton("");
		btnBithDate.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnBithDate.setActionCommand("selectDateBirthDate");
		btnBithDate.addActionListener(handler);
		btnBithDate.setBounds(566, 132, 30, 23);
		btnBithDate.setIcon(img);
		frame.getContentPane().add(btnBithDate);
		
		regCert = new ObservingTextField();
		regCert.setBounds(408, 166, 148, 20);
		frame.getContentPane().add(regCert);
		regCert.setColumns(10);
		
		btnRegCert = new JButton("");
		btnRegCert.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnRegCert.setActionCommand("selectDateRegCert");
		btnRegCert.addActionListener(handler);
		btnRegCert.setBounds(566, 166, 30, 23);
		btnRegCert.setIcon(img);
		frame.getContentPane().add(btnRegCert);
		
		Submit = new JButton("Submit");
		Submit.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		Submit.setBounds(10, 228, 586, 60);
		Submit.setActionCommand("createClientSubmit");
		Submit.addActionListener(handler);
		frame.getContentPane().add(Submit);
		
		lblNewLabel = new JLabel("Policy Renewal: ");
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblNewLabel.setBounds(261, 45, 88, 14);
		frame.getContentPane().add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Fitness Expiration:");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblNewLabel_1.setBounds(261, 76, 99, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("License Expiration: ");
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblNewLabel_2.setBounds(261, 107, 99, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Birth Date: ");
		lblNewLabel_3.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblNewLabel_3.setBounds(261, 138, 88, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Registration Certification:");
		lblNewLabel_4.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblNewLabel_4.setBounds(261, 168, 137, 14);
		frame.getContentPane().add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("date format: mm/dd/yy");
		lblNewLabel_5.setFont(new Font("Segoe UI Light", Font.ITALIC, 11));
		lblNewLabel_5.setBounds(408, 11, 129, 14);
		frame.getContentPane().add(lblNewLabel_5);
		
	   progressBar = new JProgressBar();
		progressBar.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		progressBar.setBounds(77, 18, 146, 14);
		frame.getContentPane().add(progressBar);
		
		 proglabel = new JLabel("Submitting");
		proglabel.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		proglabel.setBounds(10, 19, 62, 14);
		frame.getContentPane().add(proglabel);
		
		lblSubmittingInformation = new JLabel("Submitting Information...");
		lblSubmittingInformation.setIcon(new ImageIcon("res\\ajax-loader.gif"));
		lblSubmittingInformation.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblSubmittingInformation.setBounds(216, 191, 192, 33);
		lblSubmittingInformation.setVisible(false);
		frame.getContentPane().add(lblSubmittingInformation);
		proglabel.setVisible(false);
		progressBar.setVisible(false);
	
		
	}
}
