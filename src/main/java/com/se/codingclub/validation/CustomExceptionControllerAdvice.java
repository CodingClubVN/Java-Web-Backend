package com.se.codingclub.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@RestController
public class CustomExceptionControllerAdvice {
	@ExceptionHandler({ ConstraintViolationException.class })
	public ResponseEntity<Object> handleException(ConstraintViolationException exc, WebRequest request) {
		List<String> errors = new ArrayList<String>();
		for (ConstraintViolation<?> violation : exc.getConstraintViolations()) {
			errors.add(violation.getRootBeanClass().getName() + " " + violation.getPropertyPath() + ": "
					+ violation.getMessage());
		}
		ResponeError errorRespone = new ResponeError();
		errorRespone.setStatus(HttpStatus.BAD_REQUEST);
		errorRespone.setMessage(exc.getLocalizedMessage());
		errorRespone.setErrors(errors);
		return new ResponseEntity<Object>(errorRespone, new HttpHeaders(), errorRespone.getStatus());
	}
}
