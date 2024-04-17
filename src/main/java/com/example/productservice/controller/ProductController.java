package com.example.productservice.controller;

import com.example.productservice.dto.CategoryResponseDTO;
import com.example.productservice.dto.CreateProductRequestDTO;
import com.example.productservice.dto.ProductResponseDTO;
import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import com.example.productservice.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Product controller.
 */
@RestController
public class ProductController {

    private ProductService productService;

    /**
     * Instantiates a new Product controller.
     *
     * @param productService the product service
     */
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Gets all products.
     *
     * @return the all products
     */
    @GetMapping("/products")
    public List<ProductResponseDTO> getAllProducts() {
        List<ProductResponseDTO> productsResponseDTO = new ArrayList<>();
        List<Product> products = productService.getAllProducts();

        for (Product product : products) {
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

    /**
     * Gets product by id.
     *
     * @param id the id
     * @return the product by id
     */
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

    /**
     * Create product product.
     *
     * @param createProductRequestDTO the create product request dto
     * @return the product
     */
    @PostMapping("/products")
    public Product createProduct(@RequestBody CreateProductRequestDTO createProductRequestDTO) {
        return productService.createProduct(createProductRequestDTO);
    }

    /**
     * Gets all categories.
     *
     * @return the all categories
     */
    @GetMapping("/categories")
    public List<CategoryResponseDTO> getAllCategories() {
        List<CategoryResponseDTO> categoriesResponse = new ArrayList<>();
        List<Category> categories = productService.getAllCategories();
        for (Category category : categories) {
            categoriesResponse.add(CategoryResponseDTO.builder().categoryName(category.getName()).build());
        }
        return categoriesResponse;
    }

    /**
     * Gets products in specifig category.
     *
     * @param categoryName the category name
     * @return the products in specifig category
     */
    @GetMapping("/products/category/{categoryName}")
    public List<ProductResponseDTO> getProductsInSpecifigCategory(@PathVariable("categoryName") String categoryName) {
        List<ProductResponseDTO> productsResponseDTO = new ArrayList<>();
        List<Product> products = productService.getProductsInSpecificCategory(categoryName);
        for (Product product : products) {
            productsResponseDTO.add(ProductResponseDTO.builder()
                    .title(product.getTitle())
                    .id(product.getId())
                    .price(product.getPrice())
                    .description(product.getDescription())
                    .imageURL(product.getImageURL())
                    .build());
        }

        return productsResponseDTO;
    }

    /**
     * Update product product response dto.
     *
     * @param productId               the product id
     * @param createProductRequestDTO the create product request dto
     * @return the product response dto
     */
    @PutMapping("/product/{id}")
    public ProductResponseDTO updateProduct(@PathVariable("id") Integer productId,
                                 @RequestBody CreateProductRequestDTO createProductRequestDTO) {

        Product product = productService.updateProduct(productId, createProductRequestDTO);

        var productResponseDTO = ProductResponseDTO.builder()
                .id(product.getId())
                .title(product.getTitle())
                .price(product.getPrice())
                .description(product.getDescription())
                .imageURL(product.getImageURL())
                .category(product.getCategory())
                .build();

        return productResponseDTO;
    }
}
