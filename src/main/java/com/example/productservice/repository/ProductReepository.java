package com.example.productservice.repository;

import com.example.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * The interface Product reepository.
 */
public interface ProductReepository extends JpaRepository<Product, Integer> {
    /**
     * Find all by id product.
     *
     * @param id the id
     * @return the product
     */
    Product findAllById(Integer id);

    List<Product> findAll();

    /**
     * Find all by category name list.
     *
     * @param categoryName the category name
     * @return the list
     */
    List<Product> findAllByCategory_Name(String categoryName);

    Product findByTitle(String title);
}
