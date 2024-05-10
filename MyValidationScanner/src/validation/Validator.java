package validation;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;

import annotations.GreaterThan;
import annotations.LessThan;
import annotations.NotNull;
import annotations.Range;
import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner;
import io.github.lukehutch.fastclasspathscanner.scanner.ScanResult;

public class Validator 
{
	private HashMap<Class, ValidationHandler> map = new HashMap<>();
	
	
	public Validator() throws Exception
	{
		// init map by scanning
		ScanResult results = new FastClasspathScanner("validation").scan();		
		List<String> allResults = results.getNamesOfClassesWithAnnotation(ValidationAnnotation.class);
		System.out.println(allResults);
		for (String s : allResults)
		{
			Class c = Class.forName(s);
			ValidationAnnotation va = (ValidationAnnotation) c.getAnnotation(ValidationAnnotation.class);				
			System.out.println(va);				
			map.put(va.target(), (ValidationHandler) c.newInstance());
		}
	
	}
	
	public Validator(String path) throws Exception
	{
		// init map by scanning
		ScanResult results = new FastClasspathScanner("validation", path).scan();		
		List<String> allResults = results.getNamesOfClassesWithAnnotation(ValidationAnnotation.class);
		System.out.println(allResults);
		for (String s : allResults)
		{
			Class c = Class.forName(s);
			ValidationAnnotation va = (ValidationAnnotation) c.getAnnotation(ValidationAnnotation.class);				
			System.out.println(va);				
			map.put(va.target(), (ValidationHandler) c.newInstance());
		}
	
	}
	
	
	public void validate(Object o) throws Exception
	{
		Class clazz = o.getClass();
		for (Field f: clazz.getDeclaredFields())
		{
			Annotation[] alist = f.getAnnotations();
			for (Annotation a : alist)
			{
				ValidationHandler vh = map.get(a.annotationType());
				if (vh!=null)
				{
					vh.process(o, f);
				}
			}
		}
	}

	

	

	
	
	
	
}
