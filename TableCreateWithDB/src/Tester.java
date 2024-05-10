import processor.CreateProcessor;
import realdb.GhettoJdbcBlackBox;

public class Tester {

//	 "CREATE TABLE REGISTRATION (id INTEGER not NULL AUTO_INCREMENT,  first VARCHAR(255),  last VARCHAR(255),  age INTEGER,  PRIMARY KEY ( id ))";
	
	
// 1) what is the relevant info? 
// 2) how do you break this to spread the info with annotations in a Java onject?
// 3) how do you extract the info to recreate the original SQL statement 

	
	
	
// How does this fit with Proxies?  This is the code that occurs in your Invocation Handler

	static GhettoJdbcBlackBox jdbc;
	
	
	public static void main(String[] args)
	{
		jdbc = new GhettoJdbcBlackBox();
		jdbc.init("com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost:3306/softwareengineering",
				"root",
				"Cubil97823");

		
		String sql = new CreateProcessor().create(Registration.class);
		System.out.println(sql);
		
		jdbc.runSQL(sql);
		
		sql = new CreateProcessor().create(Student.class);
		System.out.println(sql);

		jdbc.runSQL(sql);

	}
	
}
