package com.hibernate.example.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "engineuser")
public class Engineuser {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(columnDefinition="INT")
	private
    Integer engineuserID;

    @Column(columnDefinition="VARCHAR(255)")
    String email;
    
	@Column(columnDefinition="VARCHAR(255)")
    String nickname;

    @Column(columnDefinition="VARCHAR(255)")
    String password;

    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	Integer getEngineuserID() {
		return engineuserID;
	}

	void setEngineuserID(Integer engineuserID) {
		this.engineuserID = engineuserID;
	}

	
}
