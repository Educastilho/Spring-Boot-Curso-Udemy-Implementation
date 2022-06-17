package io.github.educastilho.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ch.qos.logback.core.encoder.Encoder;
import io.github.educastilho.domain.entity.Usuario;
import io.github.educastilho.domain.repository.UsuarioRepository;
import io.github.educastilho.exception.UsuarioSenhaInvalidaException;

@Service
public class UsuarioServiceImpl implements UserDetailsService {
	

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	private UsuarioRepository repository;
	
	public UserDetails autenticar(Usuario usuario) {
		UserDetails user = loadUserByUsername(usuario.getLogin());
		boolean passwordMatch = passwordEncoder().matches(usuario.getSenha(), user.getPassword());
		if(passwordMatch) {
			return user;
		}
		throw new UsuarioSenhaInvalidaException();
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario usuario = repository.findByLogin(username).orElseThrow(
				() -> new UsernameNotFoundException("Usuário não encontrado na base de dados!"));
		
		String [] roles = usuario.isAdmin() ? new String [] {"ADMIN", "USER"} : new String [] {"USER"}; 
		
		return User
				.builder()
				.username(usuario.getLogin())
				.password(usuario.getSenha())
				.roles(roles)
				.build();
	}

	@Transactional
	public Usuario save(Usuario usuario) {
		String passwordHash = passwordEncoder().encode(usuario.getSenha());
		usuario.setSenha(passwordHash);
		return repository.save(usuario);
	}
	
	

}
