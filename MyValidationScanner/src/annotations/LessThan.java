package annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import validation.GreaterThanValidator;
import validation.LessThanValidator;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LessThan {
	public double upperBound();
}
