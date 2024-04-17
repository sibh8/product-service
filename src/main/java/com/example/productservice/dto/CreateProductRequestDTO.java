package com.example.productservice.dto;

import lombok.Builder;
import lombok.Data;

/**
 * The type Create product request dto.
 */
@Data
@Builder
public class CreateProductRequestDTO {
    private String title;
    private String description;
    private Double price;
    private String category;
    private String image;
}
