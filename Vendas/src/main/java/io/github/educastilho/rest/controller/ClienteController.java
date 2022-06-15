package io.github.educastilho.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import io.github.educastilho.domain.entity.Cliente;
import io.github.educastilho.domain.repository.Clientes;

@Controller
@RequestMapping("/api/clientes")
@ResponseBody
public class ClienteController {
	
	@Autowired
	private Clientes clientes;
	
	@GetMapping(value = "/{id}"
//			, method = RequestMethod.GET
//			,consumes = {"application/json", "application/xml", "text/html"}, 
//			produces = {"application/json", "application/xml", "text/html"}
	)
	public Cliente getClienteById(@PathVariable("id") Integer id) {
		Optional<Cliente> cliente1 =  clientes.findById(id);
		if(cliente1.isPresent()) {
			return cliente1.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado!");
		}	
	}
	
	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente save( @RequestBody Cliente cliente) {
		Cliente cliente1 = clientes.save(cliente);
		if(cliente1 != null) {
			return cliente1;
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado!");
		}
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete( @PathVariable Integer id) {
		Optional<Cliente> cliente1 = clientes.findById(id);
		if(cliente1.isPresent()) {
			clientes.delete(cliente1.get());
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado!");
		}
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizar( @PathVariable Integer id, @RequestBody Cliente cliente) {
		clientes.findById(id).map(
				clienteAtual -> {					
					cliente.setId(clienteAtual.getId());
					clientes.save(cliente);
					return clienteAtual;
				}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado!"));
	}
	
	@GetMapping("/")
	public List<Cliente> find(Cliente filtro) {
		ExampleMatcher matcher = ExampleMatcher
									.matching()
									.withIgnoreCase()
									.withStringMatcher(
											ExampleMatcher.StringMatcher.CONTAINING
											);
		
		
		Example example = Example.of(filtro, matcher);
		List<Cliente> lista = clientes.findAll(example);
		return lista;
	}
}