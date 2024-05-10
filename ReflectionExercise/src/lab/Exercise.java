package lab;

public class Exercise {
	
	
	private int fieldA;
	private int fieldB;
	
	public Exercise()
	{
		System.out.println("There are 2 int fields in this class fieldA and fieldB");
		System.out.println("Set these values using reflection");
		System.out.println("Look for the list() method and invoke each one");
	}

	
	public void list()
	{
		System.out.println("You can do the following with the fields: add(), multiplyBy(int)");
	}
	
	public void add()
	{
		System.out.println("The sum of "+fieldA+" and "+fieldB+" is "+(fieldA+fieldB));
	}
	
	public void multiplyBy(int factor)
	{
		System.out.println("The product of "+fieldA+" and "+factor+" is "+(fieldA*factor));
		System.out.println("The product of "+fieldB+" and "+factor+" is "+(fieldB*factor));
	}
	
	
}
