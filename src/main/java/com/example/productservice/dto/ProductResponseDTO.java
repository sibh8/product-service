package com.example.productservice.dto;

import com.example.productservice.models.Category;
import com.example.productservice.models.ProductCommon;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

/**
 * The type Product response dto.
 */
@Data
public class ProductResponseDTO extends ProductCommon {
    private String title;
    private String description;
    private double price;
    private String imageURL;
    private Category category;

    /**
     * Instantiates a new Product response dto.
     *
     * @param id              the id
     * @param createdAt       the created at
     * @param updatedAt       the updated at
     * @param createdByUserId the created by user id
     * @param title           the title
     * @param description     the description
     * @param price           the price
     * @param imageURL        the image url
     * @param category        the category
     */
    @Builder
    public ProductResponseDTO(Integer id, Instant createdAt, Instant updatedAt, String createdByUserId, String title, String description, double price, String imageURL, Category category) {
        super(id, createdAt, updatedAt, createdByUserId);
        this.title = title;
        this.description = description;
        this.price = price;
        this.imageURL = imageURL;
        this.category = category;
    }
}
