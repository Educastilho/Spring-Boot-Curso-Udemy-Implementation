package io.github.educastilho.domain.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import io.github.educastilho.domain.entity.Cliente;

public interface Clientes extends JpaRepository<Cliente, Integer> {

	@Query(value = "SELECT * FROM cliente c where c.nome LIKE '%:nome%' ", nativeQuery = true)
	List<Cliente> findByNomeLike(@Param("nome") String nome);
	
	List<Cliente> findByNomeOrId(String nome, Integer id);
	
	Cliente findOneByNome(String nome);
	
	boolean existsByNome(String nome);
	
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM Cliente c where c.nome = :nome")
	void deleteByNome(String nome);
	
	@Query("select c from Cliente c left join fetch c.pedidos where c.id = :id ")
	Cliente findClienteFetchPedidos(Integer id);
	
	
	//Cliente findClienteFetchPedidos(Integer id);
	
//	private static String INSERT = "insert into Cliente (nome) values (?)";
//	
//	private static String SELECT_ALL = "SELECT * FROM CLIENTE ";
//	
//	private static String SELECT_POR_NOME = "SELECT * FROM CLIENTE where nome LIKE ?";
//	
//	private static String UPDATE = "UPDATE CLIENTE set nome = ? where id = ? ";
//	
//	private static String DELETE = "DELETE FROM CLIENTE where id = ? ";
	
	//@Autowired JdbcTemplate jdbcTemplate;
	
	//@Autowired EntityManager entityManager;
	
//	@Transactional
//	public Cliente salvar(Cliente cliente) {
//		//jdbcTemplate.update(INSERT, new Object[] {cliente.getNome()});
//		entityManager.persist(cliente);
//		return cliente;
//	}
//	
//	@Transactional
//	public Cliente atualizar(Cliente cliente, String novoNome) {
//		//jdbcTemplate.update(UPDATE, new Object[] {novoNome, cliente.getId()});
//		cliente.setNome(novoNome);
//		entityManager.merge(cliente);
//		return cliente;
//	}
//	
//	@Transactional
//	public void deletar(int deleteId) {
//		//jdbcTemplate.update(DELETE, new Object[] {deleteId});
//		Cliente clienteADeletar = entityManager.find(Cliente.class, deleteId);
//		entityManager.remove(clienteADeletar);
//	}
//	
//	@Transactional()
//	public List<Cliente> buscarPorNome(String nome) {
////		return jdbcTemplate.query(SELECT_POR_NOME, new RowMapper<Cliente>() {
////			@Override
////			public Cliente mapRow(ResultSet resultSet, int i) throws SQLException {
////				
////				return new Cliente(resultSet.getInt("id"), resultSet.getString("nome"));
////			}
////		}, new Object[] {"%" + nome + "%"});
//		String jpql = "SELECT c FROM Cliente c where c.nome LIKE :nome";
//		TypedQuery<Cliente> query = entityManager.createQuery(jpql, Cliente.class);
//		query.setParameter("nome", "%" + nome + "%");
//		return query.getResultList();
//	}
//	
//	@Transactional()
//	public List<Cliente> obterTodos() {
////		return jdbcTemplate.query(SELECT_ALL, new RowMapper<Cliente>() {
////			@Override
////			public Cliente mapRow(ResultSet resultSet, int i) throws SQLException {
////				
////				return new Cliente(resultSet.getInt("id"), resultSet.getString("nome"));
////			}
////		});
//		
//		String jpql = "FROM Cliente";
//		TypedQuery<Cliente> query = entityManager.createQuery(jpql, Cliente.class);
//		return query.getResultList();
//	}
}
