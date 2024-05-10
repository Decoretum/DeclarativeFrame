package orm;

import java.util.HashMap;
import java.util.List;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Proxy;

import annotations.*;
import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner;
import io.github.lukehutch.fastclasspathscanner.scanner.ScanResult;
import dao.*;




public class MyORM 
{	
	
	HashMap<Class, Class> entityToMapperMap = new HashMap<Class, Class>();
	
	
	public void init() throws Exception
	{
		// scan all mappers -- @MappedClass
		scanMappers();		
		
		// scan all the entities -- @Entity
		scanEntities();
				
		// create all entity tables
		createTables();

	}


	public void scanMappers() throws Exception 
	{
		// use FastClasspathScanner to scan the dao package for @MappedClass
		
		ScanResult results = new FastClasspathScanner("dao").scan();		
		List<String> allResults = results.getNamesOfClassesWithAnnotation(annotations.MappedClass.class);

		for (String s : allResults)
		{	
		    Class<?> daoclass = Class.forName(s);
		    MappedClass va = daoclass.getAnnotation(MappedClass.class); //annotation
		    
		    if (va == null) {
		        throw new RuntimeException("Class " + daoclass.getName() + " does not have @MappedClass annotation");
		    }
		    
		    Class<?> clazz = va.clazz(); //Getting interface class value within annotation
		    String className = clazz.getName();
		    System.out.println("This is the mapped class under" + daoclass.getName() + ": " + className);

		    //Check if the clazz has the @Entity on it throw an Exception if not
		    
		    if(clazz.isAnnotationPresent(Entity.class)) {
		    	System.out.println("MappedClass " + className + " has @Entity annotation");
		    } else {
		    	throw new Exception(className + " has no @Entity annotation");
		    }
		    
		    //Map the clazz to the class of the interface in the supplied entityToMapperMap		    
		    entityToMapperMap.put(clazz, daoclass);
		}
	}
	

	public void scanEntities() throws ClassNotFoundException 
	{
		// use FastClasspathScanner to scan the entity package for @Entity

		ScanResult results = new FastClasspathScanner("entity").scan();
		List<String> entityClasses = results.getNamesOfClassesWithAnnotation("annotations.Entity"); //entity.student, entity.subject
		
		// go through each of the fields 

		for (String className : entityClasses) {
		    Class<?> clazz = Class.forName(className);

		    // Get all declared fields of the class
		    Field[] fields = clazz.getDeclaredFields();
		    int idCount = 0;

		    for (Field field : fields) {
		        // Check if the field has the @Column annotation
		        if (field.isAnnotationPresent(Column.class)) {
		            Column columnAnnotation = field.getAnnotation(Column.class);
		            if (columnAnnotation.id()) {
		                idCount++;
		            }
		        }
		    }
		    
		 // check if there is only 1 field with a Column id attribute
		 // if more than one field has id throw new RuntimeException("duplicate id=true")
		    if (idCount > 1) {
		        throw new RuntimeException("Duplicate id=true found in class: " + clazz.getName());
		    }
		}
		
	}
	
	
	public Object getMapper(Class<?> clazz) throws Exception // <context>mapper.class
	{
		// create the proxy object for the mapper class supplied in clazz parameter
		// all proxies will use the supplied DaoInvocationHandler as the InvocationHandler
		
		try {
			
	        // Assuming all mappers are named with "Mapper" suffix
	        Class<?> mapperClass = Class.forName(clazz.getName()); //mapper interface containing annotation with entity
	        System.out.println(mapperClass.getName());
	        //Create Proxy out of this
	        Object proxy =  Proxy.newProxyInstance(
	        		ClassLoader.getSystemClassLoader(), 
	        		new Class[] { mapperClass }, 
	        		new DaoInvocationHandler());

	        return proxy;
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }


	}
	

	private void createTables()
	{
		// go through all the Mapper classes in the map
			
		for (Class<?> mapperClass : entityToMapperMap.values()) {
	        try {
	        	// create a proxy instance for each
	        	// all these proxies can be casted to BasicMapper
	        	BasicMapper proxy = (BasicMapper) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), 
		        		new Class[] {mapperClass}, new DaoInvocationHandler());
	        	
	            // Check if it's an instance of BasicMapper
	            if (proxy instanceof BasicMapper) {
	                BasicMapper<?> basicMapper = (BasicMapper<?>) proxy;
	                
	                // run the createTable() method on each of the proxies
	                basicMapper.createTable();
	            } else {
	                throw new RuntimeException("Mapper class does not implement BasicMapper interface");
	            }
	        } catch (Exception e) {
	        	System.out.println("There is an exception with the createTables() method");
	            e.printStackTrace();
	        }
	    }
		
	}

	

	
	
}
