package com.example.productservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * The type Product.
 */
@Data
@Entity
public class Product extends ProductCommon{
    private String title;
    private String description;
    private double price;
    private String imageURL;

    @ManyToOne
    private Category category;

    @Builder
    public Product(Long id, Date createdAt, Date updatedAt, String title, String description, double price, String imageURL, Category category) {
        super(id, createdAt, updatedAt);
        this.title = title;
        this.description = description;
        this.price = price;
        this.imageURL = imageURL;
        this.category = category;
    }

    public Product() {
        super();
    }
}
