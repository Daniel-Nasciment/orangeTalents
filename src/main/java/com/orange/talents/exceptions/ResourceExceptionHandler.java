package com.orange.talents.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.orange.talents.resources.exception.StandardError;
import com.orange.talents.resources.exception.ValidationError;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e) {
		StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);

	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<StandardError> handlerHttpMessageNotReadableException(HttpMessageNotReadableException exception) {
		StandardError error = new StandardError();

		exception.printStackTrace();
		
		error.setTimeStamp(System.currentTimeMillis());

		if (exception.getCause() instanceof InvalidFormatException) {
			error.setStatus(HttpStatus.BAD_REQUEST.value());
			error.setMsg("Erro ao converter a Data");
		}
		
		if (exception.getCause() instanceof NullPointerException) {
			error.setStatus(HttpStatus.BAD_REQUEST.value());
			error.setMsg("Dado não informado no request, verificar campos.");
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);

	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> handlerMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		ValidationError err = new ValidationError(HttpStatus.BAD_REQUEST.value(), "Erro de validação", System.currentTimeMillis());

		for (FieldError x : e.getBindingResult().getFieldErrors()) {
			err.addErros(x.getField(), x.getDefaultMessage());
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);

	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<StandardError> handlerDataIntegrityViolationException(DataIntegrityViolationException e) {
		ValidationError err = new ValidationError(HttpStatus.BAD_REQUEST.value(), "Erro de validação", System.currentTimeMillis());
		
		e.printStackTrace();
		
		if(e.getCause().getCause().toString().contains("CONTA_BANCO(CPF)")) {
			err.addErros("cpf", "Erro de CPF duplicado.");
		}
		
		if(e.getCause().getCause().toString().contains("CONTA_BANCO(EMAIL)")) {
			err.addErros("email", "Erro de E-mail duplicado.");
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);

	}

}
