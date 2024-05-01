package com.example.productservice;

import com.example.productservice.repository.CategoryRepository;
import com.example.productservice.repository.ProductReepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * The type Product service application tests.
 */
@SpringBootTest
class ProductServiceApplicationTests {
    @Autowired
    private ProductReepository productReepository;

    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * Context loads.
     */
    @Test
    void contextLoads() {
    }

    @Test
    public void testMyDbProductsByTitleAndId(){
        var product = productReepository.getAllProductsByTitleAndId(54, "Samsung");
        System.out.println(product);
    }


    @Test
    public void testMyDbTitleAndIdByPrice(){
        var product = productReepository.getTitleAndIdByPrice(143.5);
        System.out.println("Size: "+product.size());
        product.forEach(p -> {
            System.out.println("Title: "+p.getTitle()+"     ID: "+p.getId());
        });
    }

    @Test
    public void testGetProductsByPrice(){
        var product = productReepository.getProductsByPrice(143.5);
        System.out.println("Size: "+product.size());
        product.forEach(p -> {
            System.out.println("Title: "+p.getTitle()+"     ID: "+p.getId());
        });
    }

    @Test
    public void testNativeSQLQuery(){
        var product = productReepository.retriveIdAndTitleFromNative(143.5);
        System.out.println("Size: "+product.size());
        product.forEach(p -> {
            System.out.println("Title: "+p.getTitle()+"     ID: "+p.getId());
        });
    }

    @Test
    public void testProductsFromCategory(){
        var category = categoryRepository.findById(1);
        System.out.println("Category name: "+category.get().getName());

        var products = category.get().getProducts();

        products.forEach(p -> {
            System.out.println(p. getTitle());
        });
    }
}
