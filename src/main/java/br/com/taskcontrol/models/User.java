package br.com.taskcontrol.models;


import javax.persistence.*;

import org.hibernate.validator.constraints.NotBlank;

@Entity(name="users")
public class User {
	@Id
	private String username;
	@NotBlank
	private String password;
	@NotBlank
	private String email;

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	

}
