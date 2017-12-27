package com.sap.grc.bod.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ConflictException extends RuntimeException{
	private static final long serialVersionUID = 1697442498408544174L;

    public ConflictException(String message) {
        super(message);
    }
}