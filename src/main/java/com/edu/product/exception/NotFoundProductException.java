package com.edu.product.exception;

public class NotFoundProductException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public NotFoundProductException(String message) {
		super(message);
	}
}
