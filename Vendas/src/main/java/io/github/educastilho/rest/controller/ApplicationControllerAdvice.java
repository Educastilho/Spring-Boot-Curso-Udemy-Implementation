package io.github.educastilho.rest.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.github.educastilho.exception.PedidoNaoEncontradoException;
import io.github.educastilho.exception.RegraNegocioException;
import io.github.educastilho.rest.ApiErrors;

@RestControllerAdvice
public class ApplicationControllerAdvice {

	@ExceptionHandler(RegraNegocioException.class)
	public ApiErrors handleRegraNegocioException(RegraNegocioException ex) {
		String mensagemErro = ex.getMessage();
		return new ApiErrors(mensagemErro);
	}
	
	@ExceptionHandler(PedidoNaoEncontradoException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ApiErrors handlePedidoNotFoundException(PedidoNaoEncontradoException ex) {
		String mensagemErro = ex.getMessage();
		return new ApiErrors(mensagemErro);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ApiErrors handleMethodNotValidException(MethodArgumentNotValidException ex) {
		List<String> errors = ex.getBindingResult()
				.getAllErrors()
				.stream()
				.map(erro -> erro.getDefaultMessage())
				.collect(Collectors.toList());
		return new ApiErrors(errors);
	}
}
