package com.devsuperior.desafio03.entities.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.desafio03.controller.dto.ClientDTO;
import com.devsuperior.desafio03.entities.Client;
import com.devsuperior.desafio03.repository.ClientRepository;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository repository;
	
	@Transactional(readOnly = true)
	public ClientDTO findById(Long id) {
		Optional<Client> clients = repository.findById(id);
		Client client = clients.get();
		ClientDTO clientDTO = new ClientDTO(client);
		return clientDTO;
	}
}
