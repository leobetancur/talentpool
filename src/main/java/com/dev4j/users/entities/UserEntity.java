package com.dev4j.users.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class UserEntity {

	@Id
	@Column(name="nickname")
	public String nickName;

	@Column(name="first_name")
	public String firstName;
	
	@Column(name="second_name")
	public String secondName;
	
	@Column(name="secuence")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	public Integer secuence;

	public UserEntity() {
		
	}
	
	public UserEntity(String nickName, String firstName, String secondName, Integer secuence) {
		this.nickName = nickName;
		this.firstName = firstName;
		this.secondName = secondName;
		this.secuence = secuence;
	}


	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
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
