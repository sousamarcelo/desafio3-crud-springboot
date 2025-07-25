package com.devsuperior.desafio03.entities.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.desafio03.controller.dto.ClientDTO;
import com.devsuperior.desafio03.entities.Client;
import com.devsuperior.desafio03.entities.services.exceptions.DataBaseException;
import com.devsuperior.desafio03.entities.services.exceptions.ElementNotFoundException;
import com.devsuperior.desafio03.entities.services.exceptions.ResourceNotFoundException;
import com.devsuperior.desafio03.repository.ClientRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository repository;
	
	@Transactional(readOnly = true)
	public ClientDTO findById(Long id) {
		Optional<Client> clients = repository.findById(id);
		Client client = clients.orElseThrow(() -> new ElementNotFoundException("Recuros não encontrado"));
		ClientDTO clientDTO = new ClientDTO(client);
		return clientDTO;
	}
	
	@Transactional(readOnly = true)
	public Page<ClientDTO> findAll(Pageable pageable){
		Page<Client> clients = repository.findAll(pageable);
		return clients.map(x -> new ClientDTO(x));
	}
	
	@Transactional
	public ClientDTO insert(ClientDTO dto) {
		Client entity = new Client();
		copyDtoTOEntity(dto, entity);
		entity = repository.save(entity);
		return new ClientDTO(entity);		
	}
	
	@Transactional
	public ClientDTO update(Long id, ClientDTO dto) {
		try {
			Client entity = repository.getReferenceById(id);
			copyDtoTOEntity(dto, entity);
			entity = repository.save(entity);
			return new ClientDTO(entity);
		} catch(EntityNotFoundException e) {
			throw new ElementNotFoundException("Recurso não encontrado");
		}		
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public void delete(Long id) {
		if (!repository.existsById(id)) {
			throw new ElementNotFoundException("Recurso não encontrado");
		}
		
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataBaseException("Falha de integridade referencial");
		}
	}
	
	private void copyDtoTOEntity(ClientDTO dto, Client entity){
		entity.setName(dto.getName());
		entity.setCpf(dto.getCpf());
		entity.setIncome(dto.getIncome());
		entity.setBirthDate(dto.getBirthDate());
		entity.setChildren(dto.getChildren());
	}	
}
