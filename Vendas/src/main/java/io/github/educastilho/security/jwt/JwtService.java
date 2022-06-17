package io.github.educastilho.security.jwt;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import io.github.educastilho.domain.entity.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService {

	@Value("${security.jwt.expiracao}")
	private String expiracao;
	
	@Value("${security.jwt.chave-assinatura}")
	private String chaveAssinatura;
	
	public String gerarToken ( Usuario usuario ) {
		long expString = Long.valueOf(expiracao);
		LocalDateTime dataHoraExpiracao = LocalDateTime.now().plusMinutes(expString);
		Date data = Date.from(dataHoraExpiracao.atZone(ZoneId.systemDefault()).toInstant());
		
//		HashMap<String, Object> claims = new HashMap<>();
//		claims.put(chaveAssinatura, claims);
		
		JwtBuilder jws = Jwts.builder();
		
		String jwtString = jws.setSubject(usuario.getLogin())
		.setExpiration(data)
		//.setClaims(null)
		.signWith(SignatureAlgorithm.HS512, chaveAssinatura)
		.compact();
		
		return jwtString;
	}
	
	public Claims obterClaims(String token) throws ExpiredJwtException {
		 return Jwts.parser().setSigningKey(chaveAssinatura).parseClaimsJws(token).getBody();
	}
	
	public boolean tokenValido(String token) {
		try {
			Claims claims = obterClaims(token);
			Date dataExpiracao = claims.getExpiration();
			LocalDateTime localDateTime = dataExpiracao.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
			return !LocalDateTime.now().isAfter(localDateTime);
		} catch (Exception e) {
			return false;
		}
	}
	
	public String obterLoginUsuario(String token) throws ExpiredJwtException {
		return (String) obterClaims(token).getSubject();
	}
	
//	public static void main(String[] args) {
//		ConfigurableApplicationContext context = SpringApplication.run(VendasApplication.class);
//		JwtService jwtService = context.getBean(JwtService.class);
//		Usuario usuario = new Usuario(1, "Eduardo", "teste", false);
//		String token = jwtService.gerarToken(usuario);
//		System.out.println(token);
//	}
}
