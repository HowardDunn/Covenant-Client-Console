package com.HowardDunn.CovenantClientConsole;

import java.util.prefs.*;
public class CovenantClientPreferences {
	
	public static Preferences prefs;
	
	  public void setPreference(String user, String password, boolean autosignin) {
	    // This will define a node in which the preferences can be stored
	    prefs = Preferences.userRoot().node(this.getClass().getName());
	    String ID1 = "user";
	    String ID2 = "password";
	    String ID3 = "autosignin";
	    prefs.put(ID1, user);
	    prefs.put(ID2, password);
	    prefs.putBoolean(ID3, autosignin);
	    // First we will get the values
	    // Define a boolean value
	  
	    // Define a integer with default 50
	  //  System.out.println(prefs.getInt(ID3, 50));

	    // now set the values
	   
	   // prefs.putInt(ID3, 45);

	    // Delete the preference settings for the first value
	   // prefs.remove(ID1);

	  }
	  public  String getPreference(String ID){
		  prefs = Preferences.userRoot().node(this.getClass().getName());
		  
		  
		  
		  return(prefs.get(ID, ""));
	  }
	  
	  public  boolean getPreferenceBool(String ID){
		  prefs = Preferences.userRoot().node(this.getClass().getName());
		  
		  System.out.println(prefs.get(ID, ""));
		  if(prefs.get(ID, "").equals("true")){
		  
		  return(true);
	  }
		  else {
			  
			  return false;
		  }
	  }

	  public static void main(String[] args) {
		 CovenantClientPreferences test = new CovenantClientPreferences();
	
		  
		 System.out.println( test.getPreference("user"));
		//  System.out.println(prefs.getBoolean("Test1",true));
	  }

}
