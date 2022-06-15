package io.github.educastilho.rest.controller;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import io.github.educastilho.domain.entity.ItemPedido;
import io.github.educastilho.domain.entity.Pedido;
import io.github.educastilho.domain.enums.StatusPedido;
import io.github.educastilho.exception.RegraNegocioException;
import io.github.educastilho.rest.dto.AtualizacaoStatusPedidoDTO;
import io.github.educastilho.rest.dto.InformacaoItemPedidoDTO;
import io.github.educastilho.rest.dto.InformacoesPedidoDTO;
import io.github.educastilho.rest.dto.PedidoDTO;
import io.github.educastilho.service.PedidoService;

@ResponseBody
@Controller
@RequestMapping("/api/pedidos")
public class PedidoController {

	@Autowired
	private PedidoService service;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Integer save(@RequestBody PedidoDTO pedido) {
		Pedido pedidoSalvo = service.save(pedido);
		return pedidoSalvo.getId();
	}
	
	@GetMapping("/{id}")
	public InformacoesPedidoDTO getById(@PathVariable Integer id) {
		return service.obterPedidoCOmpleto(id)
				.map(p -> {
					return converter(p);
				})
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "pedido não encontrado!"));
	}
	
	private InformacoesPedidoDTO converter(Pedido pedido) {
		InformacoesPedidoDTO info = new InformacoesPedidoDTO();
		info.setCodigo(pedido.getId());
		info.setDataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		info.setNomeCliente(pedido.getCliente().getNome());
		info.setCpf(pedido.getCliente().getCpf());
		info.setStatus(pedido.getStatus().name());
		info.setTotal(pedido.getTotal());
		info.setItems(converterItemPedidoDTO(pedido.getItensPedido()));
		
		return info;
	}
	
	private List<InformacaoItemPedidoDTO> converterItemPedidoDTO(List<ItemPedido> items) {
		if(CollectionUtils.isEmpty(items)) {
			return Collections.EMPTY_LIST;
		} else {
			return items.stream().map(item -> {
				InformacaoItemPedidoDTO info = new InformacaoItemPedidoDTO();
				info.setDescricaoProduto(item.getProduto().getDescricao());
				info.setPreco(item.getProduto().getPreco());
				info.setQuantidade(item.getQuantidade());
				return info;
			}).collect(Collectors.toList());
		}
	}
	
	@PatchMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateStatus(@PathVariable Integer id, @RequestBody AtualizacaoStatusPedidoDTO dto) {
		service.atualizaStatus(id, StatusPedido.valueOf(dto.getNovoStatus()));
	}
}
