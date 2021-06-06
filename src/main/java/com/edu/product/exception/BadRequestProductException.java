package com.edu.product.exception;

public class BadRequestProductException extends RuntimeException{
 
	private static final long serialVersionUID = 1L;

	public BadRequestProductException(String message) {
		super(message);
	}
}
