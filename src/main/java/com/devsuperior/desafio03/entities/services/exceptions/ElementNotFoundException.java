package com.devsuperior.desafio03.entities.services.exceptions;

public class ElementNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public ElementNotFoundException(String msg) {
		super(msg);
	}

}
