package io.github.educastilho.localizacao;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.educastilho.localizacao.domain.entity.Cidade;
import io.github.educastilho.localizacao.domain.repository.CidadeRepository;
import io.github.educastilho.localizacao.service.CidadeService;

@SpringBootApplication
public class LocalizacaoApplication implements CommandLineRunner {

	@Autowired
	private CidadeService service;
	
	
	@Override
	public void run(String... args) throws Exception {
		//System.out.println("Inicializando!");
		//service.salvarCidade((long) 1, "São Paulo", (long) 12396372);
		//service.listarCidades();
		//service.listarCidadesPorNome("Rio");
		//service.listarCidadesPorHabitantes(10000000L);
		//service.listarNomeECidadePorHabitantes(10000000L, "Rio");
		//Cidade cidade = new Cidade(null, "Recife", null);
		 //service.filtroDinamico(cidade).forEach(System.out::println);
		//service.listarCidadesByNomeSpec();
		//Cidade cidade = new Cidade(1L, "São Paulo", 1000L);
		//service.listarCidadesFiltroDinamico(cidade).forEach(System.out::println);
		service.listarCidadesPorNomeNativo("São Paulo");
	}
	
	public static void main(String[] args) {
		SpringApplication.run(LocalizacaoApplication.class, args);
	}

}
