import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import annotations.*;

import realdb.GhettoJdbcBlackBox;

public class Handler implements InvocationHandler{

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// TODO Auto-generated method stub
		
		
		// PART I -- use TableCreateWithDB project as a reference
		
		// get @MappedClass
		Class declaringClass = method.getDeclaringClass();
		MappedClass mc = null;
		if (declaringClass.isAnnotationPresent(MappedClass.class)) {
			mc = (MappedClass) declaringClass.getAnnotation(MappedClass.class);	//mc.clazz() -> User.class
		}
		
		String sqlString = null;
		
		// get @Select, replace :table in string
		if (method.isAnnotationPresent(Select.class)) {
			Select s = method.getAnnotation(Select.class);
			sqlString = s.value();
			
			String tableName = null;
			if (mc.clazz().isAnnotationPresent(Table.class)) {
				Table t = (Table) mc.clazz().getAnnotation(Table.class);
				tableName = t.value();
			}
			
			sqlString = sqlString.replaceAll(":table", tableName);
		}
		
		
		
		// get @Param and replace :XXX in select string
		for (int i = 0; i < method.getParameters().length; i++)
		{
			Parameter p = method.getParameters()[i];
			if(p.isAnnotationPresent(Param.class)) {
				Param param = (Param) p.getAnnotation(Param.class);
				String pValue = param.value();
				sqlString = sqlString.replaceAll(":"+pValue, String.valueOf(args[i]));
			}

			
		}
		

		
		
		// PART II - make sure that there is a jdbxblackbox DB present in mysql
		GhettoJdbcBlackBox jdbc = new GhettoJdbcBlackBox();
		jdbc.init("com.mysql.cj.jdbc.Driver", 
				  "jdbc:mysql://localhost/softwareengineering",
				  "root", 
				  "Cubil97823");
		
		
		List<HashMap<String, Object>> results = jdbc.runSQLQuery(sqlString);

		// process list based on getReturnType
			// a List return a list of Objects
			// an Object type will return a single object
		if (method.getReturnType()==List.class)
		{
			List returnValue = new ArrayList();

			for (HashMap<String, Object> result : results)
			{
				Object o = mc.clazz().getConstructor().newInstance(); // User Class, do not use null, make new instance of the class the mappedClass
				
				// go through all the keys in the result
				for (String columnName : result.keySet())
				{
					// look for field that has a matching @Column name attribute 
					// set the field value of this key to the instance field
					// column value can be retrieved using result.get(columnName);
					
					for (Field f : mc.clazz().getDeclaredFields()) {
						if (f.isAnnotationPresent(Column.class)){
							Column c = f.getAnnotation(Column.class);
							if (c.name().equals(columnName)) {
								f.setAccessible(true);
								f.set(o, result.get(columnName));
								break;
							}
						}
					}
				}
				// add instance to the returnValue list
				returnValue.add(o);
			}
			
			return returnValue;
		}
		else
		{
			if (results.size() == 0) {
				return null;
			}
			
			// if the return type is not a List
			// take the first result
			HashMap<String, Object> result = results.get(0);

			Object o = mc.clazz().getConstructor().newInstance(); // User Class, do not use null, make new instance of the class the mappedClass
			
			// go through all the keys in the result
			for (String columnName : result.keySet())
			{
				// look for field that has a matching @Column name attribute 
				// set the field value of this key to the instance field
				// column value can be retrieved using result.get(columnName);
				
				for (Field f : mc.clazz().getDeclaredFields()) {
					if (f.isAnnotationPresent(Column.class)){
						Column c = f.getAnnotation(Column.class);
						if (c.name().equals(columnName)) {
							f.setAccessible(true);
							f.set(o, result.get(columnName));
							break;
						}
					}
				}
			}

			return o;
		}
				
	}

}
