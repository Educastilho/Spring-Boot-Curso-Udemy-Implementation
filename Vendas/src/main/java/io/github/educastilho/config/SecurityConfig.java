package io.github.educastilho.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import io.github.educastilho.security.jwt.JwtAuthFilter;
import io.github.educastilho.security.jwt.JwtService;
import io.github.educastilho.service.impl.UsuarioServiceImpl;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UsuarioServiceImpl usuarioService;
	
	@Autowired
	private JwtService jwtService;
	
	@Bean
	public OncePerRequestFilter jwtFilter() {
		return new JwtAuthFilter(jwtService, usuarioService);
	}
	
//	private static final String[] AUTH_WHITELIST = {
//	        // -- Swagger UI v2
//	        "/v2/api-docs",
//	        "/swagger-resources",
//	        "/swagger-resources/**",
//	        "/configuration/ui",
//	        "/configuration/**",
//	        "/configuration/security",
//	        "/swagger-ui.html",
//	        "/webjars/**",
//	        // -- Swagger UI v3 (OpenAPI)
//	        "/v3/api-docs/**",
//	        "/swagger-ui/**",
//	        "/swagger-ui/",
//	        "/swagger-ui"
//	        // other public endpoints of your API may be appended to this array
//	};
	
	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		
//		auth.userDetailsService(usuarioService).passwordEncoder(passwordEncoder());
////		auth
////			.inMemoryAuthentication()
////			.passwordEncoder(passwordEncoder())
////			.withUser("Eduardo")
////			.password(passwordEncoder().encode("123"))
////			.roles("USER", "ADMIN");
//	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.csrf().disable()
				.authorizeRequests()
				.antMatchers("/api/clientes/**").hasAnyRole("USER", "ADMIN")
				.antMatchers("/api/produtos/**").hasRole("ADMIN")
				.antMatchers("/api/pedidos/**").hasAnyRole("USER", "ADMIN")
				.antMatchers(HttpMethod.POST, "/api/usuarios/**").permitAll()
				.anyRequest().authenticated()
			.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
				.addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);
			//.formLogin(/*"/login.html"*/);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(
					"/v2/api-docs",
					"/swagger-resources",
					"/swagger-resources/**",
					"/configuration/ui",
					"/configuration/**",
					"/configuration/security",
					"/swagger-ui/**",
					"/swagger-ui.html",
					"/webjars/**");
		
	}
		
}
