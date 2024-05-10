import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class TheCheaterWay {

		// TODO Auto-generated constructor stub
		public static void main (String[] args) throws Exception {
			
			//Cheater WAY
			
			
			//Room 2
			
			Class c2 = Class.forName("Room2");
			Object classObject2 = c2.getDeclaredConstructor();
			
			//Room 3
			Class c3 = Class.forName("Room3");
			
			Object classObject3 = c3.getDeclaredConstructor();
			
			//Room 4
			Class c4 = Class.forName("Room4");
			
			Object classObject4 = c4.getDeclaredConstructor();

			

			
			
			Field wordFound2 = c2.getDeclaredField("wordFound");
			
			wordFound2.setAccessible(true);
			
			wordFound2.set(classObject2, true);
			
			
			Field wordFound3 = c3.getDeclaredField("wordFound");
			
			wordFound3.setAccessible(true);
			
			wordFound3.set(classObject3, true);
			
			
			Field wordFound4 = c4.getDeclaredField("wordFound");
			
			wordFound4.setAccessible(true);
			
			wordFound4.set(classObject4, true);
			
			
			
			Class room5 = Class.forName("Room5");
			
			Constructor<?> constructor2 = room5.getDeclaredConstructor();
			
			constructor2.setAccessible(true);
			
			
			Object cheatObj = constructor2.newInstance();
			
			Method m13 = room5.getDeclaredMethod("attack");
			
			Method m14 = room5.getDeclaredMethod("look");
			
			
			Class<?> wordClass [] = new Class[3];
			
			Class<?>[] wordClass2 = {String.class};
			
			Class<?>[] wordClass3 = {String.class};
			
			
			wordClass[0] = String.class;
			
			wordClass[1] = String.class;
			
			wordClass[2] = String.class;
			
			Method m15 = room5.getDeclaredMethod("passphrase", wordClass);
			m15.setAccessible(true);
			m15.invoke(cheatObj, "Ala", "Ka", "Zam");
			
			
			
		}
	

}
