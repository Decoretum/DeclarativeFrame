package annotations;

import java.lang.annotation.Target;
import java.lang.annotation.Retention;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(TYPE)
@Retention(RUNTIME)
public @interface Song {
	String title();
	boolean explicit() default false;
}
