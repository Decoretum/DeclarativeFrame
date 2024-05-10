 package demo.bank;
 import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;


public class ProxyDemo {

	
	public static void main(String[] args)
	{
		Bank realBank = new Bank();
		
		
		// create the handler to handle the proxy calls
			// typically if there is a "real" object that is delegated to from the proxy
			// it will be set in here
		InvocationHandler handler = new BankInvocationHandler(realBank);
		
		// generate the proxy instance
		BankInterface proxy = 
					(BankInterface) Proxy.newProxyInstance(
														 ClassLoader.getSystemClassLoader(), 					// this is the default
														 new Class[] { BankInterface.class }, // list of all the interfaces the proxy should implement
														 handler												// handler used to process the dynamic calls
														 );
		// invoke any of the proxy methods
		proxy.deposit(100);
		proxy.withdraw(10);
	
		
		System.out.println(realBank);
	}
}
