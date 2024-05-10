package orm;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import annotations.Column;
import annotations.Entity;
import annotations.MappedClass;
import annotations.Param;
import annotations.Select;
import realdb.GhettoJdbcBlackBox;

public class DaoInvocationHandler implements InvocationHandler {

	static GhettoJdbcBlackBox jdbc;
	
	public DaoInvocationHandler() {
		// TODO Auto-generated constructor stub
		
		if (jdbc==null)
		{
			jdbc = new GhettoJdbcBlackBox();
			jdbc.init("com.mysql.cj.jdbc.Driver", 				// DO NOT CHANGE
					  "jdbc:mysql://localhost/softwareengineering",    // change jdbcblackbox to the DB name you wish to use
					  "root", 									// USER NAME
					  "Cubil97823");										// PASSWORD
		}
		
		
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		//Get mapped class
		Class declaringClass = method.getDeclaringClass();
		MappedClass mc = null;
		if (declaringClass.isAnnotationPresent(MappedClass.class)) {
			mc = (MappedClass) declaringClass.getAnnotation(MappedClass.class);	//mc.clazz() -> User.class
		}
		
		String methodName = method.getName();

		if (methodName.equals("createTable")) {
			createTable(method);
		} else if (methodName.equals("save")) {
			save(method, args[0]);
		} else if (methodName.equals("delete")){
			delete(method, args[0]);
		}  else if (methodName.equals("getById") || methodName.equals("getAll") || methodName.equals("getByFirstNameAndLastName")) {
			return select(method, args);
		}
		
		
		
		return null;
	}
	
	
	
	// handles @CreateTable
	private void createTable(Method method)
	{
		
// 		SAMPLE SQL 		
//	    CREATE TABLE REGISTRATION (id INTEGER not NULL AUTO_INCREMENT,
//												first VARCHAR(255), 
//												last VARCHAR(255), age INTEGER, PRIMARY KEY ( id ))
 		String sqlString = null;
 		
		// get the Mapped class 
		Class reqClass = method.getDeclaringClass();
		MappedClass mc = (MappedClass) reqClass.getAnnotation(MappedClass.class);
		
		// get entity class inside mapped
		Class<?> entity = mc.clazz();
		
		System.out.println("Creating Table for " + entity.getName().replace("dao.", ""));
		
		sqlString = "CREATE TABLE " + entity.getName().replace("entity.", "") + " (";
		
		// use reflection to check all the fields for @Column
		for (Field f : entity.getDeclaredFields()) {
			String fieldString = null;
			if (f.isAnnotationPresent(Column.class)) {
				
				// use the @Column attributed to generate the required sql statment
				Column c = f.getAnnotation(Column.class);
				if (c.id() == true) {
					fieldString = c.name() + " " + c.sqlType() + " PRIMARY KEY" + ", ";
				} else {
					fieldString = c.name() + " " + c.sqlType() + ", ";
				}
				
			}
			
			sqlString += fieldString;
		}
		
		sqlString += ")";
		sqlString = sqlString.replace(", )", ")");
		System.out.println(sqlString);
		
// 		Run the sql
		 jdbc.runSQL(sqlString);
		 System.out.println("Table " + entity.getName() + " created successfully!");
	}
	
	
	
	// handles @Delete
	private void delete(Method method, Object o) throws Exception //Object = Entity class
	{
// 		SAMPLE SQL		
//  	DELETE FROM REGISTRATION WHERE ID=1
		
		//Get entity class
		Class<?> dc = method.getDeclaringClass(); //mappedclass
		MappedClass mc = dc.getAnnotation(MappedClass.class);
		Class<?> entity = mc.clazz(); //Entity Class
		Entity e = entity.getAnnotation(Entity.class);
		String tableName = e.table();
		String sqlString = "";
		HashMap<Object, Object> hm = new HashMap<>();
		
		sqlString = "DELETE FROM " + tableName;
		
		//Get ID
		Field idField = o.getClass().getDeclaredField("id");
		idField.setAccessible(true);
		Object id = idField.get(o);
		
		if (id == null) {
			throw new RuntimeException("no pk value");
		}
		
		//DB id name
		Column c1 = idField.getAnnotation(Column.class);
		String dbID = c1.name();


		// run the sql
		sqlString += " WHERE " + dbID + " = " + id;
		sqlString = sqlString.replace(", WHERE", " WHERE");
		
//		System.out.println(sqlString);
		jdbc.runSQL(sqlString);
		System.out.println("Entity " + entity.getName() + " has been deleted on identifier = " + id);
	}
	
	// handles @Save
	private void save(Method method, Object o) throws Exception //Object = entity
	{
 		// Using the @MappedClass annotation from method
		// get the required class 		
		Class<?> reqClass = method.getDeclaringClass(); //mapper class
		MappedClass mc = reqClass.getAnnotation(MappedClass.class);
		Class<?> entity = mc.clazz(); //Entity Class
		Entity e = entity.getAnnotation(Entity.class);
		boolean changed = false;
		
		ArrayList<Object> values = new ArrayList<>();
		
		// use reflection to check all the fields for @Column
		String pk = "";
		
		for (Field f : entity.getDeclaredFields()) {
			// for the Object o parameter, get the value of the field
			f.setAccessible(true);
			Field field = o.getClass().getDeclaredField(f.getName());
			field.setAccessible(true);
			Object fieldValue = field.get(o);
			
			if (fieldValue == null) {
				changed = true;
			}
			
			// find which field is the primary key
			Column c = f.getAnnotation(Column.class);
			if (c.id() == true) {
				pk = c.name();
			}
		}
		
		// if the field is null run the insert(Object o, Class entityClass, String tableName) method
		// if the field is not null run the update(Object o, Class entityClass, String tableName) method
		if (changed == true) {
			insert(o, entity, e.table());
		} else {
			update(o, entity, e.table());
		}


	}

	private void insert(Object o, Class entityClass, String tableName) throws Exception 
	{
		
		
// 		SAMPLE SQL		
//		INSERT INTO table_name (column1, column2, column3, ...)
//		VALUES (value1, value2, value3, ...)	

//		HINT: columnX comes from the entityClass, valueX comes from o 
		Class<?> entity = entityClass; //Entity Class
		String iString = "";
		String eString = "";
		
		iString = "INSERT INTO " + tableName + "(";
		
		//Column addition
		for (Field f : entity.getDeclaredFields()) {
			String fieldString = "";
			
			if (f.isAnnotationPresent(Column.class)) {
				Column c = f.getAnnotation(Column.class);
				if (c.id() == false) {
					fieldString = c.name() + ", ";	
				}
					
			}
			iString += fieldString;
		}
		
		iString += ")";
		iString = iString.replace(", )", ")");
		
		eString += "VALUES(";
		//Addition of Values 
		for (Field f : entity.getDeclaredFields()) {
			if (f.isAnnotationPresent(Column.class)) {
			Column c = f.getAnnotation(Column.class);
			String fString = "";
			
			// for the Object o parameter, get the value of the field
			f.setAccessible(true);
			Field field = o.getClass().getDeclaredField(f.getName());
			field.setAccessible(true);
			Object fieldValue = field.get(o);
			String fieldName = field.getName();
			
			if (c.sqlType().equals("INTEGER")) {
				fString += fieldValue + ", ";
			}
			
			else if (!fieldName.equals("id")) {
				fString += "'" + fieldValue + "'" + ", ";
			}
			
			
			eString += fString;
			}
		}
		
		eString += ")";
		eString = eString.replace(", )", ")");
		
		// run sql		
//		System.out.println(iString + " " + eString);
		jdbc.runSQL(iString + " " + eString);
		System.out.println(entity.getName() + " has been created!");
	}

	private void update(Object o, Class entityClass, String tableName) throws IllegalAccessException, Exception {

//		SAMPLE SQL		
//		UPDATE table_name
//		SET column1 = value1, column2 = value2, ...
//		WHERE condition;
		
		// Assumes all fields are present
		// condition = id
		
//		HINT: columnX comes from the entityClass, valueX comes from o
		Class<?> entity = entityClass; //Entity Class
		String sqlString = "";
		HashMap<Object, Object> hm = new HashMap<>();
		
		sqlString = "UPDATE " + tableName + " " + "SET ";
		
		//Get ID
		Field idField = o.getClass().getDeclaredField("id");
		idField.setAccessible(true);
		Object id = idField.get(o);
		
		//DB id name
		Column c1 = idField.getAnnotation(Column.class);
		String dbID = c1.name();
		
		//Iterate fields
		for (Field f : entity.getDeclaredFields()) {
			Field field = o.getClass().getDeclaredField(f.getName());
			field.setAccessible(true);
			String fieldName = field.getName();
			Column c = f.getAnnotation(Column.class);

			//Put column name and corresponding value
			if (c.id() == false) {
				Object col = c.name();
				Object value = field.get(o);
				
				if (!c.sqlType().equals("INTEGER")) {
					hm.put(col, "'" + value + "'");
				} else {
					hm.put(col, value);
				}
			
			}
			
		}
		
		System.out.println(hm);
		//Stringing things together
		for (Object i : hm.keySet()) {
			Object col = i;
			Object val = hm.get(i);
			sqlString += col + " = " + val + ", ";
		}
		
		sqlString += "WHERE " + dbID + " = " + id;
		sqlString = sqlString.replace(", WHERE", " WHERE");
				
//		// run sql		
		jdbc.runSQL(sqlString);
		System.out.println(entity.getName() + " has been updated on identifier = " + id);

	}

		
	// handles @Select
	private Object select(Method method, Object[] args) throws Exception
	{
	
 		// Using the @MappedClass annotation from method to get entity
		Class<?> dec = method.getDeclaringClass();
		MappedClass mc = dec.getAnnotation(MappedClass.class);
		Class<?> entity = mc.clazz(); //entity
		Entity e = entity.getAnnotation(Entity.class);
		String tableName = e.table();
		String idName = "";
		String sqlString = "";
		
		if (method.getName().equals("getById")) {
			
			if (method.isAnnotationPresent(Select.class)) {
				Select s = method.getAnnotation(Select.class);
				sqlString = s.value();
			}
			
			
			Object o = args[0];
			
			//Get Id
			for (Field f : entity.getDeclaredFields()) {
				Column c = f.getAnnotation(Column.class);
				if (c.id() == true) {
					idName = c.name();
					break;
				}
			}
			
			
			for (int i = 0; i < method.getParameters().length; i++)
			{
				Parameter p = method.getParameters()[i];
				if(p.isAnnotationPresent(Param.class)) {
					Param param = (Param) p.getAnnotation(Param.class);
					String pValue = param.value();
					sqlString = sqlString.replaceAll(":table", tableName);
					sqlString = sqlString.replaceAll(":"+pValue, String.valueOf(args[i]));
				}

				
			}
		}
		
		else if (method.getName().equals("getByFirstNameAndLastName"))
		{
			Select s = method.getAnnotation(Select.class);
			sqlString = s.value();
			sqlString = sqlString.replace(":table", tableName);
			
			for (int i = 0; i < method.getParameters().length; i++)
			{
				Parameter p = method.getParameters()[i];
				if(p.isAnnotationPresent(Param.class)) {
					Param param = (Param) p.getAnnotation(Param.class);
					String pValue = param.value();
					sqlString = sqlString.replaceAll(":"+pValue, "'" + String.valueOf(args[i]) + "'");
				}

				
			}
		}
		
		else 
		{
			Select s = method.getAnnotation(Select.class);
			sqlString = s.value();
			sqlString = sqlString.replaceAll(":table", tableName);
		}
		
		System.out.println(sqlString);
		// this will pull actual values from the DB		
		List<HashMap<String, Object>> results = jdbc.runSQLQuery(sqlString);

		// process list based on getReturnType
		if (method.getName().equals("getAll"))
		{
			List returnValue = new ArrayList<>();
			for (HashMap<String, Object> result : results) {
				
				// create an instance for each entry in results based on mapped class
				Object newI = entity.getConstructor().newInstance(); //new instance of entity class
				
				for (String columnName : result.keySet()) {			
					for (Field f : mc.clazz().getDeclaredFields()) {
						if (f.isAnnotationPresent(Column.class)){
							Column c = f.getAnnotation(Column.class);
							if (c.name().equals(columnName)) {
								f.setAccessible(true);
								f.set(newI, result.get(columnName));
								break;
							}
						}
					}
					
				}
				returnValue.add(newI);
			}
			System.out.println("Returning a List");
			return returnValue;
		} 
		
		else
		{
			if (results.size() == 0) return null;
			if (results.size() > 1) throw new RuntimeException("More than one object matches");
			if (results.size() >1) throw new RuntimeException("More than one object matches");
			if (results.size() == 1) 
			{
				HashMap<String, Object> result = results.get(0);
				Object o = mc.clazz().getConstructor().newInstance();
				if (method.getName().equals("getByFirstNameAndLastName"))
				{
					for (String cName : result.keySet())
					{
						for (Field f : mc.clazz().getDeclaredFields()) {
							if (f.isAnnotationPresent(Column.class)){
								Column c = f.getAnnotation(Column.class);
								if (c.name().equals(cName)) {
									f.setAccessible(true);
									f.set(o, result.get(cName));
									break;
								}
							}
						}
					}
					return o;
				}
				
				else 
				{
					for (String columnName : result.keySet())
					{
						
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
				System.out.println("Returning an object");
				return o;	
				}
				
			}
			
		}
		return null;
	}
	
}
