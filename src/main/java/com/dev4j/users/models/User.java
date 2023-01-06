package com.dev4j.users.models;

public class User {
	private String nickName;
	private String firstName;
	private String secondName;
	private Integer secuence;
	
	public User() {
		
	}
	
	public User(String nickName, String firstName, String secondName, Integer secuence) {
		this.nickName = nickName;
		this.firstName = firstName;
		this.secondName = secondName;
		this.secuence = secuence;
	}
	
	public String getNickName() {
		return nickName;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getSecondName() {
		return secondName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public Integer getSecuence() {
		return secuence;
	}

	public void setSecuence(Integer secuence) {
		this.secuence = secuence;
	}

	
	
	
}
