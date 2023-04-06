package com.hibernate.example.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hibernate.example.models.Engineuser;

public interface GameEngineRepository extends JpaRepository<Engineuser,Integer>{
	boolean existsByEmailOrNickname(String nickname,String email);
	
	boolean existsByEmailAndPassword(String email,String password);
	
    
	List<Engineuser> findAll();
}