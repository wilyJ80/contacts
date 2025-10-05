package net.ddns.pray.contact.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

public final class Contact {

	private Integer id;
	private String name;
	private String dateOfBirth;

	@Pattern(regexp = "[0-9]+", message = "Please provide a valid phone number (only numbers)")
	private String phoneNo;

	@Email(message = "Please provide a valid email address")
	private String email;

	public Contact(Integer id, String name, String dateOfBirth, String phoneNo, String email) {
		this.id = id;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.phoneNo = phoneNo;
		this.email = email;
	}

	public Contact() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
