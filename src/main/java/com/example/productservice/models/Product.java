package com.example.productservice.models;

import lombok.Builder;
import lombok.Data;

/**
 * The type Product.
 */
@Data
@Builder
public class Product {
    private Long id;
    private String title;
    private String description;
    private double price;
    private String imageURL;
    private Category category;
}
