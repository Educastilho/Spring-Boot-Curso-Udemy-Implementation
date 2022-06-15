package io.github.educastilho.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import io.github.educastilho.domain.entity.Cliente;
import io.github.educastilho.domain.entity.Produto;
import io.github.educastilho.domain.repository.Clientes;
import io.github.educastilho.domain.repository.Produtos;

@Controller
@RequestMapping("/api/produtos")
@ResponseBody
public class ProdutoController {

	@Autowired
	private Produtos produtos;
	
	@GetMapping(value = "/{id}"
//			, method = RequestMethod.GET
//			,consumes = {"application/json", "application/xml", "text/html"}, 
//			produces = {"application/json", "application/xml", "text/html"}
	)
	public Produto getClienteById(@PathVariable("id") Integer id) {
		Optional<Produto> produto1 =  produtos.findById(id);
		if(produto1.isPresent()) {
			return produto1.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado!");
		}	
	}
	
	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public Produto save( @RequestBody Produto produto) {
		Produto produto1 = produtos.save(produto);
		if(produto1 != null) {
			return produto1;
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado!");
		}
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete( @PathVariable Integer id) {
		Optional<Produto> produto1 = produtos.findById(id);
		if(produto1.isPresent()) {
			produtos.delete(produto1.get());
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado!");
		}
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizar( @PathVariable Integer id, @RequestBody Produto produto) {
		produtos.findById(id).map(
				produtoAtual -> {					
					produto.setId(produtoAtual.getId());
					produtos.save(produto);
					return produtoAtual;
				}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado!"));
	}
	
	@GetMapping("/")
	public List<Produto> find(Produto filtro) {
		ExampleMatcher matcher = ExampleMatcher
									.matching()
									.withIgnoreCase()
									.withStringMatcher(
											ExampleMatcher.StringMatcher.CONTAINING
											);
		
		
		Example example = Example.of(filtro, matcher);
		List<Produto> lista = produtos.findAll(example);
		return lista;
	}
}
