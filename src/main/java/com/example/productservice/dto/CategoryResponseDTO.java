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

    /**
     * Instantiates a new Category response dto.
     *
     * @param id              the id
     * @param createdAt       the created at
     * @param updatedAt       the updated at
     * @param createdByUserId the created by user id
     * @param categoryName    the category name
     */
    @Builder
    public CategoryResponseDTO(Integer id, Instant createdAt, Instant updatedAt, String createdByUserId, String categoryName) {
        super(id, createdAt, updatedAt, createdByUserId);
        this.categoryName = categoryName;
    }
}
