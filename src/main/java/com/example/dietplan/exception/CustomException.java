package com.example.dietplan.exception;

import java.time.LocalDateTime;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomException extends ResponseEntityExceptionHandler {

	@ExceptionHandler(InvalidInputParamException.class)
	public ResponseEntity<CustomErrorResponse> invalidgender(Exception ex, WebRequest requeste) {
		CustomErrorResponse errors = new CustomErrorResponse();
		errors.setTimestamp(LocalDateTime.now());
		errors.setError(ex.getMessage());
		errors.setStatus(HttpStatus.BAD_REQUEST.value());

		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(ConstraintViolationException.class)
	public final ResponseEntity<CustomErrorResponse> handleMissingParams(ConstraintViolationException ex,
			HttpServletResponse response) {
		CustomErrorResponse errors = new CustomErrorResponse();
		errors.setTimestamp(LocalDateTime.now());
		errors.setError(ex.getMessage());
		errors.setStatus(HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<CustomErrorResponse>(errors, HttpStatus.BAD_REQUEST);
	}

}
