package processor;

import java.lang.reflect.Field;

import annotations.Column;
import annotations.Table;

public class CreateProcessor 
{
	private String template = "CREATE TABLE <name> (<fields>  PRIMARY KEY ( <pk> ))";
	

	public String create(Class clazz)
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
			
			// simple in-string replacement
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
}
