


import java.lang.reflect.Proxy;

public class SelectTester {

	
	public static void main(String[] args) throws Exception {
		
		
		// TODO Auto-generated method stub
		UserMapper proxy = 
				(UserMapper) Proxy.newProxyInstance(
													 ClassLoader.getSystemClassLoader(), 					// this is the default
													 new Class[] { UserMapper.class }, // list of all the interfaces the proxy should implement
													 new Handler()												// handler used to process the dynamic calls
													 );
		// try to run queries on the user table
		
		System.out.println(proxy.getUserById(1));
		System.out.println(proxy.getUserByFirstNameAndLastName("John","Smith"));
		System.out.println(proxy.getUsers());
	}

}
