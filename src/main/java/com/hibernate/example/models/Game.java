package com.hibernate.example.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Game {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(columnDefinition="INT")
	private Integer gameID;

    @Column(columnDefinition="VARCHAR(255)")
    String email;
    
	@Column(columnDefinition="VARCHAR(255)")
    String name;
	
	@Column(columnDefinition="VARCHAR(255)")
    String platform;
	
	@Column(columnDefinition="DECIMAL")
    double averageprice;

	public Integer getGameID() {
		return gameID;
	}

	public void setGameID(Integer gameID) {
		this.gameID = gameID;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public double getAverageprice() {
		return averageprice;
	}

	public void setAverageprice(double averageprice) {
		this.averageprice = averageprice;
	}


}
