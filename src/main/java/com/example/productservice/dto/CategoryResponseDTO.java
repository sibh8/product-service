package com.example.productservice.dto;

import lombok.Builder;
import lombok.Data;

/**
 * The type Category response dto.
 */
@Data
@Builder
public class CategoryResponseDTO {
    private String categoryName;
}
