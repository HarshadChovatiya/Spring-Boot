package com.sample.app.exception.handle;

import java.util.Date;

//import javax.servlet.ServletException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import com.sample.app.model.ErrorDetail;

@ControllerAdvice
public class CustomExceptionHandler {
	
//	@ExceptionHandler(ServletException.class)
//	public final ResponseEntity<ErrorDetail> handleServletException(ServletException ex, WebRequest request) {
//	    ErrorDetail errorDetail = new ErrorDetail(new Date(),
//	    		ex.getMessage(),
//	    		request.getDescription(false));
//		return new ResponseEntity<ErrorDetail>(errorDetail, HttpStatus.NOT_FOUND);
//	}
	
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public final ResponseEntity<ErrorDetail> handleEmptyResultDataAccessException(
			Exception ex, WebRequest request) {
		ErrorDetail errorDetail = new ErrorDetail(new Date(),
				ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<ErrorDetail>(errorDetail, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(InvalidFormatException.class)
	public final ResponseEntity<ErrorDetail> invalidFormatException(
			Exception ex, WebRequest request) {
		ErrorDetail errorDetail = new ErrorDetail(new Date(),
				ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<ErrorDetail>(errorDetail, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorDetail> handleException(Exception ex, WebRequest request) {
	    ErrorDetail errorDetail = new ErrorDetail(new Date(),
	    		ex.getMessage(),
	    		request.getDescription(false));
		return new ResponseEntity<ErrorDetail>(errorDetail, HttpStatus.BAD_REQUEST);
	}
	
}
