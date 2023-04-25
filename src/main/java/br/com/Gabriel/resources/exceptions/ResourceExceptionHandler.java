package br.com.Gabriel.resources.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.Gabriel.services.exceptions.DatabaseException;
import br.com.Gabriel.services.exceptions.ResourceNotFoundExceptions;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice //interceptar as excessoes que acontecerem para que esse obj possa realizar o tratamento
public class ResourceExceptionHandler {

	@ExceptionHandler(ResourceNotFoundExceptions.class)  //INTERCEPTAR A REQUISIÇÃO QUE DEU EXCESSÃO PARA CAIR AQUI
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundExceptions e, HttpServletRequest request) {
		String error = "Resource not found";
		HttpStatus status = HttpStatus.NOT_FOUND; // 404
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(DatabaseException.class)  
	public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request) {
		String error = "Database Error";
		HttpStatus status = HttpStatus.BAD_REQUEST; // 400
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
}
