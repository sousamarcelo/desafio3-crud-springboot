package com.devsuperior.desafio03.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.desafio03.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {	

}
