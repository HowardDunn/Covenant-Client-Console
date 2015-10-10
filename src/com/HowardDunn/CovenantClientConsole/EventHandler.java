package com.HowardDunn.CovenantClientConsole;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import com.qt.datapicker.DatePicker;

public class EventHandler implements ActionListener{

	public static boolean filtered = false;
	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		//System.out.println(event.getActionCommand());
		
		if(event.getActionCommand().equals("Sign In") || event.getActionCommand().equals("Go")){
			
			SQL.message = "";
			SignIn.message.setText("");
			SignIn.signIntoCCConsole();
			
		}
		
		
	
		else if (event.getActionCommand().equals("vClientList")){
			
			ClientList client = new ClientList();
			client.table.removeAll();
			for(int i = 0; i < client.model.getRowCount(); i++){
				
				client.model.removeRow(i);
			}
			
			client.frmCovenantClientConsole.setVisible(true);
			
		}
		
		else if (event.getActionCommand().equals("createClientSubmit")){
		
			
			Thread t1 = new Thread(new Runnable() {
			     public void run() {
			          // code goes here.
			    	 MainWindow.lblSubmittingInformation.setVisible(true);	
			    	 try {
							
					        
							MainWindow.callClientSubmit();
							
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, "Please Check your entries date format mm/dd/yy", "InfoBox: " + "ERROR", JOptionPane.INFORMATION_MESSAGE);
							
						}
			    	 MainWindow.lblSubmittingInformation.setVisible(false);
			     }
			});  
			t1.start();
		
			
		}
		
		else if (event.getActionCommand().equals("filter")){
			
			
		//	ClientList.frmCovenantClientConsole.setVisible(false);
			ClientList.model.setRowCount(0);
	//		ClientList client = new ClientList();
			
		//	client.frmCovenantClientConsole.setVisible(true);
			
			if(!filtered){
				
				SQL.filterTable();
				ClientList.teller.setText("Showing your Clients");
			}
			else{
				
				SQL.PopulateTable();
				ClientList.teller.setText("Showing All Clients");
			}
			
			filtered = !filtered;
			
		}
		
		
		else if (event.getActionCommand().equals("updateClient")){
			
			int result = JOptionPane.showConfirmDialog(null, 
					   "Are you sure you wish to update this client's information?",null, JOptionPane.YES_NO_OPTION);
					if(result == JOptionPane.YES_OPTION) {
						
						 Thread t1 = new Thread(new Runnable(){
							
							@Override
							public void run() {
								// TODO Auto-generated method stub
								ClientList.updatingInfo.setVisible(true);
								ClientList.updateClients();
								ClientList.updatingInfo.setVisible(false);
							}
						 });
						 t1.start();
					} 
				
			
			
		}
		
		else if (event.getActionCommand().equals("deleteClient")){
			
			int result = JOptionPane.showConfirmDialog(null, 
					   "Are you sure you wish to delete this client?",null, JOptionPane.YES_NO_OPTION);
					if(result == JOptionPane.YES_OPTION) {
						if(result == JOptionPane.YES_OPTION) {
							
							 Thread t1 = new Thread(new Runnable(){
								
								@Override
								public void run() {
									// TODO Auto-generated method stub
									ClientList.updatingInfo.setVisible(true);
									ClientList.updatingInfo.setText("Deleting Client Info...");
									ClientList.deleteClient();
									ClientList.updatingInfo.setVisible(false);
									ClientList.updatingInfo.setText("Updating Client Database...");
								}
							 });
							 
							 t1.start();
							
						} 
						
					} 
			
		
		
	}
		
		else if (event.getActionCommand().equals("createClient")){
			
			MainWindow window = new MainWindow();
			window.frame.setVisible(true);
		}
		
		else if(event.getActionCommand().equals("selectDateBirthDate")){
			try{
				//	UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					//UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
				UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
					}
					catch(Exception e){
					}
			
			DatePicker dp = new DatePicker((Observer) MainWindow.birthDate);
			Date selectedDate = dp.parseDate(MainWindow.birthDate.getText());
		
			dp.setSelectedDate(selectedDate);
			dp.start(MainWindow.birthDate);
			
		}
		else if(event.getActionCommand().equals("selectDatePolicyRenewal")){
			try{
				//	UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					//UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
				UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
					}
					catch(Exception e){
					}
			
			DatePicker dp = new DatePicker((Observer) MainWindow.polRenewal);
			Date selectedDate = dp.parseDate(MainWindow.polRenewal.getText());
		
			dp.setSelectedDate(selectedDate);
			dp.start(MainWindow.polRenewal);
			
		}
		else if(event.getActionCommand().equals("selectDateLicenseExpiry")){
			try{
				//	UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					//UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
					UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
					}
					catch(Exception e){
					}
			DatePicker dp = new DatePicker((Observer) MainWindow.licExpiration);
			Date selectedDate = dp.parseDate(MainWindow.licExpiration.getText());
		
			try{
				//	UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					//UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
					UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
					}
					catch(Exception e){
					}
			dp.setSelectedDate(selectedDate);
			
			dp.start(MainWindow.licExpiration);
			
		}
		else if(event.getActionCommand().equals("selectDateFitnessExpiry")){
			try{
				//	UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					//UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
					UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
					}
					catch(Exception e){
					}
			DatePicker dp = new DatePicker((Observer) MainWindow.fitExpiration);
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date selectedDate = dp.parseDate(MainWindow.fitExpiration.getText());
			
			
	
			dp.setSelectedDate(selectedDate);
			dp.start(MainWindow.fitExpiration);
	
}
		else if(event.getActionCommand().equals("selectDateRegCert")){
			try{
				//	UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					//UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
					UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
					}
					catch(Exception e){
					}
			DatePicker dp = new DatePicker((Observer) MainWindow.regCert);
			Date selectedDate = dp.parseDate(MainWindow.regCert.getText());
			
	
			dp.setSelectedDate(selectedDate);
			dp.start(MainWindow.regCert);
	
}
		
		else if(event.getActionCommand().equals("selectDatePolicyRenewal2")){
			try{
				//	UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					//UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
				UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
					}
					catch(Exception e){
					}
			
			DatePicker dp = new DatePicker((Observer) ClientList.polRenewal);
			Date selectedDate = dp.parseDate(ClientList.polRenewal.getText());
		
			dp.setSelectedDate(selectedDate);
			dp.start(ClientList.polRenewal);
			
		}
		else if(event.getActionCommand().equals("selectDateLicenseExpiry2")){
			try{
				//	UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					//UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
					UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
					}
					catch(Exception e){
					}
			DatePicker dp = new DatePicker((Observer) ClientList.licExpiration);
			Date selectedDate = dp.parseDate(ClientList.licExpiration.getText());
		
			try{
				//	UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					//UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
					UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
					}
					catch(Exception e){
					}
			dp.setSelectedDate(selectedDate);
			
			dp.start(ClientList.licExpiration);
			
		}
		else if(event.getActionCommand().equals("selectDateFitnessExpiry2")){
			try{
				//	UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					//UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
					UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
					}
					catch(Exception e){
					}
			DatePicker dp = new DatePicker((Observer) ClientList.fitExpiration);
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date selectedDate = dp.parseDate(ClientList.fitExpiration.getText());
			
		
	
			dp.setSelectedDate(selectedDate);
			dp.start(ClientList.fitExpiration);
	
}
		else if(event.getActionCommand().equals("selectDateRegCert2")){
			try{
				//	UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					//UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
					UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
					}
					catch(Exception e){
					}
			DatePicker dp = new DatePicker((Observer) ClientList.regCert);
			Date selectedDate = dp.parseDate(ClientList.regCert.getText());
			
		
			dp.setSelectedDate(selectedDate);
			dp.start(ClientList.regCert);
	
}
		else if(event.getActionCommand().equals("selectDateBirthDate2")){
			try{
				//	UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					//UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
					UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
					}
					catch(Exception e){
					}
			DatePicker dp = new DatePicker((Observer) ClientList.birthday);
			Date selectedDate = dp.parseDate(ClientList.birthday.getText());
			
			
			dp.setSelectedDate(selectedDate);
			dp.start(ClientList.birthday);
	
}
		
	}

}
