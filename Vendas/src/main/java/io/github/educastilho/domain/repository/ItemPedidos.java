package io.github.educastilho.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.educastilho.domain.entity.ItemPedido;

public interface ItemPedidos extends JpaRepository<ItemPedido, Integer> {

}
