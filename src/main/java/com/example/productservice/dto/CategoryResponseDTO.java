package com.example.productservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryResponseDTO {
    private String categoryName;
}
