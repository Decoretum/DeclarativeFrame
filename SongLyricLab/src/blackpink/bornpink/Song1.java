package blackpink.bornpink;

import annotations.*;

@Album(name="Born Pink", year=2022)
@Song(title="Happiest Girl", explicit=false)
@BlockCount(17)
@URL("https://www.youtube.com/watch?v=0-NCFA91AbA")
public class Song1
{
	@Singer("JENNIE")	
	@LineCount(3)
	@Block(1)
	public String block1()
	{
	return "Don't hold my hand, don't beg me back\n"
			+ "Don't say that we'll make it through this\n"
			+ "Yeah, if I'm so beautiful, then why?\n";
	}
	
	@Singer("ROSE")	
	@LineCount(3)
	@Block(2)
	public String block2()
	{
		return "The doors we slammed, the plates we smashed\n"
				+ "Echo with the sound of madness\n"
				+ "I can't remember why we try\n";
	}

	@Singer("JISOO")	
	@LineCount(2)
	@Block(3)
	public String block3()
	{
		return	"My heart only wants you\n"
				+ "The moment you say no\n";
	}
	
	@Singer("LALISA")	
	@LineCount(4)
	@Block(4)
	public String block4()
	{
		return "But tonight, I'll be the happiest girl in the world\n"
				+ "You'll see like it doesn't matter\n"
				+ "Tonight, I'll be the happiest girl in the world\n"
				+ "You'll see like it never happened\n";
	}
	
	@Singer("JISOO")	
	@LineCount(1)
	@Repeat(3)
	@Block(5)
	public String block5()
	{
		return "I can stop the tears if I want to\n";
	}
	
	@Singer("JENNIE")	
	@LineCount(2)
	@Block(6)
	public String block6()
	{
		return "But tonight, I'll be the happiest girl in the world\n"
				+ "You'll see like it never happened\n";
	}
	
	@Singer("ROSE")	
	@LineCount(3)
	@Block(7)
	public String block7()
	{
		return "Don't make us saints, we're wards of pain\n"
				+ "The past and a perfect picture\n"
				+ "There's no one else to blame this time\n";
	}
	
	@Singer("LALISA")	
	@LineCount(3)
	@Block(8)
	public String block8()
	{
		return "Don't change the truth, we can't undo\n"
				+ "The high we chase, steal the crash, no\n"
				+ "You're not the one who gets to cry\n";
	}
	

	@Singer("JISOO")	
	@LineCount(2)
	@Block(9)
	public String block9()
	{
		return "My heart only wants you\n"
				+ "The moment you say no\n";
	}
	
	@Singer("JENNIE")	
	@LineCount(4)
	@Block(10)
	public String block10()
	{
		return "But tonight, I'll be the happiest girl in the world\n"
				+ "You'll see like it doesn't matter\n"
				+ "Tonight, I'll be the happiest girl in the world\n"
				+ "You'll see like it never happened\n";
	}
	
	@Singer("JISOO")	
	@LineCount(1)
	@Repeat(3)
	@Block(11)
	public String block11()
	{
		return "I can stop the tears if I want to\n";
	}
	
	@Singer("ROSE")	
	@LineCount(2)
	@Block(12)
	public String block12()
	{
		return "But tonight, I'll be the happiest girl in the world\n"
				+ "You'll see like it never happened\n";
	}
	
	@Singer("LALISA")
	@LineCount(2)
	@Block(13)
	public String block13()
	{
		return "All it takes is a smooth pop of a bottle top\n"
				+ "To fix a heart, a broken heart, baby\n";
	}
	
	@Singer("JISOO")	
	@LineCount(2)
	@Block(14)
	public String block14()
	{
		return "All it takes is a little rolling paper, take us\n"
				+ "To the start, go back to the start\n";
	}
	
	@Singer("ROSE")	
	@LineCount(4)
	@Block(15)
	public String block15()
	{
		return "But tonight, I'll be the happiest girl in the world\n"
				+ "You'll see like it doesn't matter\n"
				+ "Tonight, I'll be the happiest girl in the world\n"
				+ "You'll see like it never happened\n";
	}
	
	
	@Singer("JISOO")	
	@LineCount(1)
	@Repeat(3)
	@Block(16)
	public String block16()
	{
		return "I can stop the tears if I want to\n";
	}
	
	@Singer("JENNIE")	
	@LineCount(2)
	@Block(17)
	public String block17()
	{
		return "But tonight, I'll be the happiest girl in the world\n"
				+ "You'll see like it never happened\n";
	}
	

}
