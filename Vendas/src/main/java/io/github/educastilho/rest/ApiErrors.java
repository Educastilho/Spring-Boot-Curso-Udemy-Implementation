package io.github.educastilho.rest;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ApiErrors {

	public List<String> errors;
	
	public ApiErrors(String mensagemErro) {
		this.errors = Arrays.asList(mensagemErro);
	}

	public ApiErrors() {
		super();
	}

	public ApiErrors(List<String> errors2) {
		this.errors = errors2;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	@Override
	public int hashCode() {
		return Objects.hash(errors);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ApiErrors other = (ApiErrors) obj;
		return Objects.equals(errors, other.errors);
	}

	@Override
	public String toString() {
		return "ApiErrors [errors=" + errors + "]";
	}
}
