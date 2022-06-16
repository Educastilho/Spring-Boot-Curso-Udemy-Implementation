package io.github.educastilho.validation.constraintvalidation;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import io.github.educastilho.validation.NotEmptyList;

public class NotEmptyListValidator implements ConstraintValidator<NotEmptyList, List> {

	@Override
	public boolean isValid(List value, ConstraintValidatorContext context) {		
		return value != null && !value.isEmpty();
	}

	@Override
	public void initialize(NotEmptyList constraintAnnotation) {
		
	}
	
	

}
