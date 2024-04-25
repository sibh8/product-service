package com.example.productservice.models;

import com.example.productservice.dto.ProductResponseDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

/**
 * The type Product.
 */
@Data
@Entity
public class Product extends ProductCommon {
    private String title;
    private String description;
    private double price;
    private String imageURL;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    private Category category;

    /**
     * Instantiates a new Product.
     *
     * @param id          the id
     * @param createdAt   the created at
     * @param updatedAt   the updated at
     * @param title       the title
     * @param description the description
     * @param price       the price
     * @param imageURL    the image url
     * @param category    the category
     */
    @Builder
    public Product(Integer id, Instant createdAt, Instant updatedAt, String title, String description, double price, String imageURL, Category category) {
        super(id, createdAt, updatedAt);
        this.title = title;
        this.description = description;
        this.price = price;
        this.imageURL = imageURL;
        this.category = category;
    }

    /**
     * Instantiates a new Product.
     */
    public Product() {
        super();
    }

    public ProductResponseDTO toProductResponseDTO() {
        return ProductResponseDTO.builder()
                .imageURL(imageURL)
                .id(getId())
                .category(category)
                .description(description)
                .price(price)
                .title(title)
                .createdAt(getCreatedAt())
                .updatedAt(getUpdatedAt())
                .build();
    }
}
