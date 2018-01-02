package com.sap.grc.bod.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class BusinessObjectExceptionHandler {

	@ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String handleBusinessObjectCustomeException(BusinessObjectCustomException e){
		return e.toString();
	}
}
