package validation;

import java.lang.reflect.Field;

import annotations.LessThan;

@ValidationAnnotation(target=LessThan.class)
public class LessThanValidator implements ValidationHandler {

	public void process(Object o, Field f) throws Exception{
		// check if numeric type
		
		f.setAccessible(true);
		
		if (!(f.get(o) instanceof Number))
		{
			throw new RuntimeException(f.getName()+" must be a number");
		}
		
		LessThan r = f.getAnnotation(LessThan.class);
		
		double d = f.getDouble(o);
		
		if (d>r.upperBound())
		{
			throw new RuntimeException(f.getName()+" must be less than "+r.upperBound());
		}

		
	}	
	
}
