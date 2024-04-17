package com.example.productservice.dto;

import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class FakeStoreProductDTO {
    private Long id;
    private String title;
    private Double price;
    private String category;
    private String description;
    private String image;


    public Product toProduct() {
        return Product.builder()
                .category(Category.builder()
                        .name(category)
                        .build())
                .imageURL(image)
                .description(description)
                .price(price)
                .title(title)
                .id(id)
                .build();
    }
}
