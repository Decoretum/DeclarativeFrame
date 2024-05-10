import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;

import annotations.*;

public class LyricPrinter {

	public static void main(String[] args) throws Exception {
		
		printSong("blackpink.bornpink.Song1");
		printSong("blackpink.bornpink.Song2");

	}

	private static void emergencySort(Method[] methodList)
	{
		Arrays.sort(methodList, new Comparator<Method>() {@Override
			public int compare(Method o1, Method o2) {
				
				if (o1.isAnnotationPresent(Block.class) && o2.isAnnotationPresent(Block.class))
				{
					Block b1 = (Block) o1.getAnnotation(Block.class);
					Block b2 = (Block) o2.getAnnotation(Block.class);
					if (b1.value()>b2.value())
					{
						return 1;
					}
					
					if (b1.value()<b2.value())
					{
						return -1;
					}
				}
				
				return 0;
			}
			});
//		System.out.println("done");
	}
	
	
	private static void printSong(String song) throws Exception
	{
		//SONG 1
		
		//Get Class and Object Data
		Class getClass = Class.forName(song);
		Object song1 = getClass.getDeclaredConstructor().newInstance();
		
		//Getting Class annotations
		Album albumA = (Album) getClass.getAnnotation(Album.class);
		System.out.println("Album: " + albumA.name() + " (" + albumA.year() + ")");
		
		Song songA = (Song) getClass.getAnnotation(Song.class);
		String cond = "";
		if (songA.explicit() == true) {
			cond = "(explicit)";
		}
		System.out.println("Song: " + songA.title() + " " + cond);
		
		URL urlA = (URL) getClass.getAnnotation(URL.class);
		System.out.println("URL: " + urlA.value() + "\n");
		
		//Getting the methods' blocks' value
		
		//Get methods
		Method[] methods = getClass.getDeclaredMethods();
		emergencySort(methods);
		for (Method i : methods) {
			Singer singer = i.getAnnotation(Singer.class);
			System.out.println(singer.value() + ":");
			if (i.isAnnotationPresent(Repeat.class)) {
				Repeat repeat = (Repeat) i.getAnnotation(Repeat.class);
				for (int j = 0; j < repeat.value(); j++) {
					System.out.print(i.invoke(song1));
				}
				System.out.println("");
			} else {
				System.out.println(i.invoke(song1));
			}
			
		}
		

	}
	
}
