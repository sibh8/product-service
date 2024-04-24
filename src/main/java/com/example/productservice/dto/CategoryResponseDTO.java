package com.example.productservice.dto;

import com.example.productservice.models.ProductCommon;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

/**
 * The type Category response dto.
 */
@Data
public class CategoryResponseDTO extends ProductCommon {
    private String categoryName;

    @Builder

    public CategoryResponseDTO(Integer id, Instant createdAt, Instant updatedAt, String categoryName) {
        super(id, createdAt, updatedAt);
        this.categoryName = categoryName;
    }
}
