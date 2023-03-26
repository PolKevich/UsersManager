package by.htp.ex.bean;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Size(max = 40)
	@Pattern(regexp = "^[a-zA-Z]*$", message = "last name must contain only Latin letters")
	@Column(name = "last_name", nullable = false)
	private String lastName;

	@Size(max = 20)
	@Pattern(regexp = "^[a-zA-Z]*$", message = "last name must contain only Latin letters")
	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Size(max = 40)
	@Pattern(regexp = "^[a-zA-Z]*$", message = "last name must contain only Latin letters")
	@Column(name = "middle_name", nullable = false)
	private String middleName;

	@Size(max = 50)
	@Email(message = "Invalid email address")
	@Column(name = "email", nullable = false)
	private String email;

	@Enumerated(EnumType.STRING)
	@Column(name = "role", nullable = false)
	private UserRole role;

	public User() {

	}
	
	

	public User(
			@Size(max = 40) @Pattern(regexp = "^[a-zA-Z]*$", message = "last name must contain only Latin letters") String lastName,
			@Size(max = 20) @Pattern(regexp = "^[a-zA-Z]*$", message = "last name must contain only Latin letters") String firstName,
			@Size(max = 40) @Pattern(regexp = "^[a-zA-Z]*$", message = "last name must contain only Latin letters") String middleName,
			@Size(max = 50) @Email(message = "Invalid email address") String email, UserRole role) {
		
		this.lastName = lastName;
		this.firstName = firstName;
		this.middleName = middleName;
		this.email = email;
		this.role = role;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public UserRole getRole() {
		return role;
	}
	
	public void setRole(UserRole role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, firstName, id, lastName, middleName, role);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email) && Objects.equals(firstName, other.firstName) && id == other.id
				&& Objects.equals(lastName, other.lastName) && Objects.equals(middleName, other.middleName)
				&& role == other.role;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", lastName=" + lastName + ", firstName=" + firstName + ", middleName=" + middleName
				+ ", email=" + email + ", role=" + role + "]";
	}



}
