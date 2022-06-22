package io.github.educastilho;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import io.github.educastilho.domain.entity.Cliente;
import io.github.educastilho.domain.entity.Pedido;
import io.github.educastilho.domain.entity.Produto;
import io.github.educastilho.domain.entity.Usuario;
import io.github.educastilho.domain.repository.Clientes;
import io.github.educastilho.domain.repository.Pedidos;
import io.github.educastilho.domain.repository.Produtos;
import io.github.educastilho.domain.repository.UsuarioRepository;
import io.github.educastilho.service.impl.UsuarioServiceImpl;

@SpringBootApplication
@ComponentScan(basePackages = {"io.github.educastilho", "io.github.educastilho.repository", "io.github.educastilho.service" })
@RestController
@EnableWebMvc
public class VendasApplication /*extends SpringBootServletInitializer*/ {

	@Bean
	public CommandLineRunner init(@Autowired Clientes clientes, @Autowired Produtos produtos, @Autowired UsuarioServiceImpl usuarioservice) {
		return args -> {
//			Cliente cliente = new Cliente();
//			cliente.setNome("Eduardo");
//			cliente.setCpf("44461859002");
//			clientes.save(cliente);
//			
//			Produto produto = new Produto();
//			produto.setDescricao("Papel");
//			produto.setPreco(BigDecimal.valueOf(0.5));
//			produtos.save(produto);
//			
//			Usuario usuario = new Usuario();
//			usuario.setLogin("Educastilho");
//			usuario.setSenha("123");
//			usuario.setAdmin(false);
//			usuarioservice.save(usuario);
			
			
		};
	}
	
	public static void main(String[] args) {
		SpringApplication.run(VendasApplication.class, args);
	}

}
