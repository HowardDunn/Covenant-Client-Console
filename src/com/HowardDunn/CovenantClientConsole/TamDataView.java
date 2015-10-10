package com.HowardDunn.CovenantClientConsole;




import java.awt.EventQueue;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TamDataView {

	private JFrame frame;
	public static String helper;
	 public static List <String> myList = new ArrayList<String>();
	 public static DefaultTableModel model = new DefaultTableModel();
	 private JTable table;
	 static boolean done = false; 
	 private static String clientCode;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TamDataView window = new TamDataView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	 public static void JDBFRead()
		        throws Exception
		    {
		        //DBFReader dbfreader = new DBFReader((new URL("http://www.svcon.com/us48st.dbf")).openStream());
		        //DBFReader dbfreader = new DBFReader("F:\\work\\book2.dbf");
		 InputStream is = new FileInputStream("INS.DBF");
	        DBFReader dbfreader = new DBFReader(is);
		        
		        //DBFReader dbfreader = new DBFReader("E:\\hexiongshare\\test.dbf");
		        int i;
		        int size = dbfreader.getFieldCount();
		       
		        String[] row = new String[size];
		        for (i=0; i<dbfreader.getFieldCount(); i++) {
		          System.out.print(dbfreader.getField(i).getName()+"  ");
		        	 helper += dbfreader.getField(i).getName()+"  ";
		        	model.addColumn(dbfreader.getField(i).getName());
		        }
		        
		        myList.add(helper);
		        System.out.print("\n");
		        for(i = 0; dbfreader.hasNextRecord(); i++)
		        {
		            Object aobj[] = dbfreader.nextRecord(Charset.forName("GBK"));
		            helper = "";
		            for (int j=0; j<aobj.length; j++){
		             //System.out.print(aobj[j]+"  |  ");
		            helper += aobj[j]+"  |  ";
		            	row[j] = aobj[j] +"";
		            
		            	}
		            myList.add(helper);
		            model.addRow(row);
		         //   System.out.print("\n");
		        }

		        myList.add("Total Count: " + i);
		        System.out.println("Total Count: " + i);
		    }
	 public static void insertTamDataToSQL()
		        throws Exception
		    {
		 SQL.connection();
		        //DBFReader dbfreader = new DBFReader((new URL("http://www.svcon.com/us48st.dbf")).openStream());
		        //DBFReader dbfreader = new DBFReader("F:\\work\\book2.dbf");
		 InputStream is = new FileInputStream("POLICY2.DBF");
	        DBFReader dbfreader = new DBFReader(is);
		        
		        //DBFReader dbfreader = new DBFReader("E:\\hexiongshare\\test.dbf");
		        int i;
		        int size = dbfreader.getFieldCount();
		       
		        String[] row = new String[size];
		   /*     for (i=0; i<dbfreader.getFieldCount(); i++) {
		          System.out.print(dbfreader.getField(i).getName()+"  ");
		        	 helper += dbfreader.getField(i).getName()+"  ";
		        	model.addColumn(dbfreader.getField(i).getName());
		        } */
		        
		        model.addColumn("Policy Idx");
		        
		        
		        model.addColumn("Branch");
		        model.addColumn("Policy Description");
		        model.addColumn("Policy Type");
		       
		        model.addColumn("Company");
		        
		        model.addColumn("Policy#");
		        model.addColumn("Effective Date");
		        model.addColumn("Expiration Date");
		        model.addColumn("Premium Cost");
		        model.addColumn("Premium Balance");
		        model.addColumn("CSR");
		        model.addColumn("Policy Status");
		        model.addColumn("Client Code");
		        
		        
		       
		        System.out.print("\n");
		        for(i = 0; dbfreader.hasNextRecord(); i++)
		        {
		            Object aobj[] = dbfreader.nextRecord(Charset.forName("GBK"));
		            helper = "";
		            for (int j=0; j<aobj.length; j++){
		             //System.out.print(aobj[j]+"  |  ");
		            helper += aobj[j]+"  |  ";
		            if(j == 0 || j == 2 || j == 4 || j == 6 || j == 8 || j == 10 || j == 11 || j == 12 || j == 13  || j == 23 || j == 27 || j == 31){
		            	
		            	if(j == 11 || j == 12){
		            		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		    				DateFormat dateFormat2 = new SimpleDateFormat("MM/dd/yyyy");
		    		   String dateString = (String) aobj[j];
		    		  	try{
		    		  		
		    		  		String yearChecker = dateString.substring(6, 7) ;
		    		  		int addYear  = Integer.parseInt(dateString.substring(7, 8));
		    		  		if(yearChecker.equals("A") )
		    		  			addYear += 2000;
		    		  		if(yearChecker.equals("B"))
		    		  			addYear += 2010;
		    		  		if(yearChecker.equals("C"))
		    		  			addYear += 2020;
		    		  		if(yearChecker.equals( "D"))
		    		  			addYear += 2030;
		    		  		if(yearChecker.equals("E"))
		    		  			addYear += 2040;
		    		  		if(yearChecker.equals( "F"))
		    		  			addYear += 2050;
		    		  		if(yearChecker.equals( "G"))
		    		  			addYear += 2060;
		    		  		if(yearChecker.equals("H"))
		    		  			addYear += 2070;
		    		  		if(yearChecker.equals( "I"))
		    		  			addYear += 2080;
		    		  		if(yearChecker.equals("J"))
		    		  			addYear += 2090;
		    		  		if(yearChecker.equals("K"))
		    		  			addYear += 2100;
		    		  		dateString = dateString.substring(0, 6);
		    		  		dateString += addYear;
		    		  		
		    		  		
		    		  		myList.add(dateString);
		            	}
		            	catch(java.lang.StringIndexOutOfBoundsException e){
		            		
		            		myList.add("");
		            	}
		    		   
		    		   
		    			//	Date date = dateFormat2.parse(dateString);
		            		
		    			//	myList.add(dateFormat2.format(date));
		            	}
		            	
		            	else{
		            myList.add(aobj[j] +"");
		            	}
		            }
		            
		            if(j==0){
		            	   clientCode = aobj[j] +"";
		               
		           	try{
		           		clientCode = clientCode.substring(0,7 );
	            	
	            	}
	            	catch(java.lang.StringIndexOutOfBoundsException e){
	            		
	            	}
		            }
		               
		               
		            	   
		            }
		            
		            myList.add(clientCode);
		           
		            
		          
		        	if(myList.get(6).equals("") || myList.get(6).equals("1     1"))
		        		myList.set(6, "00/00/0000");
		        	if(myList.get(7).equals("") || myList.get(7).equals("1     1"))
		        		myList.set(7, "00/00/0000");
		        	
		        	
		        	
		        	
		            SQL.updateTamData(myList.get(0),myList.get(1),myList.get(2),myList.get(3),myList.get(4),myList.get(5),myList.get(6),myList.get(7),
		            		myList.get(8),myList.get(9),myList.get(10),myList.get(11),myList.get(12));
		           
		            model.addRow(myList.toArray());
		           myList.clear();
		           // model.addRow(row);
		         //   System.out.print("\n");
		        }

		      //  myList.add("Total Count: " + i);
		        System.out.println("Total Count: " + i);
		    }

	/**
	 * Create the application.
	 */
	public TamDataView() {
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 945, 535);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(0, 0, 929, 496);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane.setViewportView(table);
		try {
			JDBFRead();
		//	insertTamDataToSQL();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
