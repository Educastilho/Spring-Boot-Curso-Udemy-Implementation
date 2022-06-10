package io.github.educastilho.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.educastilho.model.Cliente;
import io.github.educastilho.repository.ClientesRepository;

@Service
public class ClientesService {
	
	@Autowired
	private ClientesRepository repository;

	public void salvarCliente(Cliente cliente) {
		validarCliente(cliente);
		repository.persistirCliente(cliente);
		
	}
	
	public void validarCliente(Cliente cliente) {
		//validar o cliente
	}

}
