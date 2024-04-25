package com.example.productservice.advice;

import com.example.productservice.dto.ErrorDTO;
import com.example.productservice.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * The type Controller advices.
 */
@ControllerAdvice
public class ControllerAdvices {
    /**
     * Handle product not found exception response entity.
     *
     * @return the response entity
     */
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleProductNotFoundException() {
        var errorDTO = ErrorDTO.builder().errorCode(102).errorMessage("No Products were found").build();

        return new ResponseEntity<>(errorDTO, HttpStatus.NOT_FOUND);
    }
}
