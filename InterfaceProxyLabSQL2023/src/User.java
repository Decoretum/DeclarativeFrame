

// PUT ANNOTATIONS FOR FIELDS SIMILAR TO THE SAMPLE
// determine what SQL types and column names to use by looking at the UserMapper

import annotations.Column;
import annotations.Table;

@Table("USER")
public class User 
{
	@Column(name="uid", sql="BIGINT not NULL AUTO_INCREMENT", primaryKey=true)
	private Long id;
	
	@Column(name="first_name", sql="VARCHAR(255)")	
	private String firstName;
	
	@Column(name="last_name", sql="VARCHAR(255)")
	private String lastName;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@Override
	public String toString() {
		return String.format("User [id=%s, firstName=%s, lastName=%s]", id, firstName, lastName);
	}
	
	
}
