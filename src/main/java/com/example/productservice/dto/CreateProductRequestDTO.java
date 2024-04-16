package com.example.productservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateProductRequestDTO {
    private String title;
    private String description;
    private Double price;
    private String category;
    private String image;
}
