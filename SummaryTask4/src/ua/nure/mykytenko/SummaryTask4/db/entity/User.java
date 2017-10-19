package ua.nure.mykytenko.SummaryTask4.db.entity;

import java.io.Serializable;

/**
 * Order entity.
 * 
 * @author A.Mykytenko
 * 
 */
public class User implements Serializable {

	private static final long serialVersionUID = -9091193047211359598L;

	private long id;

	private String email;

	private String password;

	private String firstName;

	private String lastName;

	private String documentTag;

	private Role role;

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", documentTag=" + documentTag + ", role=" + role + "]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	/*
	 * public String getDocumentTag() { return documentTag; }
	 * 
	 * public void setDocumentTag(String documentTag) { this.documentTag =
	 * documentTag; }
	 */
}
