package com.example.productservice.controller;

import com.example.productservice.dto.CategoryResponseDTO;
import com.example.productservice.dto.CreateProductRequestDTO;
import com.example.productservice.dto.ProductResponseDTO;
import com.example.productservice.exception.ProductNotFoundException;
import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import com.example.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Product controller.
 */
@RestController
public class ProductController {

    private final ProductService productService;

    /**
     * Instantiates a new Product controller.
     *
     * @param productService the product service
     */
    public ProductController(@Qualifier("selfdbproductservice") ProductService productService) {
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
            productsResponseDTO.add(product.toProductResponseDTO());
        }
        return productsResponseDTO;
    }

    /**
     * Gets product by id.
     *
     * @param id the id
     * @return the product by id
     * @throws ProductNotFoundException the product not found exception
     */
    @GetMapping("/productsresponse/{id}")
    public ResponseEntity<ProductResponseDTO> getProductRespoonseById(@PathVariable("id") Integer id) throws ProductNotFoundException {
        var product = productService.getProductById(id);

        if (product == null) {
            throw new ProductNotFoundException();
        }

        var productResponseDTO = product.toProductResponseDTO();

        return new ResponseEntity<>(productResponseDTO, HttpStatus.OK);
    }

    /**
     * Gets product by id.
     *
     * @param id the id
     * @return the product by id
     */
    @Cacheable(value = "product")
    @GetMapping("/products/{id}")
    public ProductResponseDTO getProductById(@PathVariable("id") Integer id){
        var product = productService.getProductById(id);

        var productResponseDTO = product.toProductResponseDTO();

        return productResponseDTO;
    }

    /**
     * Create product product.
     *
     * @param createProductRequestDTO the create product request dto
     * @return the product
     */
    @CachePut(value = "product", key = "#createProductRequestDTO.title")
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
            categoriesResponse.add(CategoryResponseDTO.builder()
                    .id(category.getId())
                    .categoryName(category.getName())
                    .updatedAt(category.getUpdatedAt())
                    .createdAt(category.getCreatedAt())
                    .build());
        }
        return categoriesResponse;
    }

    /**
     * Gets products in specifig category.
     *
     * @param categoryName the category name
     * @return the products in specifig category
     * @throws ProductNotFoundException the product not found exception
     */
    @GetMapping("/products/category/{categoryName}")
    public ResponseEntity<List<?>> getProductsInSpecifigCategory(@PathVariable("categoryName") String categoryName) throws ProductNotFoundException {
        List<ProductResponseDTO> productsResponseDTO = new ArrayList<>();
        List<Product> products = productService.getProductsInSpecificCategory(categoryName);
        if (products.size() == 0) {
//            return new ResponseEntity(new ArrayList<>(), HttpStatus.NO_CONTENT);
            throw new ProductNotFoundException();
        }
        for (Product product : products) {
            productsResponseDTO.add(product.toProductResponseDTO());
        }

        return new ResponseEntity<>(productsResponseDTO, HttpStatus.OK);
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

        return product.toProductResponseDTO();
    }

    /**
     * Delete product response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @PostMapping("/product/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Integer id) {
        var product = productService.getProductById(id);
        if (product == null) {
            var message = "Record with ID " + id + " does not exist in database";
            return new ResponseEntity<>(message, HttpStatus.NO_CONTENT);
        } else {
            productService.deleteProduct(id);
            var message = "Record with ID " + id + " is now deleted";
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
    }


    /**
     * Gets paginated product.
     *
     * @param pageNo   the page no
     * @param pageSize the page size
     * @return the paginated product
     */
    @GetMapping("/products/{pageNo}/{pageSize}")
    public ResponseEntity<List<Product>> getPaginatedProduct(@PathVariable("pageNo") Integer pageNo, @PathVariable("pageSize") Integer pageSize){

        Page<Product> products = productService.getPaginatedProduct(pageNo, pageSize);

        return ResponseEntity.ok(products.getContent());
    }
}
