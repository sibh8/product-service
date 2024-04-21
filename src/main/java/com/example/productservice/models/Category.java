package com.example.productservice.models;

import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * The type Category.
 */
@Data
@Entity
public class Category extends ProductCommon{
    private String name;

    @Builder
    public Category(Long id, Date createdAt, Date updatedAt, String name) {
        super(id, createdAt, updatedAt);
        this.name = name;
    }

    public Category() {
        super();
    }
}
