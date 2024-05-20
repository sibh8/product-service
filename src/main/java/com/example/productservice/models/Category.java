package com.example.productservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

/**
 * The type Category.
 */
@Data
@Entity
public class Category extends ProductCommon implements Serializable {
    private String name;

    /**
     * The Products.
     */
    @OneToMany(mappedBy = "category")
    List<Product> products;


    /**
     * Instantiates a new Category.
     *
     * @param id              the id
     * @param createdAt       the created at
     * @param updatedAt       the updated at
     * @param createdByUserId the created by user id
     * @param name            the name
     * @param products        the products
     */
    @Builder
    public Category(Integer id, Instant createdAt, Instant updatedAt, String createdByUserId, String name, List<Product> products) {
        super(id, createdAt, updatedAt, createdByUserId);
        this.name = name;
        this.products = products;
    }

    /**
     * Instantiates a new Category.
     */
    public Category() {
        super();
    }
}
