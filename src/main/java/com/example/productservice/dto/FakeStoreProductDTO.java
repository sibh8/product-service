package com.example.productservice.dto;

import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import lombok.Builder;
import lombok.Data;

/**
 * The type Fake store product dto.
 */
@Builder
@Data
public class FakeStoreProductDTO {
    private Long id;
    private String title;
    private Double price;
    private String category;
    private String description;
    private String image;


    /**
     * To product product.
     *
     * @return the product
     */
    public Product toProduct() {
        var product = Product.builder()
                .id(id)
                .category(Category.builder()
                        .name(category)
                        .build())
                .imageURL(image)
                .description(description)
                .price(price)
                .title(title)
                .build();

        return product;
    }
}
