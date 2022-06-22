package io.github.educastilho.localizacao.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import io.github.educastilho.localizacao.domain.entity.Cidade;
import io.github.educastilho.localizacao.domain.repository.CidadeRepository;
import io.github.educastilho.localizacao.domain.repository.specs.CidadeSpecs;

@Service
public class CidadeService {
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Transactional
	public void salvarCidade (Long id, String nome, Long habitantes) {
		Cidade cidade = new Cidade(id, nome, habitantes);
		cidadeRepository.save(cidade);
	}

	public void listarCidades() {
		cidadeRepository.findAll().forEach(System.out::println);
	}
	
	public void listarCidadesPorNome(String nome) {
		Pageable pageable = PageRequest.of(0, 10);
		//cidadeRepository.findByNomeLike('%'+nome+'%', Sort.by("habitantes")).forEach(System.out::println);
		cidadeRepository.findByNomeLike('%'+nome+'%', pageable).forEach(System.out::println);
	}
	
	public void listarCidadesPorHabitantes(Long habitantes) {
		cidadeRepository.findByHabitantesLessThanEqual(habitantes).forEach(System.out::println);
	}
	
	public void listarNomeECidadePorHabitantes(Long habitantes, String nome) {
		cidadeRepository.findByHabitantesGreaterThanEqualAndNomeLike(habitantes, '%'+nome+'%').forEach(System.out::println);
	}
	
	public List<Cidade> filtroDinamico(Cidade cidade) {
		
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withIgnoreCase("nome")
				.withStringMatcher(StringMatcher.STARTING)
				//.withIncludeNullValues()
				;
		
		Example<Cidade> example = Example.of(cidade, matcher);
		
		return cidadeRepository.findAll(example); 
	}
	
	public void listarCidadesByNomeSpec() {
		Specification<Cidade> spec = CidadeSpecs.propertyEqual("nome", "São Paulo").and(CidadeSpecs.habitantesGreaterThan(1000L));
		cidadeRepository.findAll(spec).forEach(System.out::println);
	}
	
	public List<Cidade> listarCidadesFiltroDinamico(Cidade filtro) {
		Specification<Cidade> spec = Specification.where((root, query, criteriaBuilder) -> criteriaBuilder.conjunction());
		
		if (filtro.getId() != null) {
			spec = spec.and(CidadeSpecs.idEqual(filtro.getId()));
		}
		
		if(StringUtils.hasText(filtro.getNome())) {
			spec = spec.and(CidadeSpecs.nomelike(filtro.getNome()));
		}
		
		if(filtro.getHabitantes() != null) {
			spec = spec.and(CidadeSpecs.habitantesGreaterThan(filtro.getHabitantes()));
		}
		
		return cidadeRepository.findAll(spec);
	}
	
	public void listarCidadesPorNomeNativo(String nome) {
		cidadeRepository.findByNomeNativoProjection(nome)
		.stream().map(cidadeProjetction -> 
				new Cidade(cidadeProjetction.getId(), cidadeProjetction.getNome(), null)
				)
		.forEach(System.out::println);
	}
}
