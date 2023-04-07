package com.hibernate.example.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(columnDefinition="INT")
	private Integer messageID;
    
	@Column(columnDefinition="VARCHAR(255)")
    String lastName;
	
	@Column(columnDefinition="VARCHAR(255)")
    String firstName;
	
	@Column(columnDefinition="VARCHAR(255)")
    String email;
	
	@Column(columnDefinition="VARCHAR(255)")
    String message;

	public Integer getMessageID() {
		return messageID;
	}

	public void setMessageID(Integer messageID) {
		this.messageID = messageID;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
