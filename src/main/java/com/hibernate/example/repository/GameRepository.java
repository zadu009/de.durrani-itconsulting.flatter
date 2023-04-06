package com.hibernate.example.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hibernate.example.models.Engineuser;
import com.hibernate.example.models.Game;

public interface GameRepository extends JpaRepository<Game,Integer>{
	
	Integer removeByEmail(String email);
	
	List<Game> findAllByEmail(String email);
	
}