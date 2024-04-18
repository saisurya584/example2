package com.travelbees.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="tourists")
public class Tourist {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
//	@NotBlank(message = "First name is required")
//	@Size(min=3,max =45, message = "First name cannot exceed 45 characters")
	private String firstName;
	
//	@NotBlank(message = "Last name is required")
//	@Size(min=3,max = 45, message = "Last name cannot exceed 45 characters")
	private String lastName;
	
//	@NotBlank(message = "Email is required")
//	@Email(message = "Invalid email format")
	private String email;
	
//	@NotBlank(message = "Password is required")
//    @Size(min = 6, message = "Password must be at least 6 characters long")
	private String password;
	
//	@NotBlank(message = "Contact number is required")
//	@Pattern(regexp = "[6-9][1-9]\\d{8}", message = "Invalid mobile number format")
	private String contactNumber;
     
//	@NotBlank(message="status is required")
	private String status;
	
//	@NotBlank(message="role is required")
	private String role;

	public Tourist() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Tourist(Long id, String firstName, String lastName, String email, String password, String contactNumber,
			String status, String role) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.contactNumber = contactNumber;
		this.status = status;
		this.role = role;
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Tourist [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", contactNumber=" + contactNumber + ", status=" + status + ", role="
				+ role + "]";
	}
	
	
	
	
	
}