package com.example.productservice.models;

import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

/**
 * The type Category.
 */
@Data
@Entity
public class Category extends ProductCommon {
    private String name;

    /**
     * Instantiates a new Category.
     *
     * @param id        the id
     * @param createdAt the created at
     * @param updatedAt the updated at
     * @param name      the name
     */
    @Builder
    public Category(Integer id, Instant createdAt, Instant updatedAt, String name) {
        super(id, createdAt, updatedAt);
        this.name = name;
    }

    /**
     * Instantiates a new Category.
     */
    public Category() {
        super();
    }
}
