package com.edu.product.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(name = "ApiErrorResponse", description = "Response Error Object")
@Data
public class ApiErrorResponseDTO {
	
	@Schema(description = "error code", required = true)
	private int code;
	@Schema(description = "type of error")
	private String type;
	@Schema(description = "description of the error")
	private String description;
}
