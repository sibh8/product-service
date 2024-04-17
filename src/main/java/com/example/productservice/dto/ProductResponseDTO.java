package com.example.productservice.dto;

import com.example.productservice.models.Category;
import lombok.Builder;
import lombok.Data;

/**
 * The type Product response dto.
 */
@Builder
@Data
public class ProductResponseDTO {
    private Long id;
    private String title;
    private String description;
    private double price;
    private String imageURL;
    private Category category;
}
