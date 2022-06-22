package io.github.educastilho.localizacao.domain.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_cidade")
public class Cidade {

	@Id
	@Column(name = "id_cidade")
	private Long id;
	
	@Column(name = "nome", length = 100)
	private String nome;
	
	@Column(name = "qtd_habitantes")
	private Long habitantes;

	public Cidade() {
		super();
	}

	public Cidade(Long id, String nome, Long habitantes) {
		super();
		this.id = id;
		this.nome = nome;
		this.habitantes = habitantes;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getHabitantes() {
		return habitantes;
	}

	public void setHabitantes(Long habitantes) {
		this.habitantes = habitantes;
	}

	@Override
	public int hashCode() {
		return Objects.hash(habitantes, id, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cidade other = (Cidade) obj;
		return Objects.equals(habitantes, other.habitantes) && Objects.equals(id, other.id)
				&& Objects.equals(nome, other.nome);
	}

	@Override
	public String toString() {
		return "Cidade [id=" + id + ", nome=" + nome + ", habitantes=" + habitantes + "]";
	}
	
	
	
	
}
