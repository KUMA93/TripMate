package com.ssafy.trip.model.dto;

import java.io.Serializable;

public class User implements Serializable{
	private String id;
	private String pass;
	private String name;
	private String email;
	private String position = "user";
	private int sido_code;

	public User() {
		super();
	}

	

	public User(String id, String pass, String name, String email, String position, int sido_code) {
		super();
		this.id = id;
		this.pass = pass;
		this.name = name;
		this.email = email;
		this.position = position;
		this.sido_code = sido_code;
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public int getSido_code() {
		return sido_code;
	}

	public void setSido_code(int sido_code) {
		this.sido_code = sido_code;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", pass=" + pass + ", name=" + name + ", email=" + email + ", position="
				+ position + ", sido_code=" + sido_code + "]";
	}
	
	
}
