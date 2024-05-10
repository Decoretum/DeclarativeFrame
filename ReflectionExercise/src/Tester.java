import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Tester {

	public static void main(String[] args) throws Exception
	{
		// instantiate lab.Exercise
		// follow the instructions given
		
		
		Class newClass = Class.forName("lab.Exercise");
		Object newObject = newClass.getDeclaredConstructor().newInstance();
		
		//Setting the Fields
		Field aField = newClass.getDeclaredField("fieldA");
		aField.setAccessible(true);
		aField.set(newObject, 2000);
		
		Field bField = newClass.getDeclaredField("fieldB");
		bField.setAccessible(true);
		bField.set(newObject, 10000);
		
		Method list = newClass.getDeclaredMethod("list");
		list.invoke(newObject);
		
		Method add = newClass.getDeclaredMethod("add");
		add.invoke(newObject);
		
		//MultiplyBy
		Class<?>[]formparam = {};
		
		//Argument
		Class<?>[]argTypes = new Class[] {int.class};
		int mainArgs = 98;
		Method mult = newClass.getDeclaredMethod("multiplyBy", argTypes);
		mult.invoke(newObject, mainArgs);
		
	}
	
}
