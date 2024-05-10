import annotations.*;

@Table("REGISTRATION")
public class Registration {

	@Column(name="id", sql="INTEGER not NULL AUTO_INCREMENT", primaryKey=true)
	private Integer id;
	
	@Column(name="first", sql="VARCHAR(255)")	
	private String first;

	@Column(name="last", sql="VARCHAR(255)")	
	private String last;
	
	@Column(name="age", sql="INTEGER")		
	private Integer age;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Registration [id=" + id + ", first=" + first + ", last=" + last + ", age=" + age + "]";
	}
	
	
	
}
