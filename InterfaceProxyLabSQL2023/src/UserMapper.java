

import java.util.List;
import annotations.*;

@MappedClass(clazz=User.class)
public interface UserMapper 
{

	@Select("select * from :table where uid = :id")
	User getUserById(@Param("id") Integer id);
	
	
	@Select("select * from :table")
	List<User> getUsers();
	
	
	@Select("select * from :table where first_name = \":firstName\" and last_name=\":lastName\"")
	User getUserByFirstNameAndLastName(@Param("firstName") String firstName, @Param("lastName") String lastName );

}

