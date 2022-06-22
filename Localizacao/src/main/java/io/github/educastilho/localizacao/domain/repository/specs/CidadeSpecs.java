package io.github.educastilho.localizacao.domain.repository.specs;

import org.springframework.data.jpa.domain.Specification;

import io.github.educastilho.localizacao.domain.entity.Cidade;

public abstract class CidadeSpecs {

	public static Specification<Cidade> propertyEqual(String prop, Object value) {
		return(root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(prop), value);
	}
	
	public static Specification<Cidade> idEqual(Long id) {
		return(root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("id"), id);
	}
	
	public static Specification<Cidade> nomeEqual(String nome) {
		return(root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("nome"), nome);
	}
	
	public static Specification<Cidade> habitantesGreaterThan(Long habitantes) {
		return(root, query, criteriaBuilder) -> criteriaBuilder.greaterThan(root.get("habitantes"), habitantes);
	}
	
	public static Specification<Cidade> habitantesBetween(Long min, Long max) {
		return(root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("habitantes"), min, max);
	}
	
	public static Specification<Cidade> nomelike(String nome) {
		return(root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.upper(root.get("nome")), "%"+nome+"%".toUpperCase());
	}
}
