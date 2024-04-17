package com.example.productservice.models;

import lombok.Builder;
import lombok.Data;

/**
 * The type Category.
 */
@Data
@Builder
public class Category {
    private Long id;
    private String name;
}
