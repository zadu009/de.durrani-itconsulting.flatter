package com.hibernate.example.repository;


import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.hibernate.example.models.Message;

@EnableJpaRepositories("com.hibernate.example.*")
@ComponentScan(basePackages = { "com.hibernate.example.*" })
@EntityScan("com.hibernate.example.*")
public interface MessageRepository extends JpaRepository<Message,Integer>{

}