package io.github.educastilho.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.educastilho.domain.entity.ItemPedido;
import io.github.educastilho.domain.entity.Pedido;
import io.github.educastilho.domain.enums.StatusPedido;
import io.github.educastilho.domain.repository.Clientes;
import io.github.educastilho.domain.repository.ItemPedidos;
import io.github.educastilho.domain.repository.Pedidos;
import io.github.educastilho.domain.repository.Produtos;
import io.github.educastilho.exception.PedidoNaoEncontradoException;
import io.github.educastilho.exception.RegraNegocioException;
import io.github.educastilho.rest.dto.ItemPedidoDTO;
import io.github.educastilho.rest.dto.PedidoDTO;
import io.github.educastilho.service.PedidoService;

@Service
public class PedidoServiceImpl implements PedidoService {

	@Autowired
	private Pedidos repository;
	
	@Autowired
	private Clientes clienteRepository;
	
	@Autowired
	private Produtos produtoRepository;
	
	@Autowired
	private ItemPedidos itemPedidosRepository;
	
	
	@Override
	@Transactional
	public Pedido save(PedidoDTO dto) {
		Pedido pedido = new Pedido();
		
		pedido.setCliente(clienteRepository
				.findById(dto.getCliente_id())
				.orElseThrow(()-> new RegraNegocioException("código de cliente inválido!"))
		);
		pedido.setDataPedido(LocalDate.now());
		pedido.setTotal(dto.getTotal());
		pedido.setStatus(StatusPedido.REALIZADO);
		List<ItemPedido> itemsPedidos = converterItems(pedido, dto.getItemsPedido());
		pedido.setItensPedido(itemsPedidos);
		
		repository.save(pedido);
		itemPedidosRepository.saveAll(itemsPedidos);
		pedido.setItensPedido(itemsPedidos);
		return pedido;
	}
	
	private List<ItemPedido> converterItems(Pedido pedido, List<ItemPedidoDTO> items) {
		if(items.isEmpty()) {
			throw new RegraNegocioException("código de cliente inválido!");
		} else {
			return items.stream().map(dto -> {
								
				ItemPedido itemPedido = new ItemPedido();
				itemPedido.setQuantidade(dto.getQuantidade());
				itemPedido.setPedido(pedido);
				itemPedido.setProduto(produtoRepository
						.findById(dto.getProduto())
						.orElseThrow(()-> new RegraNegocioException("código de produto inválido: " + dto.getProduto())));
				
				return itemPedido;
			}).collect(Collectors.toList());
		}
	}

	@Override
	public Optional<Pedido> obterPedidoCOmpleto(Integer idPedido) {
		
		
		return repository.findByIdFetchItems(idPedido);
	}

	@Override
	@Transactional
	public void atualizaStatus(Integer id, StatusPedido status) {
		repository.findById(id).map(pedido -> {
			pedido.setStatus(status);
			return repository.save(pedido);
		}).orElseThrow(() -> new PedidoNaoEncontradoException(""));
	}
	
}
