package annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import validation.GreaterThanValidator;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface GreaterThan {
	public double lowerBound();
}
