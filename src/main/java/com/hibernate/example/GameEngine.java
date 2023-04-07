package com.hibernate.example;

import java.util.List;

public class GameEngine {
	

	String email;
	String nickname;
	String lastname;
	String firstname;
	String message;
	String password;
	String signInStatus;
	String signUpStatus;
	String userExistStatus;
    List<Game> gamesList;

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

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSignInStatus() {
		return signInStatus;
	}

	public void setSignInStatus(String signInStatus) {
		this.signInStatus = signInStatus;
	}

	public String getSignUpStatus() {
		return signUpStatus;
	}

	public void setSignUpStatus(String signUpStatus) {
		this.signUpStatus = signUpStatus;
	}

	public String getUserExistStatus() {
		return userExistStatus;
	}

	public void setUserExistStatus(String userExistStatus) {
		this.userExistStatus = userExistStatus;
	}

	public List<Game> getGamesList() {
		return gamesList;
	}

	public void setGamesList(List<Game> gamesList) {
		this.gamesList = gamesList;
	}

}
