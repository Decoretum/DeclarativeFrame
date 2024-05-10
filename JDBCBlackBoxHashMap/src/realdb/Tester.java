package realdb;

import java.util.HashMap;
import java.util.List;

public class Tester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub


		GhettoJdbcBlackBox jdbc = new GhettoJdbcBlackBox();
		jdbc.init("com.mysql.cj.jdbc.Driver", 
				"jdbc:mysql://localhost:3306/softwareengineering",   // assumes a DB called createtabletest exists
				"root", 		// username
				"Cubil97823");			// password
		
		try
		{
		  
// 		  hard coded Select
			
		  List<HashMap<String, Object>> results = jdbc.runSQLQuery("SELECT * from REGISTRATION");
			
	      for (HashMap<String, Object> result : results)
	      {
	    	  for (String key : result.keySet())  // keySet() gives the list of keys/column names
	    	  {
	    		  System.out.println(key+" = "+result.get(key)); // use get() to retrieve the contents of the column
	    	  }
	    	  
	    	  System.out.println();
	      }
	      
	      
		}
		catch(Exception e)
		{
			
		}
	     
		
		
	}

}
