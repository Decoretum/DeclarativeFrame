

import processor.CreateProcessor;
import realdb.GhettoJdbcBlackBox;

public class CreateTester {

	
	public static void main(String[] args) throws Exception {
		
		
		GhettoJdbcBlackBox jdbc = new GhettoJdbcBlackBox();
		jdbc.init("com.mysql.cj.jdbc.Driver", 
				  "jdbc:mysql://localhost/softwareengineering", 
				  "root",
				  "Cubil97823");

		// FIRST PUT ANNOTATION IN USER.java
		
		// create the USER table
		
		String sql = new CreateProcessor().create(User.class);
		System.out.println(sql);
		
		jdbc.runSQL(sql);
		
		
		
		
	}

}
