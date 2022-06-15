package io.github.educastilho.rest.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class InformacoesPedidoDTO {

	private Integer codigo;
	private String cpf;
	private String nomeCliente;
	private BigDecimal total;
	private String dataPedido;
	private String status;
	private List<InformacaoItemPedidoDTO> items;
	
	public InformacoesPedidoDTO() {
		
	}

	public InformacoesPedidoDTO(Integer codigo, String cpf, String nomeCliente, BigDecimal total, String dataPedido,
			String status, List<InformacaoItemPedidoDTO> items) {
		this.codigo = codigo;
		this.cpf = cpf;
		this.nomeCliente = nomeCliente;
		this.total = total;
		this.dataPedido = dataPedido;
		this.status = status;
		this.items = items;
	}

	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public String getDataPedido() {
		return dataPedido;
	}
	public void setDataPedido(String dataPedido) {
		this.dataPedido = dataPedido;
	}
	public List<InformacaoItemPedidoDTO> getItems() {
		return items;
	}
	public void setItems(List<InformacaoItemPedidoDTO> items) {
		this.items = items;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo, cpf, dataPedido, items, nomeCliente, status, total);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InformacoesPedidoDTO other = (InformacoesPedidoDTO) obj;
		return Objects.equals(codigo, other.codigo) && Objects.equals(cpf, other.cpf)
				&& Objects.equals(dataPedido, other.dataPedido) && Objects.equals(items, other.items)
				&& Objects.equals(nomeCliente, other.nomeCliente) && Objects.equals(status, other.status)
				&& Objects.equals(total, other.total);
	}

	@Override
	public String toString() {
		return "InformacoesPedidoDTO [codigo=" + codigo + ", cpf=" + cpf + ", nomeCliente=" + nomeCliente + ", total="
				+ total + ", dataPedido=" + dataPedido + ", status=" + status + ", items=" + items + "]";
	}
	
}
