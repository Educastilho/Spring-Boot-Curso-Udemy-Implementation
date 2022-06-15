package io.github.educastilho.rest.dto;

import java.math.BigDecimal;
import java.util.Objects;

public class InformacaoItemPedidoDTO {

	private String descricaoProduto;
	private BigDecimal preco;
	private Integer quantidade;
	
	public InformacaoItemPedidoDTO() {
		
	}
	
	public InformacaoItemPedidoDTO(String descricaoProduto, BigDecimal preco, Integer quantidade) {
		this.descricaoProduto = descricaoProduto;
		this.preco = preco;
		this.quantidade = quantidade;
	}
	
	public String getDescricaoProduto() {
		return descricaoProduto;
	}
	public void setDescricaoProduto(String descricaoProduto) {
		this.descricaoProduto = descricaoProduto;
	}
	public BigDecimal getPreco() {
		return preco;
	}
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	@Override
	public int hashCode() {
		return Objects.hash(descricaoProduto, preco, quantidade);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InformacaoItemPedidoDTO other = (InformacaoItemPedidoDTO) obj;
		return Objects.equals(descricaoProduto, other.descricaoProduto) && Objects.equals(preco, other.preco)
				&& Objects.equals(quantidade, other.quantidade);
	}

	@Override
	public String toString() {
		return "InformacaoItemPedidoDTO [descricaoProduto=" + descricaoProduto + ", preco=" + preco + ", quantidade="
				+ quantidade + "]";
	}
	
}
