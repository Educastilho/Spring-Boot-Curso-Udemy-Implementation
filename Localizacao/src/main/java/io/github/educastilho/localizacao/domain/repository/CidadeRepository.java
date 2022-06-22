package io.github.educastilho.localizacao.domain.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import io.github.educastilho.localizacao.domain.entity.Cidade;
import io.github.educastilho.localizacao.domain.repository.projection.CidadeProjection;

public interface CidadeRepository extends JpaRepository<Cidade, Long>, JpaSpecificationExecutor<Cidade> {

	@Query(nativeQuery = true, value = "select c.id_cidade as id, c.nome from tb_cidade c where c.nome = :nome")
	List<CidadeProjection> findByNomeNativoProjection(String nome);
	
	@Query("select c from Cidade c where c.nome = :nome")
	List<Cidade> findByNomeNativo(String nome);
	
	List<Cidade> findByNome(String nome);
	
	//@Query(" select c from Cidade c where lower(c.nome) like lower(?1)")
	List<Cidade> findByNomeLike(String nome, Sort sort);
	
	//@Query(" select c from Cidade c where lower(c.nome) like lower(?1)")
	Page<Cidade> findByNomeLike(String nome, Pageable page);
	
	List<Cidade> findByNomeStartingWith(String nome);
	
	List<Cidade> findByNomeEndingWith(String nome);
	
	List<Cidade> findByNomeContaining(String nome);
	
	List<Cidade> findByHabitantes(Long habitatnes);
	
	List<Cidade> findByHabitantesLessThan(Long habitatnes);
	
	List<Cidade> findByHabitantesLessThanEqual(Long habitatnes);
	
	List<Cidade> findByHabitantesGreaterThan(Long habitatnes);
	
	List<Cidade> findByHabitantesGreaterThanEqual(Long habitatnes);
	
	List<Cidade> findByHabitantesGreaterThanEqualAndNomeLike(Long habitatnes, String nome);
	
	//List<Cidade> findBy
}
