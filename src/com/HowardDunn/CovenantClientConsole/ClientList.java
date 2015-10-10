package com.HowardDunn.CovenantClientConsole;

import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;

import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

public class ClientList {

	public static JFrame frmCovenantClientConsole;
	public static JTable tabl1e;
	public static JTable table;
	public static DefaultTableModel model = new DefaultTableModel(); 
	private JLabel lblClientList;
	private static JTextField fName;
	private static JTextField lName;
	private static JTextField email;
	private static JTextField telephone;
	private static JTextField balance;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	public static ObservingTextField polRenewal;
	private JLabel lblFitnessExpiration;
	private JLabel lblLicenseExpiration;
	private JLabel lblNewLabel_5;
	private JLabel lblRegistrationCertification;
	public static ObservingTextField   fitExpiration;
	public static ObservingTextField  licExpiration;
	public static ObservingTextField  birthday;
	public static ObservingTextField  regCert;
	private static JButton btnDelete;
	private JButton btnSave;
	public static int rowSelected;
	private JButton btnNewButton;
	public static JLabel teller;
	private JButton polRenewal2;
	private JButton fitExpiration2;
	private JButton licExpiration2;
	private JButton birthday2;
	private JButton regCert2;
	private JLabel loading;
	public static JLabel updatingInfo;
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
					ClientList window = new ClientList();
					window.frmCovenantClientConsole.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ClientList() {
		initialize();
		EventHandler.filtered = false;
		Thread t1 = new Thread(new Runnable() {
		     public void run() {
		          // code goes here.
		    	 SQL.PopulateTable(); 
		        System.out.println("Finished loading Client List");
		        loading.setVisible(false);
		     }
		});  
		
		t1.start();
		table.getModel().addTableModelListener(new TableModelListener() {

		      

			@Override
			public void tableChanged(TableModelEvent arg0) {
				// TODO Auto-generated method stub
				if(model.getRowCount() > 0 && table.getRowCount() > 0){
int row = table.getSelectedRow();
    rowSelected=row;
				updateTextFields(row);
				}
			}
		    });
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	public static void deleteClient(){
		
		
		try {
			SQL.deleteClient(fName.getText(), lName.getText(), birthday.getText(), polRenewal.getText(), licExpiration.getText(), fitExpiration.getText(),
					email.getText(), telephone.getText(), regCert.getText(), balance.getText(),String.valueOf(table.getModel().getValueAt(rowSelected,10)));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Please Check your entries, could not delete", "InfoBox: " + "ERROR", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	
	public static void updateClients(){
		
		
		try {
			SQL.updateClient(fName.getText(), lName.getText(), birthday.getText(), polRenewal.getText(), licExpiration.getText(), fitExpiration.getText(),
					email.getText(), telephone.getText(), regCert.getText(), balance.getText(),String.valueOf(table.getModel().getValueAt(rowSelected,10)));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Please Check your entries, could not submit data", "InfoBox: " + "ERROR", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	private void updateTextFields(int row){
		
		try{
		fName.setText((String) table.getModel().getValueAt(row, 0));
		lName.setText((String) table.getModel().getValueAt(row, 1));
		email.setText((String) table.getModel().getValueAt(row, 2));
		telephone.setText((String) table.getModel().getValueAt(row, 3));
		balance.setText((String) table.getModel().getValueAt(row, 4));
		
		polRenewal.setText((String) table.getModel().getValueAt(row, 5));
		fitExpiration.setText((String) table.getModel().getValueAt(row, 7));
		licExpiration.setText((String) table.getModel().getValueAt(row, 6));
		birthday.setText((String) table.getModel().getValueAt(row, 9));
		regCert.setText((String) table.getModel().getValueAt(row, 8));
		}
		
		catch( java.lang.ArrayIndexOutOfBoundsException e){
			
		}
	}
	private void initialize() {
		frmCovenantClientConsole = new JFrame();
		frmCovenantClientConsole.getContentPane().setFont(new Font("Segoe UI", Font.PLAIN, 11));
		frmCovenantClientConsole.setIconImage(Toolkit.getDefaultToolkit().getImage("res\\covenantlogo.png"));
		frmCovenantClientConsole.setTitle("Covenant Client Console");
		frmCovenantClientConsole.setBounds(100, 100, 800, 639);
		frmCovenantClientConsole.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmCovenantClientConsole.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 55, 764, 298);
		frmCovenantClientConsole.getContentPane().add(scrollPane);
		String[] row = {"FirstName","LastName","Email","Telephone","Balance","Pol","Fit","license","reg","birthday"};
		
		
	 
	
		scrollPane.setViewportView(table);
		if(model.getRowCount() > 0){
			
			model = new DefaultTableModel();
			Menu.initializedClientList = false;
		}
		table = new JTable(model);
		
		table.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyPressed(KeyEvent e) {
				
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					if(table.getSelectedRow() < model.getRowCount()-1)
					updateTextFields(table.getSelectedRow()+1);
					else if(table.getSelectedRow() == model.getRowCount()-1)
						updateTextFields(0);
				}
			
				if(e.getKeyCode()==KeyEvent.VK_DOWN){
					if(table.getSelectedRow() < model.getRowCount()-1)
					updateTextFields(table.getSelectedRow()+1);
					else if(table.getSelectedRow() == model.getRowCount()-1)
						updateTextFields(model.getRowCount()-1);
				}
				
				if(e.getKeyCode()==KeyEvent.VK_UP){
					if(table.getSelectedRow() > 0)
					updateTextFields(table.getSelectedRow()-1);
					else if(table.getSelectedRow() == 0)
						updateTextFields(0);
				}
				
			}
		});
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			
			try{
				
				int row = table.getSelectedRow();
				 rowSelected=row;
				System.out.println(table.getModel().getValueAt(row,10));
				updateTextFields(row);
				
				
			}
			catch(Exception e){
				
			}
			
			}
		});
		
		if(Menu.initializedClientList == false){
		model.addColumn("First Name");
		model.addColumn("Last Name");
		model.addColumn("Email");
		model.addColumn("Telephone");
		model.addColumn("Balance");
		model.addColumn("Policy Expiration Date");
		model.addColumn("License Expiration Date");
		model.addColumn("Fitness Expiration Date");
		model.addColumn("Registration Expiration Date");
		model.addColumn("Birthday");
		model.addColumn("ID");
		model.addColumn("employee");
		
		Menu.initializedClientList = true;
		}
		table.removeColumn(table.getColumnModel().getColumn(11));
		table.removeColumn(table.getColumnModel().getColumn(10));
		
		scrollPane.setViewportView(table);
		
		loading = new JLabel("Loading Data Please Wait....");
		loading.setIcon(new ImageIcon("res\\ajax-loader.gif"));
		loading.setBounds(531, 11, 217, 36);
		frmCovenantClientConsole.getContentPane().add(loading);
		
		lblClientList = new JLabel("Client List");
		lblClientList.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		lblClientList.setBounds(344, 11, 90, 33);
		frmCovenantClientConsole.getContentPane().add(lblClientList);
		
		fName = new JTextField();
		fName.setBounds(90, 389, 276, 20);
		frmCovenantClientConsole.getContentPane().add(fName);
		fName.setColumns(10);
		
		lName = new JTextField();
		lName.setBounds(90, 420, 276, 20);
		frmCovenantClientConsole.getContentPane().add(lName);
		lName.setColumns(10);
		
		email = new JTextField();
		email.setBounds(90, 451, 276, 20);
		frmCovenantClientConsole.getContentPane().add(email);
		email.setColumns(10);
		
		telephone = new JTextField();
		telephone.setBounds(90, 482, 276, 20);
		frmCovenantClientConsole.getContentPane().add(telephone);
		telephone.setColumns(10);
		
		balance = new JTextField();
		balance.setBounds(90, 513, 276, 20);
		frmCovenantClientConsole.getContentPane().add(balance);
		balance.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("First Name:");
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblNewLabel.setBounds(10, 395, 70, 14);
		frmCovenantClientConsole.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Last Name:");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblNewLabel_1.setBounds(10, 423, 70, 14);
		frmCovenantClientConsole.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Email:");
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblNewLabel_2.setBounds(10, 457, 70, 14);
		frmCovenantClientConsole.getContentPane().add(lblNewLabel_2);
		
		JLabel lblTelephone = new JLabel("Telephone:");
		lblTelephone.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblTelephone.setBounds(10, 485, 70, 14);
		frmCovenantClientConsole.getContentPane().add(lblTelephone);
		
		lblNewLabel_3 = new JLabel("Balance:");
		lblNewLabel_3.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblNewLabel_3.setBounds(10, 516, 46, 14);
		frmCovenantClientConsole.getContentPane().add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Policy Renewal:");
		lblNewLabel_4.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblNewLabel_4.setBounds(396, 391, 100, 14);
		frmCovenantClientConsole.getContentPane().add(lblNewLabel_4);
		
		polRenewal = new ObservingTextField();
		polRenewal.setBounds(547, 389, 158, 20);
		frmCovenantClientConsole.getContentPane().add(polRenewal);
		polRenewal.setColumns(10);
		
		lblFitnessExpiration = new JLabel("Fitness Expiration:");
		lblFitnessExpiration.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblFitnessExpiration.setBounds(396, 422, 100, 14);
		frmCovenantClientConsole.getContentPane().add(lblFitnessExpiration);
		
		lblLicenseExpiration = new JLabel("License Expiration:");
		lblLicenseExpiration.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblLicenseExpiration.setBounds(396, 453, 100, 14);
		frmCovenantClientConsole.getContentPane().add(lblLicenseExpiration);
		
		lblNewLabel_5 = new JLabel("Birthday:");
		lblNewLabel_5.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblNewLabel_5.setBounds(396, 488, 100, 14);
		frmCovenantClientConsole.getContentPane().add(lblNewLabel_5);
		
		lblRegistrationCertification = new JLabel("Registration Certification:");
		lblRegistrationCertification.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblRegistrationCertification.setBounds(396, 515, 141, 14);
		frmCovenantClientConsole.getContentPane().add(lblRegistrationCertification);
		
		fitExpiration = new ObservingTextField();
		fitExpiration.setBounds(547, 420, 158, 20);
		frmCovenantClientConsole.getContentPane().add(fitExpiration);
		fitExpiration.setColumns(10);
		
		licExpiration = new ObservingTextField();
		licExpiration.setBounds(547, 451, 158, 20);
		frmCovenantClientConsole.getContentPane().add(licExpiration);
		licExpiration.setColumns(10);
		
		birthday = new ObservingTextField();
		birthday.setBounds(547, 482, 158, 20);
		frmCovenantClientConsole.getContentPane().add(birthday);
		birthday.setColumns(10);
		
		regCert = new ObservingTextField();
		regCert.setBounds(547, 513, 158, 20);
		frmCovenantClientConsole.getContentPane().add(regCert);
		regCert.setColumns(10);
		
		EventHandler handler = new EventHandler();
		
		btnDelete = new JButton("Delete Client");
		btnDelete.setIcon(new ImageIcon("res\\delete.png"));
		btnDelete.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnDelete.setBounds(10, 556, 356, 33);
		btnDelete.setActionCommand("deleteClient");
		btnDelete.addActionListener(handler);
		frmCovenantClientConsole.getContentPane().add(btnDelete);
		
		btnSave = new JButton("Update Client Info");
		btnSave.setIcon(new ImageIcon("res\\save.png"));
		btnSave.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnSave.setBounds(396, 556, 356, 33);
		btnSave.setActionCommand("updateClient");
		btnSave.addActionListener(handler);
		frmCovenantClientConsole.getContentPane().add(btnSave);
		
		btnNewButton = new JButton("Filter ");
		btnNewButton.setActionCommand("filter");
		btnNewButton.addActionListener(handler);
		btnNewButton.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnNewButton.setBounds(10, 12, 176, 34);
		frmCovenantClientConsole.getContentPane().add(btnNewButton);
		
		teller = new JLabel("Showing All Clients");
		teller.setFont(new Font("Segoe UI", Font.ITALIC, 11));
		teller.setBounds(341, 38, 125, 14);
		frmCovenantClientConsole.getContentPane().add(teller);
		ImageIcon img = new ImageIcon();
		Image im = null;
		try {
			im = ImageIO.read(new File("res\\calendar.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		img.setImage(im.getScaledInstance(23, 23, java.awt.Image.SCALE_SMOOTH));
		polRenewal2 = new JButton("");
		polRenewal2.setBounds(710, 389, 42, 23);
		frmCovenantClientConsole.getContentPane().add(polRenewal2);
		polRenewal2.setIcon(img);
		
		fitExpiration2 = new JButton("");
		fitExpiration2.setBounds(710, 420, 42, 23);
		frmCovenantClientConsole.getContentPane().add(fitExpiration2);
		fitExpiration2.setIcon(img);
		
		licExpiration2 = new JButton("");
		licExpiration2.setBounds(710, 451, 42, 23);
		frmCovenantClientConsole.getContentPane().add(licExpiration2);
		licExpiration2.setIcon(img);
		
		birthday2 = new JButton("");
		birthday2.setBounds(710, 482, 42, 23);
		frmCovenantClientConsole.getContentPane().add(birthday2);
		birthday2.setIcon(img);
		regCert2 = new JButton("");
		regCert2.setBounds(710, 513, 42, 23);
		frmCovenantClientConsole.getContentPane().add(regCert2);
		regCert2.setIcon(img);
		
		polRenewal2.setActionCommand("selectDatePolicyRenewal2");
		fitExpiration2.setActionCommand("selectDateFitnessExpiry2");
		licExpiration2.setActionCommand("selectDateLicenseExpiry2");
		birthday2.setActionCommand("selectDateBirthDate2");
		regCert2.setActionCommand("selectDateRegCert2");
		
		updatingInfo = new JLabel("Updating Client Database...");
		updatingInfo.setIcon(new ImageIcon("res\\ajax-loader.gif"));
		updatingInfo.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		updatingInfo.setBounds(338, 356, 158, 33);
		updatingInfo.setVisible(false);
		frmCovenantClientConsole.getContentPane().add(updatingInfo);
		
		polRenewal2.addActionListener(handler);
		fitExpiration2.addActionListener(handler);
		licExpiration2.addActionListener(handler);
		birthday2.addActionListener(handler);
		regCert2.addActionListener(handler);
		frmCovenantClientConsole.setVisible(true);
		
	}
}
