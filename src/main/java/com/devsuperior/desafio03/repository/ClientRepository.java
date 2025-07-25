package com.devsuperior.desafio03.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devsuperior.desafio03.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {	

}
