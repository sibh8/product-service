package com.example.productservice.controller;

import com.example.productservice.dto.CreateProductRequestDTO;
import com.example.productservice.dto.ProductResponseDTO;
import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import com.example.productservice.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<ProductResponseDTO> getAllProducts() {
        List<ProductResponseDTO> productsResponseDTO = new ArrayList<>();
        List<Product> products = productService.getAllProducts();

        for(Product product: products){
            productsResponseDTO.add(ProductResponseDTO.builder()
                            .imageURL(product.getImageURL())
                            .id(product.getId())
                            .category(Category.builder()
                                    .name(product.getCategory().getName())
                                    .build())
                            .description(product.getDescription())
                            .price(product.getPrice())
                            .title(product.getTitle())
                    .build());
        }
        return productsResponseDTO;
    }

    @GetMapping("/products/{id}")
    public ProductResponseDTO getProductById(@PathVariable("id") Integer id) {
        var product = productService.getProductById(id);

        var productResponseDTO = ProductResponseDTO.builder()
                .id(product.getId())
                .title(product.getTitle())
                .price(product.getPrice())
                .description(product.getDescription())
                .category(product.getCategory())
                .imageURL(product.getImageURL())
                .build();

        return productResponseDTO;
    }

    @PostMapping("/products")
    public Product createProduct(@RequestBody CreateProductRequestDTO createProductRequestDTO) {
        return productService.createProduct(createProductRequestDTO);
    }
}
