package com.example.bikerental.exceptions;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.jsonwebtoken.ExpiredJwtException;

@RestControllerAdvice
public class AdminExceptionHandler {
	Logger logger = LoggerFactory.getLogger(AdminExceptionHandler.class);
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity< Map<String,String>> handleInvalidArguments(ConstraintViolationException ex)
	{
		Map<String, String> errorMap=new HashMap<>();

		ex.getConstraintViolations().forEach(err->
			errorMap.put(err.getPropertyPath().toString(), err.getMessageTemplate())
		);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap);
	}
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity< Map<String,String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex)
	{
		Map<String, String> errorMap=new HashMap<>();
		  BindingResult result = ex.getBindingResult();
		    final List<FieldError> fieldErrors = result.getFieldErrors();
		    fieldErrors.forEach(err->
				errorMap.put(err.getField(),err.getDefaultMessage())
			);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap);
	}
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ExpiredJwtException.class)
	public ResponseEntity< Map<String,String>> handleExpiredJwtException(ExpiredJwtException ex)
	{
		Map<String, String> errorMap=new HashMap<>();
		errorMap.put("Jwt", "expired");

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap);
	}
}
