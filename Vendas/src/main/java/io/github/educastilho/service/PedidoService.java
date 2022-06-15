package io.github.educastilho.service;

import java.util.Optional;

import io.github.educastilho.domain.entity.Pedido;
import io.github.educastilho.domain.enums.StatusPedido;
import io.github.educastilho.rest.dto.PedidoDTO;

public interface PedidoService {

	Pedido save(PedidoDTO dto);
	
	Optional<Pedido> obterPedidoCOmpleto(Integer idPedido);
	
	void atualizaStatus(Integer id, StatusPedido status);
}
