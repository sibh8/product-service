package com.example.productservice.repository;

import com.example.productservice.models.Product;
import com.example.productservice.repository.projection.ProductIdAndTitleProjection;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

    /**
     * Find by title product.
     *
     * @param title the title
     * @return the product
     */
    Product findByTitle(String title);

    /**
     * Gets all products by title and id.
     *
     * @param id    the id
     * @param title the title
     * @return the all products by title and id
     */
    @Query("select p from Product p where p.id=:id and p.title=:title")
    Product getAllProductsByTitleAndId(@Param("id") Integer id, @Param("title") String title);

    // Get title and ID by price

    /**
     * Gets title and id by price.
     *
     * @param price the price
     * @return the title and id by price
     */
    @Query ("select p.id as id, p.title as title from Product p where p.price = :price")
    public List<ProductIdAndTitleProjection> getTitleAndIdByPrice(@Param("price") Double price);

    /**
     * Gets products by price.
     *
     * @param price the price
     * @return the products by price
     */
    @Query ("select p from Product p where p.price = :price")
    public List<Product> getProductsByPrice(@Param("price") Double price);

    /**
     * Retrive id and title from native list.
     *
     * @param price the price
     * @return the list
     */
    @Query(value = "select id, title from product where price=:price",
    nativeQuery = true)
    List<ProductIdAndTitleProjection> retriveIdAndTitleFromNative(@Param("price") Double price);

    /**
     * Gets all product tiles for category.
     *
     * @param id the id
     * @return the all product tiles for category
     */
    @Query("select p.title from Product p where p.category.id = :id")
    List<String> getAllProductTilesForCategory(@Param("id") Integer id);



}
