package com.hibernate.example.repository;


import java.util.List;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.hibernate.example.models.Engineuser;

@EnableJpaRepositories("com.hibernate.example.*")
@ComponentScan(basePackages = { "com.hibernate.example.*" })
@EntityScan("com.hibernate.example.*")   
public interface GameEngineRepository extends JpaRepository<Engineuser,Integer>{
	boolean existsByEmailOrNickname(String nickname,String email);
	
	boolean existsByEmailAndPassword(String email,String password);
	
    
	List<Engineuser> findAll();
}