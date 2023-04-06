package com.hibernate.example.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.hibernate.example.models.Message;

public interface MessageRepository extends JpaRepository<Message,Integer>{

}