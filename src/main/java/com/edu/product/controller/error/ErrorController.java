package com.edu.product.controller.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.edu.product.exception.BadRequestProductException;
import com.edu.product.exception.NotFoundProductException;
import com.edu.product.model.dto.ApiErrorResponseDTO;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import io.swagger.v3.oas.annotations.Hidden;

@RestControllerAdvice
public class ErrorController {
	
	@Hidden
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NotFoundProductException.class)
	public ApiErrorResponseDTO handleNotFoundProductException(NotFoundProductException exception) {  
		ApiErrorResponseDTO apiErrorResponseDTO = new ApiErrorResponseDTO();
		apiErrorResponseDTO.setCode(HttpStatus.NOT_FOUND.value());
		apiErrorResponseDTO.setType("DataNotFound");
		apiErrorResponseDTO.setDescription(String.format("Product %s not found", exception.getMessage()));
		return apiErrorResponseDTO;
	}
	
	@Hidden
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ApiErrorResponseDTO handleHttpMessageNotReadableException(HttpMessageNotReadableException exception) {
		Throwable exceptionClass = exception.getMostSpecificCause();
		ApiErrorResponseDTO apiErrorResponseDTO = new ApiErrorResponseDTO();
		apiErrorResponseDTO.setCode(HttpStatus.BAD_REQUEST.value());
		apiErrorResponseDTO.setType("MessageNotReadable");
		apiErrorResponseDTO.setDescription(exception.getMessage());
		if (exceptionClass instanceof InvalidFormatException) {  
			apiErrorResponseDTO.setDescription("Error format JSON, values mismatch");
		}else if (exceptionClass instanceof JsonParseException) {
			apiErrorResponseDTO.setDescription("Error parsing JSON, malformed JSON");
		} 
		return apiErrorResponseDTO;
	}
	
	@Hidden
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ApiErrorResponseDTO handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {  
		ApiErrorResponseDTO apiErrorResponseDTO = new ApiErrorResponseDTO();
		apiErrorResponseDTO.setCode(HttpStatus.BAD_REQUEST.value());
		apiErrorResponseDTO.setType("MethodArgumentNotValid");
		apiErrorResponseDTO.setDescription(String.format("JSON invalid - %s ",exception.getFieldError().getDefaultMessage()));
		return apiErrorResponseDTO;
	}
	
	@Hidden
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(BadRequestProductException.class)
	public ApiErrorResponseDTO handleBadRequestProductException(BadRequestProductException exception) {  
		ApiErrorResponseDTO apiErrorResponseDTO = new ApiErrorResponseDTO();
		apiErrorResponseDTO.setCode(HttpStatus.BAD_REQUEST.value());
		apiErrorResponseDTO.setType("Bad Request");
		apiErrorResponseDTO.setDescription(String.format("Product %s already exits", exception.getMessage()));
		return apiErrorResponseDTO;
	}
	
	@Hidden
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ApiErrorResponseDTO handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception) {  
		Throwable exceptionClass = exception.getMostSpecificCause(); 
		ApiErrorResponseDTO apiErrorResponseDTO = new ApiErrorResponseDTO();
		apiErrorResponseDTO.setCode(HttpStatus.BAD_REQUEST.value());
		apiErrorResponseDTO.setType("Bad Request");
		apiErrorResponseDTO.setDescription(exception.getMessage());
		if (exceptionClass instanceof NumberFormatException) {
			apiErrorResponseDTO.setDescription("Error: Product id format is not correct");
		}
		return apiErrorResponseDTO;
	} 
	 
}
