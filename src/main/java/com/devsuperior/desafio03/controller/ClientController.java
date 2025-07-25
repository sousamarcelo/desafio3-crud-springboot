package com.devsuperior.desafio03.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {
	
	@GetMapping//(value = "/{id}")
	public String testeRequest() {
		return "Teste requisição";
	}

}
