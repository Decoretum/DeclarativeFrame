package validation;

import java.lang.reflect.Field;

import annotations.NotNull;

@ValidationAnnotation(target=NotNull.class)
public class NotNullValidator implements ValidationHandler 
{
	public void process(Object o, Field f) throws Exception{
		// check if not a primitive
		if (f.getType().isPrimitive())
		{
			throw new RuntimeException("Cannot be used on a primitive");
		}
		
		f.setAccessible(true);
		
		if (f.get(o)==null)
		{
			throw new RuntimeException(f.getName()+ " cannot be null");			
		}
		
	}
}
