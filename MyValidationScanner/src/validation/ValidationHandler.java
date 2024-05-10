package validation;
import java.lang.reflect.Field;


public interface ValidationHandler 
{
	public void process(Object o, Field f) throws Exception;
}
