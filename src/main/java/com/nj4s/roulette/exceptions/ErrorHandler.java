package com.nj4s.roulette.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.nj4s.roulette.util.Constants;

@ControllerAdvice
public class ErrorHandler {

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<ExceptionInfo> badRequestException(HttpServletRequest request, BadRequestException e) {
		ExceptionInfo exceptionInfo = new ExceptionInfo(HttpStatus.BAD_REQUEST.value(), e.getClass().getSimpleName(),
				e.getMessage(), request.getRequestURI());
		return new ResponseEntity<>(exceptionInfo, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(com.fasterxml.jackson.databind.exc.InvalidFormatException.class)
	public ResponseEntity<ExceptionInfo> badRequestException(HttpServletRequest request,
			com.fasterxml.jackson.databind.exc.InvalidFormatException e) {
		ExceptionInfo exceptionInfo = new ExceptionInfo(HttpStatus.BAD_REQUEST.value(), e.getClass().getSimpleName(),
				Constants.BET_OPTION_NOT_VALID, request.getRequestURI());
		return new ResponseEntity<>(exceptionInfo, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(org.springframework.web.bind.MissingRequestHeaderException.class)
	public ResponseEntity<ExceptionInfo> badRequestException(HttpServletRequest request,
			org.springframework.web.bind.MissingRequestHeaderException e) {
		ExceptionInfo exceptionInfo = new ExceptionInfo(HttpStatus.BAD_REQUEST.value(), e.getClass().getSimpleName(),
				e.getMessage(), request.getRequestURI());
		return new ResponseEntity<>(exceptionInfo, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(org.springframework.web.method.annotation.MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ExceptionInfo> badRequestException(HttpServletRequest request,
			org.springframework.web.method.annotation.MethodArgumentTypeMismatchException e) {
		ExceptionInfo exceptionInfo = new ExceptionInfo(HttpStatus.BAD_REQUEST.value(), e.getClass().getSimpleName(),
				e.getMessage(), request.getRequestURI());
		return new ResponseEntity<>(exceptionInfo, HttpStatus.BAD_REQUEST);
	}

}
