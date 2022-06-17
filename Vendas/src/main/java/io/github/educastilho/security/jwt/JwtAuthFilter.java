package io.github.educastilho.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import io.github.educastilho.service.impl.UsuarioServiceImpl;

public class JwtAuthFilter extends OncePerRequestFilter {

	private JwtService jwtService;
	private UsuarioServiceImpl usuarioService;
	
	public JwtAuthFilter(JwtService jwtService, UsuarioServiceImpl usuarioService) {
		super();
		this.jwtService = jwtService;
		this.usuarioService = usuarioService;
	}

	@Override
	protected void doFilterInternal
		(HttpServletRequest request, 
			HttpServletResponse response, 
			FilterChain filterChain)
			throws ServletException, IOException {
	
		String authString = request.getHeader("Authorization");
		
		if(authString != null && authString.startsWith("Bearer")) {
			String token = authString.split(" ")[1];
			boolean isValid = jwtService.tokenValido(token);
			
			if(isValid) {
				String login = jwtService.obterLoginUsuario(token);
				UserDetails userDetails = usuarioService.loadUserByUsername(login);
				UsernamePasswordAuthenticationToken user = 
							new UsernamePasswordAuthenticationToken(userDetails, null, 
									userDetails.getAuthorities());
				user.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(user);
			} 

		}
		filterChain.doFilter(request, response);
	}

}
