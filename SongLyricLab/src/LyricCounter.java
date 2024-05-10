import java.lang.reflect.Method;
import annotations.*;

public class LyricCounter 
{
public static void main(String[] args) throws Exception {
		
		for (int i = 1; i <= 2; i++) {
			System.out.println("SONG " + i);
			int count = countLyrics("JISOO", "blackpink.bornpink.Song" + i);
			System.out.println("JISOO sang "+count+" lines");
			
			count = countLyrics("JENNIE", "blackpink.bornpink.Song" + i);
			System.out.println("JENNIE sang "+count+" lines");

			count = countLyrics("ROSE", "blackpink.bornpink.Song" + i);
			System.out.println("ROSE sang "+count+" lines");

			count = countLyrics("LALISA", "blackpink.bornpink.Song" + i);
			System.out.println("LALISA sang "+count+" lines");
			
			System.out.println("");
		}
	}

	
	private static int countLyrics(String singer, String song) throws Exception
	{
		//Get classes and objects
		Class getClass = Class.forName(song);
		Class forSort = Class.forName("LyricPrinter");
		Object inst = getClass.getDeclaredConstructor().newInstance();
		
//		//Fetch emergencySort from other class
//		Class<?> [] args = {Method[].class};
//		
//		Method[] methods = getClass.getDeclaredMethods();
//		
//		Method eSort = forSort.getDeclaredMethod("emergencySort", args);
//		eSort.setAccessible(true);
//		
//		for (int i = 0; i < methods.length; i++) {
//			args[0] = Method.class;
//		}
//		
//		eSort.invoke(inst, methods);
		
		//Iterate and condition
		Method[] methods = getClass.getDeclaredMethods();
		int count = 0;
		for (Method i : methods) {
			Singer singerA = i.getAnnotation(Singer.class);
			if (singer.equals(singerA.value())) {
				LineCount lCount = i.getAnnotation(LineCount.class);
				if (i.isAnnotationPresent(Repeat.class)) {
					Repeat repeat = i.getAnnotation(Repeat.class);
					count += lCount.value() * repeat.value();
				} else {
					count += lCount.value();
				}
			}
		}
		
		
		return count;
	}
}
