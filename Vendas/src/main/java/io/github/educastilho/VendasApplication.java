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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.educastilho.domain.entity.Cliente;
import io.github.educastilho.domain.entity.Pedido;
import io.github.educastilho.domain.repository.Clientes;
import io.github.educastilho.domain.repository.Pedidos;

@SpringBootApplication
@ComponentScan(basePackages = {"io.github.educastilho", "io.github.educastilho.repository", "io.github.educastilho.service" })
@RestController
public class VendasApplication {

	@Bean
	public CommandLineRunner init(@Autowired Clientes clientes, @Autowired Pedidos pedidos) {
		return args -> {
			Cliente cliente = new Cliente();
			cliente.setNome("Eduardo");
			clientes.save(cliente);
			
			Cliente cliente2 = new Cliente();
			cliente2.setNome("João");
			clientes.save(cliente2);
			
			Cliente cliente3 = new Cliente();
			cliente3.setNome("Lucas Souza");
			clientes.save(cliente3);
			
			Pedido pedido = new Pedido();
			pedido.setCliente(cliente2);
			pedido.setDataPedido(LocalDate.now());
			pedido.setTotal(BigDecimal.valueOf(0));
			pedidos.save(pedido);
			
			
			List<Cliente> todosClientes = clientes.findAll();
			todosClientes.forEach(System.out::println);
			
			cliente.setNome("Eduardo Silva");
			clientes.save(cliente);
			
			System.out.println("Apos atualização");
			
			List<Cliente> todosClientes2 = clientes.findAll();
			todosClientes2.forEach(System.out::println);
			
			//clientes.deleteById(cliente.getId());
			clientes.deleteByNome(cliente.getNome());
			
			System.out.println("Apos deleção");
			
			List<Cliente> todosClientes3 = clientes.findAll();
			todosClientes3.forEach(System.out::println);
			
			System.out.println("Antes Busca");
			
			List<Cliente> buscarClientes = clientes.findByNomeLike("Lucas");
			buscarClientes.forEach(System.out::println);
			
			System.out.println("Após Busca");
			
			List<Cliente> todosClientes4 = clientes.findAll();
			todosClientes4.forEach(System.out::println);
			
			System.out.println("Antes Existe por Nome?");
			System.out.println(clientes.existsByNome(cliente2.getNome()));
			System.out.println("Depois Existe por Nome?");
			
			System.out.println("Antes Buscar pedidos pelo cliente");
			//Cliente clientePedido = clientes.findClienteFetchPedidos(cliente2.getId());
			//System.out.println(clientePedido);
			//System.out.println(clientePedido.getPedidos());
			pedidos.findByCliente(cliente2).forEach(System.out::println);
			System.out.println("Depois Buscar pedidos pelo cliente");
			
		};
	}
	
	public static void main(String[] args) {
		SpringApplication.run(VendasApplication.class, args);
	}

}
