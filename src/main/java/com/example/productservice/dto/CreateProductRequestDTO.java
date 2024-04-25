package com.example.productservice.dto;

import com.example.productservice.models.Product;
import lombok.Builder;
import lombok.Data;

/**
 * The type Create product request dto.
 */
@Data
@Builder
public class CreateProductRequestDTO {
    private String title;
    private String description;
    private Double price;
    private String category;
    private String image;

    /**
     * To product product.
     *
     * @return the product
     */
    public Product toProduct() {
        return Product.builder()
                .title(title)
                .price(price)
                .description(description)
                .imageURL(image)
                .build();
    }
}
