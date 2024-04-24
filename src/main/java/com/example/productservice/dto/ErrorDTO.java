package com.example.productservice.dto;

import lombok.Builder;
import lombok.Data;

/**
 * The type Error dto.
 */
@Data
@Builder
public class ErrorDTO {
    private Integer errorCode;
    private String errorMessage;
}
