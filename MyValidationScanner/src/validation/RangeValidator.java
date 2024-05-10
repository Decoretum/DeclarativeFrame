package validation;

import java.lang.reflect.Field;

import annotations.Range;

@ValidationAnnotation(target=Range.class)
public class RangeValidator implements ValidationHandler {

	
	public void process(Object o, Field f) throws Exception {
		// check if numeric type
		f.setAccessible(true);
		
		if (!(f.get(o) instanceof Number))
		{
			throw new RuntimeException(f.getName()+" must be a number");
		}
		
		Range r = f.getAnnotation(Range.class);
		
		double d = f.getDouble(o);
		
		if (d>r.upperBound() || d<r.lowerBound())
		{
			throw new RuntimeException(f.getName()+" must be between "+r.lowerBound()+" and "+r.upperBound());
		}
			
		
	}
}
