package demo.bank;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import demo.annotations.DebugParam;
import demo.annotations.DebugProxy;

class BankInvocationHandler implements InvocationHandler
{
	
	// "real" object to delegate to
	private Bank bank;
	
	
	public BankInvocationHandler(Bank b) 
	{
		bank = b;
	}
	
	
	// calls to the proxy methods will land here
	@Override
	public Object invoke(Object proxy,		// proxy instance that triggered this call 
						 Method method, 	// Method object on the Proxy that was invoked
						 Object[] args		// arguments passed on invocation
						 ) throws Throwable 
	{
		// add any additional functionality here based on annotations if any
		
		if (method.isAnnotationPresent(DebugProxy.class))
		{
			System.out.println("In proxy invocation handler for '"+method.getName()+"' ... delegating to real");
				
		
			int count = 0;
			for (Parameter param : method.getParameters())
			{
//				if (param.isAnnotationPresent(DebugParam.class))
//				{
					System.out.printf("Param#%d = %s value=%s\n",count, param.getName(), args[count]);  // note the reflective name
//				}
				count++;
			}
		}
		
		// simple delegation: find the matching method in the real bank -- same name and parameter types
		Method bankMethod = bank.getClass().getMethod(method.getName(), method.getParameterTypes());
		
		System.out.println("Method Invoked: " + bankMethod.getName());		
		// invoke the method with the supplied arguments
		return bankMethod.invoke(bank, args);
	}
	
}