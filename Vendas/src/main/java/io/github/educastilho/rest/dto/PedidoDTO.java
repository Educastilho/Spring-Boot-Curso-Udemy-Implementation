package io.github.educastilho.rest.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import javax.validation.constraints.NotNull;

import io.github.educastilho.validation.NotEmptyList;

//{
//    "cliente": 1,
//    "total": 5,
//    "items": [
//        {
//                "produto": 1,
//                "quantidade": 10
//        }
//    ]
//}

public class PedidoDTO {

	@NotNull(message = "{campo.codigo-cliente.obrigatorio}")
	private Integer cliente_id;
	@NotNull(message = "{campo.total-pedido.obrigatorio}")
	private BigDecimal total;
	@NotEmptyList(message = "{campo.items-pedido.obrigatorio}")
	private List<ItemPedidoDTO> itemsPedido;
	
	public PedidoDTO() {
		
	}	
	
	public PedidoDTO(Integer cliente_id, BigDecimal total, List<ItemPedidoDTO> itemsPedido) {
		this.cliente_id = cliente_id;
		this.total = total;
		this.itemsPedido = itemsPedido;
	}

	public Integer getCliente_id() {
		return cliente_id;
	}
	public void setCliente_id(Integer cliente_id) {
		this.cliente_id = cliente_id;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public List<ItemPedidoDTO> getItemsPedido() {
		return itemsPedido;
	}
	public void setItemsPedido(List<ItemPedidoDTO> itemsPedido) {
		this.itemsPedido = itemsPedido;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cliente_id, itemsPedido, total);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PedidoDTO other = (PedidoDTO) obj;
		return Objects.equals(cliente_id, other.cliente_id) && Objects.equals(itemsPedido, other.itemsPedido)
				&& Objects.equals(total, other.total);
	}

	@Override
	public String toString() {
		return "PedidoDTO [cliente_id=" + cliente_id + ", total=" + total + ", itemsPedido=" + itemsPedido + "]";
	}
	
}
