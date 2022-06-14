package io.github.educastilho.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.educastilho.domain.entity.Produto;

public interface Produtos extends JpaRepository<Produto, Integer> {

}
