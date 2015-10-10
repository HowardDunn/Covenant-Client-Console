package com.HowardDunn.CovenantClientConsole;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;


public class SQL {
	
//	static String host ="jdbc:mysql://localhost/covenant";
//	static String username = "dadunn95";
//	static String password = "Jam290995";
	static String host ="jdbc:mysql://68.178.143.43/CCCconsole";
	static String username = "CCCconsole";
	static String password = "Jam290995#";
	public static String userFname;
	public static String userLname;
	public static String message = "";
	public static boolean signedIn = false;
	public static boolean adminPrivilege = false;
	public static Date today = new Date();
	public static	Date tomorrow = new Date(today.getTime() + (1000 * 60 * 60 * 24));
	public static	List<String> licenseNumbers = new ArrayList<String>();
	public static	List<String> policyNumbers = new ArrayList<String>();
	public static	List<String> fitnessNumbers = new ArrayList<String>();
	public static	List<String> birthdayNumbers = new ArrayList<String>();
	public static	List<String> regcertNumbers = new ArrayList<String>();
	public static String employeeId = " " ;
	public static String lNumber,pNumber,fNumber,bNumber,rNumber;
	
	public static void connection(){
		
		try{
			
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Connection to Driver Successful");
			
		}
		
		catch(ClassNotFoundException e){
			
			e.printStackTrace();
		}
	}
	public static void getNotificationsToday(){
		
		
		connection();
		
		
		try {
			Connection connect = DriverManager.getConnection(host,username,password);
			System.out.println("Connection to Database Successful");
			try{
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			
			
			PreparedStatement statement = (PreparedStatement) connect.prepareStatement("SELECT * from clients WHERE license_expiration='" + dateFormat.format(today) +"'");
				
				 
			ResultSet data = statement.executeQuery();
			while(data.next()){
			
			String notif = 	data.getObject("firstname") + " " +  data.getObject("lastname") +"'s license expires today";
			String number = data.getObject("firstname") + " " +  data.getObject("lastname") + ": " + data.getObject("telephone");
			licenseNumbers.add(number);
			lNumber = number;
			
		//	System.out.print( data.getObject("firstname")+" ");
		//	System.out.println( notif);
			
			Menu.addNotificationLicense(notif);
	         
			
	       
	         
			}
		
			statement.close();
			
			}
			catch(MySQLIntegrityConstraintViolationException e){
				
				System.out.println("Failed to get data");
			}
			
			try{
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
			PreparedStatement statement = (PreparedStatement) connect.prepareStatement("SELECT * from clients WHERE fitness_expiration='" + dateFormat.format(today) +"'");
				
				 
			ResultSet data = statement.executeQuery();
		
			while(data.next()){
			
			String notif = 	data.getObject("firstname") + " " +  data.getObject("lastname") +"'s fitness expires today";
			String number = data.getObject("firstname") + " " +  data.getObject("lastname") + ": " + data.getObject("telephone");
			fitnessNumbers.add(number);
			fNumber = number;
		//	System.out.print( data.getObject("firstname")+" ");
		//	System.out.println( notif);
			
			Menu.addNotificationFitness(notif);
	         
			
	       
	         
			}
		
			statement.close();
			
			
			}
			catch(MySQLIntegrityConstraintViolationException e){
				
				System.out.println("Failed to get data");
			}
			
			try{
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				DateFormat dateFormat2 = new SimpleDateFormat("MM-dd");
			
			
			PreparedStatement statement = (PreparedStatement) connect.prepareStatement("SELECT * from clients  WHERE MONTH(birthday) = MONTH(NOW()) AND DAY(birthday) = DAY(NOW())");
				
				 
			ResultSet data = statement.executeQuery();
			while(data.next()){
			
				if(dateFormat2.format(data.getObject("birthday")).equals(dateFormat2.format(today))){
			String notif = 	data.getObject("firstname") + " " +  data.getObject("lastname") +"'s birthday is today";
			String number = data.getObject("firstname") + " " +  data.getObject("lastname") + ": " + data.getObject("telephone");
			birthdayNumbers.add(number);
			bNumber = number;
		//	System.out.print( data.getObject("firstname")+" ");
		//	System.out.println( notif);
			
			Menu.addNotificationBirthday(notif);
	         
				}
	       
	         
			}
		
			statement.close();
			
			}
			catch(MySQLIntegrityConstraintViolationException e){
				
				System.out.println("Failed to get data");
			}
			
			try{
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			
			
			PreparedStatement statement = (PreparedStatement) connect.prepareStatement("SELECT * from clients WHERE registration_cert='" + dateFormat.format(today) +"'");
				
				 
			ResultSet data = statement.executeQuery();
			while(data.next()){
			
			String notif = 	data.getObject("firstname") + " " +  data.getObject("lastname") +"'s cert expires today";
			String number = data.getObject("firstname") + " " +  data.getObject("lastname") + ": " + data.getObject("telephone");
			regcertNumbers.add(number);
			rNumber = number;
		//	System.out.print( data.getObject("firstname")+" ");
		//	System.out.println( notif);
			
			Menu.addNotificationRegistration(notif);
		
	         
			}
			statement.close();
			
			}
			catch(MySQLIntegrityConstraintViolationException e){
				
				System.out.println("Failed to get data");
			}
			
			try{
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			
			
			PreparedStatement statement = (PreparedStatement) connect.prepareStatement("SELECT * from clients WHERE policy_expiration='" + dateFormat.format(today) +"'");
				
				 
			ResultSet data = statement.executeQuery();
			while(data.next()){
			
			String notif = 	data.getObject("firstname") + " " +  data.getObject("lastname") +"'s policy expires today";
			String number = data.getObject("firstname") + " " +  data.getObject("lastname") + ": " + data.getObject("telephone");
			policyNumbers.add(number);
			pNumber = number;
		//	System.out.print( data.getObject("firstname")+" ");
		//	System.out.println( notif);
			
			Menu.addNotificationPolicy(notif);
		
	         
			}
			statement.close();
			connect.close();
			}
			catch(MySQLIntegrityConstraintViolationException e){
				
				System.out.println("Failed to get data");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			
		} 
	}
	
	public static void creatClient(String firstName, String lastName, String birthday, String policyExpiration, String licExpiration, String fitExpiration, 
			String email, String telephone, String regCert, String balance) throws ParseException{

		connection();
		try {
			Connection connect = DriverManager.getConnection(host,username,password);
			System.out.println("Connection to Database Successful");
			
			try{
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				DateFormat dateFormat2 = new SimpleDateFormat("MM/dd/yy");
		
				
			PreparedStatement statement = (PreparedStatement) connect.prepareStatement("INSERT INTO clients(firstname,lastname,birthday,"
					+ "policy_expiration,license_expiration,fitness_expiration,email,telephone,registration_cert,balance,employee) VALUES(?,?,?,?,?,?,?,?,?,?,?)" );
				
			Date bdate = dateFormat2.parse(birthday);
			System.out.println(bdate);
			Date pdate = dateFormat2.parse(policyExpiration);
			Date ldate = dateFormat2.parse(licExpiration);
			Date fdate = dateFormat2.parse(fitExpiration);
			Date rdate = dateFormat2.parse(regCert);
				 
			statement.setString(1,firstName);
			statement.setString(2,lastName);
			statement.setString(3,dateFormat.format(bdate));
			statement.setString(4,dateFormat.format(pdate));
			statement.setString(5,dateFormat.format(ldate));
			statement.setString(6,dateFormat.format(fdate));
			statement.setString(7,email);
			statement.setString(8,telephone);
			statement.setString(9,dateFormat.format(rdate));
			statement.setString(10,balance);
			statement.setString(11,employeeId);
		
		    statement.executeUpdate();
			
		statement.close();
		connect.close();
		MainWindow.clearEntries();
			}
			catch(MySQLIntegrityConstraintViolationException e){
				
				System.out.println("Failed to submit data");
			}
		}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "Please Check your entries, could not submit data", "InfoBox: " + "ERROR", JOptionPane.INFORMATION_MESSAGE);
				e.printStackTrace();
				
			} 
	}
	
	public static void updateClient(String firstName, String lastName, String birthday, String policyExpiration, String licExpiration, String fitExpiration, 
			String email, String telephone, String regCert, String balance,String ID) throws ParseException{

		connection();
		try {
			Connection connect = DriverManager.getConnection(host,username,password);
			System.out.println("Connection to Database Successful");
			
			try{
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				DateFormat dateFormat2 = new SimpleDateFormat("MM/dd/yy");
		
				Date bdate = dateFormat2.parse(birthday);
				
				Date pdate = dateFormat2.parse(policyExpiration);
				Date ldate = dateFormat2.parse(licExpiration);
				Date fdate = dateFormat2.parse(fitExpiration);
				Date rdate = dateFormat2.parse(regCert);
				String sqlQuery = "";
				if(!adminPrivilege){
				 sqlQuery = String.format("UPDATE clients SET firstname='%s',lastname='%s',birthday='%s',"
						+ "policy_expiration='%s',license_expiration='%s',fitness_expiration='%s',email='%s',telephone='%s',registration_cert='%s',balance='%s' WHERE ID='%s' AND employee='%s'", firstName,
						lastName,dateFormat.format(bdate),dateFormat.format(pdate),dateFormat.format(ldate),dateFormat.format(fdate),email,telephone,dateFormat.format(rdate),balance,ID,employeeId);
				}
				else{
					
					 sqlQuery = String.format("UPDATE clients SET firstname='%s',lastname='%s',birthday='%s',"
							+ "policy_expiration='%s',license_expiration='%s',fitness_expiration='%s',email='%s',telephone='%s',registration_cert='%s',balance='%s' WHERE ID='%s'", firstName,
							lastName,dateFormat.format(bdate),dateFormat.format(pdate),dateFormat.format(ldate),dateFormat.format(fdate),email,telephone,dateFormat.format(rdate),balance,ID);
				}
				System.out.println(sqlQuery);
			PreparedStatement statement = (PreparedStatement) connect.prepareStatement(sqlQuery );
				
			
				 

			
		
		    statement.executeUpdate();
			
		statement.close();
		connect.close();
		if(employeeId.equals( ClientList.table.getModel().getValueAt(ClientList.rowSelected, 11)) || adminPrivilege){
		JOptionPane.showMessageDialog(null, "Successfully updated client information !", "InfoBox: " + "Information Successfully Submitted", JOptionPane.INFORMATION_MESSAGE);
		ClientList.frmCovenantClientConsole.setVisible(false);
		ClientList.model.setRowCount(0);
		ClientList client = new ClientList();
		Menu.refresh();
		client.frmCovenantClientConsole.setVisible(true);

		}
		else{
			JOptionPane.showMessageDialog(null, "You do not have admin privileges to edit a client that is not yours !", "InfoBox: " + "Information Successfully Submitted", JOptionPane.INFORMATION_MESSAGE);
			
		}
	
			}
			catch(MySQLIntegrityConstraintViolationException e){
				
				System.out.println("Failed to submit data");
			}
		}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "Please Check your entries, could not submit data", "InfoBox: " + "ERROR", JOptionPane.INFORMATION_MESSAGE);
				e.printStackTrace();
				
			} 
		
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			}
			catch(Exception e){
				
			}
	}
	
	
	public static void deleteClient(String firstName, String lastName, String birthday, String policyExpiration, String licExpiration, String fitExpiration, 
			String email, String telephone, String regCert, String balance,String ID) throws ParseException{

		connection();
		try {
			Connection connect = DriverManager.getConnection(host,username,password);
			System.out.println("Connection to Database Successful");
			
			try{
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				DateFormat dateFormat2 = new SimpleDateFormat("MM/dd/yy");
		
				Date bdate = dateFormat2.parse(birthday);
				
				Date pdate = dateFormat2.parse(policyExpiration);
				Date ldate = dateFormat2.parse(licExpiration);
				Date fdate = dateFormat2.parse(fitExpiration);
				Date rdate = dateFormat2.parse(regCert);
				String sqlQuery = "";
				if(!adminPrivilege){
				 sqlQuery = String.format("DELETE FROM clients WHERE ID='%s' AND employee='%s' AND birthday='%s'", ID,employeeId,dateFormat.format(bdate));
				}
				else{
					
					 sqlQuery = String.format("DELETE FROM clients WHERE ID='%s' AND birthday='%s'", ID,dateFormat.format(bdate));
				}
				System.out.println(sqlQuery);
			PreparedStatement statement = (PreparedStatement) connect.prepareStatement(sqlQuery );
				
			
				 

			
		
		    statement.executeUpdate();
			
		statement.close();
		connect.close();
		if(employeeId == ClientList.table.getModel().getValueAt(ClientList.rowSelected, 11) || adminPrivilege){
		JOptionPane.showMessageDialog(null, "Successfully deleted client  !", "InfoBox: " + "Client Successfully Deleted", JOptionPane.INFORMATION_MESSAGE);
		ClientList.frmCovenantClientConsole.setVisible(false);
		ClientList.model.setRowCount(0);
		ClientList client = new ClientList();
		
		Menu.refresh();
		client.frmCovenantClientConsole.setVisible(true);

		}
		else{
			JOptionPane.showMessageDialog(null, "You do not have admin privileges to delete a client that is not yours !", "InfoBox: " + "Permission Error !", JOptionPane.INFORMATION_MESSAGE);
			
		}
	
			}
			catch(MySQLIntegrityConstraintViolationException e){
				
				System.out.println("Failed to delete data");
			}
		}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "Please Check your entries, could not delete data", "InfoBox: " + "ERROR", JOptionPane.INFORMATION_MESSAGE);
				e.printStackTrace();
				
			} 
		
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			}
			catch(Exception e){
				
			}
	}
	
public static void PopulateTable(){
		
		connection();
		ClientList.table.removeAll();
	    
		try {
			Connection connect = DriverManager.getConnection(host,username,password);
			System.out.println("Connection to Database Successful");
			
			try{
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				DateFormat dateFormat2 = new SimpleDateFormat("MM/dd/yy");
		
				
			PreparedStatement statement = (PreparedStatement) connect.prepareStatement("SELECT * from clients" );
			ResultSet data = statement.executeQuery();	
			
			while(data.next()){
				
			  String[] row = {(String) data.getObject("firstname"),(String) data.getObject("lastname"),
						(String) data.getObject("email"),(String) data.getObject("telephone"),String.valueOf(data.getObject("balance")),
						dateFormat2.format(data.getObject("policy_expiration")),dateFormat2.format(data.getObject("license_expiration")),dateFormat2.format(data.getObject("fitness_expiration")),dateFormat2.format(data.getObject("registration_cert")),
						dateFormat2.format(data.getObject("birthday")), String.valueOf(data.getObject("ID")),(String) data.getObject("employee")};
 
				ClientList.model.addRow(row);
				
			
		         
				}
		
		
		    
			
		statement.close();
		connect.close();
		
			}
			catch(MySQLIntegrityConstraintViolationException e){
				
				System.out.println("Failed to get data");
			}
		}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "Failed to get Data", "InfoBox: " + "ERROR", JOptionPane.INFORMATION_MESSAGE);
				e.printStackTrace();
				
			} 
		
	}
	public static void filterTable(){
		
		connection();
		ClientList.table.removeAll();
	    
		try {
			Connection connect = DriverManager.getConnection(host,username,password);
			System.out.println("Connection to Database Successful");
			
			try{
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				DateFormat dateFormat2 = new SimpleDateFormat("MM/dd/yy");
		
				
			PreparedStatement statement = (PreparedStatement) connect.prepareStatement("SELECT * from clients" );
			ResultSet data = statement.executeQuery();	
			
			while(data.next()){
				
			  String[] row = {(String) data.getObject("firstname"),(String) data.getObject("lastname"),
						(String) data.getObject("email"),(String) data.getObject("telephone"),String.valueOf(data.getObject("balance")),
						dateFormat2.format(data.getObject("policy_expiration")),dateFormat2.format(data.getObject("license_expiration")),dateFormat2.format(data.getObject("fitness_expiration")),dateFormat2.format(data.getObject("registration_cert")),
						dateFormat2.format(data.getObject("birthday")), String.valueOf(data.getObject("ID")),(String) data.getObject("employee")};
 
			  
			  if(employeeId.equals((String) data.getObject("employee"))){
				ClientList.model.addRow(row);
			  }
			
		         
				}
		
		
		    
			
		statement.close();
		connect.close();
		
			}
			catch(MySQLIntegrityConstraintViolationException e){
				
				System.out.println("Failed to get data");
			}
		}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "Failed to get Data", "InfoBox: " + "ERROR", JOptionPane.INFORMATION_MESSAGE);
				e.printStackTrace();
				
			} 
		
	}
	
	public static void getNotificationsTomorrow(){
		
		
		connection();
		
		
		try {
			Connection connect = DriverManager.getConnection(host,username,password);
			System.out.println("Connection to Database Successful");
			try{
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				
		
			
			PreparedStatement statement = (PreparedStatement) connect.prepareStatement("SELECT * from clients WHERE license_expiration='" + dateFormat.format(tomorrow) +"'");
				
				 
			ResultSet data = statement.executeQuery();
			while(data.next()){
			
			String notif = 	data.getObject("firstname") + " " +  data.getObject("lastname") +"'s license expires tomorrow";
			String number = data.getObject("firstname") + " " +  data.getObject("lastname") + ": " + data.getObject("telephone");
			licenseNumbers.add(number);
			lNumber = number;
		//	System.out.print( data.getObject("firstname")+" ");
		
			
			Menu.addNotificationLicense(notif);
		
	         
			}
			statement.close();
		
			}
			catch(MySQLIntegrityConstraintViolationException e){
				
				System.out.println("Failed to get data");
			}
			
			try{
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			
			
			PreparedStatement statement = (PreparedStatement) connect.prepareStatement("SELECT * from clients WHERE fitness_expiration='" + dateFormat.format(tomorrow) +"'");
				
				 
			ResultSet data = statement.executeQuery();
			while(data.next()){
			
			String notif = 	data.getObject("firstname") + " " +  data.getObject("lastname") +"'s fitness expires tomorrow";
			String number = data.getObject("firstname") + " " +  data.getObject("lastname") + ": " + data.getObject("telephone");
			fitnessNumbers.add(number);
			fNumber = number;
		//	System.out.print( data.getObject("firstname")+" ");
		//	System.out.println( notif);
			
			Menu.addNotificationFitness(notif);
		
	         
			}
			statement.close();
		
			}
			catch(MySQLIntegrityConstraintViolationException e){
				
				System.out.println("Failed to get data");
			}
			
			try{
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				DateFormat dateFormat2 = new SimpleDateFormat("MM-dd");
			
			
			PreparedStatement statement = (PreparedStatement) connect.prepareStatement("SELECT * from clients WHERE birthday='" + dateFormat.format(tomorrow) +"'");
				
				 
			ResultSet data = statement.executeQuery();
			while(data.next()){
				if(dateFormat2.format(data.getObject("birthday")).equals(dateFormat2.format(today))){
			String notif = 	data.getObject("firstname") + " " +  data.getObject("lastname") +"'s birthday is tomorrow";
			String number = data.getObject("firstname") + " " +  data.getObject("lastname") + ": " + data.getObject("telephone");
			birthdayNumbers.add(number);
			bNumber = number;
		//	System.out.print( data.getObject("firstname")+" ");
		//	System.out.println( notif);
			
			Menu.addNotificationBirthday(notif);
				}
	         
			}
			statement.close();
		
			}
			catch(MySQLIntegrityConstraintViolationException e){
				
				System.out.println("Failed to get data");
			}
			try{
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				
			
			PreparedStatement statement = (PreparedStatement) connect.prepareStatement("SELECT * from clients WHERE registration_cert='" + dateFormat.format(tomorrow) +"'");
				
				 
			ResultSet data = statement.executeQuery();
			while(data.next()){
			
			String notif = 	data.getObject("firstname") + " " +  data.getObject("lastname") +"'s cert expires tomorrow";
			String number = data.getObject("firstname") + " " +  data.getObject("lastname") + ": " + data.getObject("telephone");
			regcertNumbers.add(number);
			rNumber = number;
		//	System.out.print( data.getObject("firstname")+" ");
		//	System.out.println( notif);
			
			Menu.addNotificationRegistration(notif);
		
	         
			}
			statement.close();
		    
			}
			catch(MySQLIntegrityConstraintViolationException e){
				
				System.out.println("Failed to get data");
			}
			
			
			try{
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				
			
			PreparedStatement statement = (PreparedStatement) connect.prepareStatement("SELECT * from clients WHERE policy_expiration='" + dateFormat.format(tomorrow) +"'");
				
				 
			ResultSet data = statement.executeQuery();
			while(data.next()){
			
			String notif = 	data.getObject("firstname") + " " +  data.getObject("lastname") +"'s policy expires tomorrow";
			String number = data.getObject("firstname") + " " +  data.getObject("lastname") + ": " + data.getObject("telephone");
			policyNumbers.add(number);
			pNumber = number;
		//	System.out.print( data.getObject("firstname")+" ");
		//	System.out.println( notif);
			
			Menu.addNotificationPolicy(notif);
		
	         
			}
			statement.close();
		    connect.close();
			}
			catch(MySQLIntegrityConstraintViolationException e){
				
				System.out.println("Failed to get data");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			
		} 
	}
	
	
	public static void CreateUser(String user, String pass,String email){
		
		connection();
		//String host ="jdbc:mysql://localhost/test";
		//String username = "root";
	//	String password = "";
		
		boolean userNameTaken = false;
		
		
	
		
		try {
			Connection connect = DriverManager.getConnection(host,username,password);
			
				
			System.out.println("Connection to Database Successful");
			/*
			try{
				PreparedStatement statement = (PreparedStatement) connect.prepareStatement("SELECT * from users WHERE username='" + user + "'");
					
					 
				ResultSet data = statement.executeQuery();
				while(data.next()){
				
		         System.out.println("UserName Already taken");
					userNameTaken = true;
				}
				statement.close();
				connect.close();
				}
				catch(MySQLIntegrityConstraintViolationException e){
					
					//System.out.println(" UserName is already in use");
				}
			if(!userNameTaken){*/
			try{
			//PreparedStatement statement = (PreparedStatement) connect.prepareStatement("INSERT INTO members(user,password,name,surname,email) VALUES(?,?,?,?,?)");
				PreparedStatement statement = (PreparedStatement) connect.prepareStatement("INSERT INTO users(username,password,email) VALUES(?,?,?)");
				
				
				statement.setString(1,user);
				statement.setString(2,pass);
				statement.setString(3,email);
			
			    statement.executeUpdate();
				
			statement.close();
			connect.close();
			}
			catch(MySQLIntegrityConstraintViolationException e){
				//System.out.println("i am in here");
				System.out.println(" UserName is already in use");
			 }
			//}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			System.out.println(e.getMessage());
			if(e.getMessage().substring(e.getMessage().length()-2).equals("r'")){
				
				System.out.println("Username Already Exists");
			}
			
			else if(e.getMessage().substring(e.getMessage().length()-2).equals("Y'")){
				
				System.out.println("Email Already in use");
			}
			
			//e.printStackTrace();
		}
	}
	
	public static void SignIn(String user, String pass){
		
		connection();
		
		
		try {
			Connection connect = DriverManager.getConnection(host,username,password);
			System.out.println("Connection to Database Successful");
			try{
			PreparedStatement statement = (PreparedStatement) connect.prepareStatement("SELECT * from users WHERE username='" + user + "' && password='"+ pass+"'");
				
				 
			ResultSet data = statement.executeQuery();
			while(data.next()){
				
			userFname =	(String) data.getObject("firstname");
			userLname = (String) data.getObject("lastname");
			String check = (String)data.getObject("adminPrivilege");
			if(check.equals("yes")){
				
				adminPrivilege = true;
			}
				
	         System.out.println("SignedIn");
	         signedIn = true;
	       employeeId = user;
	         message = "Sign In successful";
	        
			}
			if(!signedIn){
				
				System.out.println("Failed To Sign In");
				message = "Failed To Sign In";
				
			}
			statement.close();
			connect.close();
			}
			catch(MySQLIntegrityConstraintViolationException e){
				
				System.out.println("Please check your information and try again");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			
		} 
		
		//SignIn.lblNewLabel_1.setVisible(false);
	}
	
	
		
		public static void insertTamData(String policyIdx, String Branch, String policyDescription, String policyType, String company, String policyNum, 
				String effectiveDate, String expirationDate, String premiumCost, String premiumBalance, String CSR,  String policyStatus,  String clientCode) throws ParseException{

			
			try {
				Connection connect = DriverManager.getConnection(host,username,password);
				System.out.println("Connection to Database Successful");
				
				try{
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					DateFormat dateFormat2 = new SimpleDateFormat("MM/dd/yyyy");
			
					Date date = dateFormat2.parse(effectiveDate);
					Date date2 = dateFormat2.parse(expirationDate);
					
				PreparedStatement statement = (PreparedStatement) connect.prepareStatement("INSERT INTO policy(policyIdx,Branch,policyDescription,policyType,"
						+ "company,policyNum,effectiveDate,expirationDate,premiumCost,premiumBalance,CSR,policyStatus,clientCode) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)" );
					
			
					 
				statement.setString(1,policyIdx);
				statement.setString(2,Branch);
				statement.setString(3,policyDescription);
				statement.setString(4,policyType);
				statement.setString(5,company);
				statement.setString(6,policyNum);
				statement.setString(7,dateFormat.format(date));
				statement.setString(8,dateFormat.format(date2));
				statement.setString(9,premiumCost);
				statement.setString(10,premiumBalance);
				statement.setString(11,CSR);
				statement.setString(12,policyStatus);
				statement.setString(13,clientCode);
			
			    statement.executeUpdate();
				
			statement.close();
			connect.close();

				}
				catch(MySQLIntegrityConstraintViolationException e){
					
					System.out.println("Failed to submit data");
				}
			}
				catch (SQLException e) {
					// TODO Auto-generated catch block
					//JOptionPane.showMessageDialog(null, "Please Check your entries, could not submit data", "InfoBox: " + "ERROR", JOptionPane.INFORMATION_MESSAGE);
					e.printStackTrace();
					
				} 
		}
	
		public static void updateTamData(String policyIdx, String Branch, String policyDescription, String policyType, String company, String policyNum, 
				String effectiveDate, String expirationDate, String premiumCost, String premiumBalance, String CSR,  String policyStatus,  String clientCode) throws ParseException{

			if(!policyNum.equals("")){
			connection();
			try {
				Connection connect = DriverManager.getConnection(host,username,password);
				System.out.println("Connection to Database Successful");
				
				try{
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					DateFormat dateFormat2 = new SimpleDateFormat("MM/dd/yyyy");
			
					Date date1 = dateFormat2.parse(effectiveDate);
					Date date2 = dateFormat2.parse(expirationDate);
					
					
					String sqlQuery = "";
					
					
					 sqlQuery = String.format("UPDATE policy SET policyIdx='%s',Branch='%s',policyDescription='%s',"
							+ "policyType='%s',company='%s',effectiveDate='%s',expirationDate='%s',premiumCost='%s',"
							+ "premiumBalance='%s',CSR='%s',policyStatus='%s',clientCode='%s' WHERE policyNum='%s'", policyIdx,Branch,policyDescription,policyType,company,dateFormat.format(date1),dateFormat.format(date2),premiumCost,premiumBalance,CSR,policyStatus,clientCode,policyNum);
			
				PreparedStatement statement = (PreparedStatement) connect.prepareStatement(sqlQuery );
					
				
					 

				
			
			    statement.executeUpdate();
				
			statement.close();
			connect.close();
				
		
				}
				catch(MySQLIntegrityConstraintViolationException e){
					
					System.out.println("Failed to submit data");
				}
			}
				catch (SQLException e) {
					// TODO Auto-generated catch block
				//	JOptionPane.showMessageDialog(null, "Please Check your entries, could not submit data", "InfoBox: " + "ERROR", JOptionPane.INFORMATION_MESSAGE);
					e.printStackTrace();
					
				} 
			}
			try{
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				}
				catch(Exception e){
					
				}
		}
	public static void main(String args[]){
		Scanner scan = new Scanner(System.in);
		//CreateUser("Kayla","Dunn","kaykit95@gmail.com");
		String name, surname,email,user,password;
		System.out.print("Enter your desired username: ");
		user = scan.next();
		System.out.print("Enter your desired password: ");
		password = scan.next();
		
		//System.out.print("Enter your email: ");
		//email = scan.next();
		
		SignIn(user,password);
		//SignIn("dadunn95@gmail.com");
	}

}
