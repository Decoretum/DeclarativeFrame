
import java.lang.reflect.Constructor;

import java.lang.reflect.Field;

import java.lang.reflect.Method;

public class TheLongWay {

	public static void main (String args[]) throws Exception {
	
		//Get Class object and instantiate an object class
		
		Class c = Class.forName("Room1");
		
		Object classObject = c.getDeclaredConstructor().newInstance();
		
		//Room 2
		
		Class c2 = Class.forName("Room2");
		
		Object classObject2 = c2.getDeclaredConstructor().newInstance();
		
		Method m2 = c2.getDeclaredMethod("swim");
		
		m2.invoke(classObject2);
		
		//Sword taken
		
		Method m3 = c2.getDeclaredMethod("takeSword");
		
		m3.invoke(classObject2);
		
		//pile rubble
		
		Method m4 = c2.getDeclaredMethod("look");
		
		m4.invoke(classObject2);
		
		m4.invoke(classObject2);
		
		//Digging
		
		Method m5 = c2.getDeclaredMethod("dig");
		
		m5.setAccessible(true);
		
		m5.invoke(classObject2);
		
		//scroll contains 3 words. but 2 unreadable. Remaining word says "Zam"
		
		//Room 3
		
		Class c3 = Class.forName("Room3");
		
		Object classObject3 = c3.getDeclaredConstructor().newInstance();
		
		Method m6 = c3.getDeclaredMethod("attack");
		
		Method m7 = c3.getDeclaredMethod("look");
		
		Method m8 = c3.getDeclaredMethod("openChest");
		
		Method m9 = c3.getDeclaredMethod("look");
		
		m6.setAccessible(true);
		
		//killing the dragon
		
		//m6.invoke(classObject3);
		
		m7.invoke(classObject3);
		
		m8.invoke(classObject3);
		
		m9.invoke(classObject3);
		
		//Book with a page ear-marked with word "Ala" written in blood
		
		//Room 4
		
		Class c4 = Class.forName("Room4");
		
		Object classObject4 = c4.getDeclaredConstructor().newInstance();
		
		Class<?>[] argClass = {int.class};
		
		Method m10 = c4.getDeclaredMethod("answer", argClass);
		
		Method m11 = c4.getDeclaredMethod("look");
		
		m10.invoke(classObject4, 342 * 587);
		
		m11.invoke(classObject4);
		
		//Low voice says, Ka
		
		//Room 5
		
		Class c5 = Class.forName("Room5");
		
		Constructor<?> constructor = c5.getDeclaredConstructor();
		
		constructor.setAccessible(true);
		
		Object classObject5 = constructor.newInstance();
		
		Method m13 = c5.getDeclaredMethod("attack");
		
		Method m14 = c5.getDeclaredMethod("look");
		
		Class<?> wordClass [] = new Class[3];
		
		Class<?>[] wordClass2 = {String.class};
		
		Class<?>[] wordClass3 = {String.class};
		
		wordClass[0] = String.class;
		
		wordClass[1] = String.class;
		
		wordClass[2] = String.class;
		
		Method m15 = c5.getDeclaredMethod("passphrase", wordClass);
		
		m13.setAccessible(true);
		
		m15.setAccessible(true);
		
		//I die
		
		//m13.invoke(classObject5);
		
		String [] wArray = {"Ala", "Ka", "Zam"};
		
		m15.invoke(classObject5, "Ala", "Ka", "Zam");
		
		
	
	}

}

