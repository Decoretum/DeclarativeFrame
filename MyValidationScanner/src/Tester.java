import validation.Validator;

public class Tester 
{
	public static void main(String[] args) throws Exception
	{
		MyClass m = new MyClass();
		
		new Validator().validate(m);
	}
	
}
