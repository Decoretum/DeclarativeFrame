package validation;

import java.lang.reflect.Field;

import annotations.GreaterThan;

@ValidationAnnotation(target=GreaterThan.class)
public class GreaterThanValidator implements ValidationHandler {


	public void process(Object o, Field f) throws Exception {
		// check if numeric type
		f.setAccessible(true);
		
		if (!(f.get(o) instanceof Number))
		{
			throw new RuntimeException(f.getName()+" must be a number");
		}
		
		GreaterThan r = f.getAnnotation(GreaterThan.class);
		
		double d = f.getDouble(o);
		
		if (d<r.lowerBound())
		{
			throw new RuntimeException(f.getName()+" must be greater then "+r.lowerBound());
		}
		
	}
	
}
