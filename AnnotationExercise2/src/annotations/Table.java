package annotations;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;

@Target(TYPE)
@Retention(RUNTIME)
public @interface Table {
	String value();
}
