package com.example.productservice.service;

import com.example.productservice.dto.CreateProductRequestDTO;
import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * The interface Product service.
 */
public interface ProductService {
    /**
     * Gets product by id.
     *
     * @param id the id
     * @return the product by id
     */
    Product getProductById(Integer id);

    /**
     * Gets all products.
     *
     * @return the all products
     */
    List<Product> getAllProducts();

    /**
     * Create product product.
     *
     * @param createProductRequestDTO the create product request dto
     * @return the product
     */
    Product createProduct(CreateProductRequestDTO createProductRequestDTO);

    /**
     * Gets all categories.
     *
     * @return the all categories
     */
    List<Category> getAllCategories();

    /**
     * Gets products in specific category.
     *
     * @param categoryName the category name
     * @return the products in specific category
     */
    List<Product> getProductsInSpecificCategory(String categoryName);

    /**
     * Update product product.
     *
     * @param productId               the product id
     * @param createProductRequestDTO the create product request dto
     * @return the product
     */
    Product updateProduct(Integer productId, CreateProductRequestDTO createProductRequestDTO);

    /**
     * Delete product.
     *
     * @param id the id
     */
    void deleteProduct(Integer id);

    /**
     * Gets paginated product.
     *
     * @param pageNo   the page no
     * @param pageSize the page size
     * @return the paginated product
     */
    Page<Product> getPaginatedProduct(Integer pageNo, Integer pageSize);
}
