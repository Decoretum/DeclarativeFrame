

import java.lang.reflect.Field;

import annotations.Column;
import annotations.Table;

public class AnnotationTester 
{
	// Generate a create table SQL statement based on the information in the supplied class
	
	// sample:
	// CREATE TABLE REGISTRATION (id INTEGER not NULL AUTO_INCREMENT, first VARCHAR(255), last VARCHAR(255), age INTEGER,   PRIMARY KEY ( id ))

	
	private String template = "CREATE TABLE <name> (<fields>  PRIMARY KEY ( <pk> ))";
	

	public String createSQL(Class clazz)
	{
		if (clazz.isAnnotationPresent(Table.class))
		{
			Table t = (Table) clazz.getAnnotation(Table.class);
			String tableName = t.value();
			
			String fieldString = "";
			
			String pkColumn = null;
			
			Field[] fields = clazz.getDeclaredFields();
			
			for (Field f : fields)
			{
				if (f.isAnnotationPresent(Column.class))
				{
					Column c = f.getAnnotation(Column.class);
					String name = c.name();
					String sql = c.sql();
					boolean pk = c.primaryKey();
					
					if (pk==true)
					{
						pkColumn = name;
					}
					
					fieldString = fieldString + name + " "+sql+", ";
				}
			}
			
			
			String returnSql = template.replaceAll("<name>", tableName);
			returnSql = returnSql.replaceAll("<fields>", fieldString);
			returnSql = returnSql.replaceAll("<pk>", pkColumn);
						
			
			return returnSql;
			
		}
		else
		{
			throw new RuntimeException("No @Table");
		}
	}
	
	
	public static void main(String[] args)
	{
		AnnotationTester t = new AnnotationTester();
		System.out.println(t.createSQL(Student.class));
		System.out.println(t.createSQL(Registration.class));
	}
}
