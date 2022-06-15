package io.github.educastilho.exception;

public class PedidoNaoEncontradoException extends RuntimeException {

	public PedidoNaoEncontradoException(String message) {
		super("Pedido Não Encontrado");
	}
}
