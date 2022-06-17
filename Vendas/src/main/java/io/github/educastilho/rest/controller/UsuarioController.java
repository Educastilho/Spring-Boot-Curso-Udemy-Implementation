package io.github.educastilho.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.github.educastilho.domain.entity.Usuario;
import io.github.educastilho.exception.UsuarioSenhaInvalidaException;
import io.github.educastilho.rest.dto.CredenciaisDTO;
import io.github.educastilho.rest.dto.TokenDTO;
import io.github.educastilho.security.jwt.JwtService;
import io.github.educastilho.service.impl.UsuarioServiceImpl;

@RestController
@RequestMapping("/api/usuarios")
@ResponseBody
public class UsuarioController {

	@Autowired
	private UsuarioServiceImpl service;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private JwtService jwtService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario salvar(@RequestBody Usuario usuario) {
		return service.save(usuario);
	}
	
	@PostMapping("/auth")
	public TokenDTO autenticar(@RequestBody CredenciaisDTO credenciaisDTO) {
		try {
			Usuario usuario = new Usuario();
			usuario.setLogin(credenciaisDTO.getLogin());
			usuario.setSenha(credenciaisDTO.getSenha());
			UserDetails userAuthenticated = service.autenticar(usuario);
			String token = jwtService.gerarToken(usuario);
			return new TokenDTO(usuario.getLogin(), token);
		} catch (UsernameNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
		} catch (UsuarioSenhaInvalidaException e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
		}
	}
	
}
