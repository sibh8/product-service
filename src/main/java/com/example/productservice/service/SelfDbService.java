package com.example.productservice.service;

import com.example.productservice.dto.CreateProductRequestDTO;
import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import com.example.productservice.repository.CategoryRepository;
import com.example.productservice.repository.ProductReepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

/**
 * The type Self db service.
 */
@Service("selfdbproductservice")
public class SelfDbService implements ProductService {

    private final ProductReepository productReepository;
    private final CategoryRepository categoryRepository;

    /**
     * Instantiates a new Self db service.
     *
     * @param productReepository the product reepository
     * @param categoryRepository the category repository
     */
    public SelfDbService(ProductReepository productReepository, CategoryRepository categoryRepository) {
        this.productReepository = productReepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product getProductById(Integer id) {
        return productReepository.findAllById(id);
    }

    @Override
    public List<Product> getAllProducts() {
        return productReepository.findAll();
    }

    @Override
    public Product createProduct(CreateProductRequestDTO createProductRequestDTO) {
        // Check if the Product exists
        var product = productReepository.findByTitle(createProductRequestDTO.getTitle());
        if (product == null) {
            product = createProductRequestDTO.toProduct();
            product.setCreatedAt(Instant.now());
        } else {
            product.setUpdatedAt(Instant.now());
        }

        // Check if the category exists in database if exists, don't create a new entry.
        var category = categoryRepository.findByName(createProductRequestDTO.getCategory());
        if (category == null) {
            category = Category.builder()
                    .name(createProductRequestDTO.getCategory())
                    .createdAt(Instant.now())
                    .name(createProductRequestDTO.getCategory())
                    .build();
        } else {
            category.setUpdatedAt(Instant.now());
        }
        product.setCategory(category);

        return productReepository.save(product);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Product> getProductsInSpecificCategory(String categoryName) {
        return productReepository.findAllByCategory_Name(categoryName);
    }

    @Override
    public Product updateProduct(Integer productId, CreateProductRequestDTO createProductRequestDTO) {
        var product = createProductRequestDTO.toProduct();
        if (productReepository.findAllById(productId) == null)
            return null;

        var category = categoryRepository.findByName(createProductRequestDTO.getCategory());
        product.setUpdatedAt(Instant.now());
        product.setCategory(Category.builder()
                .updatedAt(Instant.now())
                .build());
        return productReepository.save(product);
    }

    @Override
    public void deleteProduct(Integer id) {
        productReepository.deleteById(id);
    }

    @Override
    public Page<Product> getPaginatedProduct(Integer pageNo, Integer pageSize) {
        Pageable pageAble = PageRequest.of(pageNo, pageSize);

        Page<Product> productPage = productReepository.findAll(pageAble);

        return productPage;
    }
}
