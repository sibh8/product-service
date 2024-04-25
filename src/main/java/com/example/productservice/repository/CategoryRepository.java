package com.example.productservice.repository;

import com.example.productservice.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * The interface Category repository.
 */
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    List<Category> findAll();

    Category findByName(String name);
}
