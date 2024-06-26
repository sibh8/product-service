package com.example.productservice.service;

import com.example.productservice.dto.CreateProductRequestDTO;
import com.example.productservice.dto.FakeStoreCategoryDTO;
import com.example.productservice.dto.FakeStoreProductDTO;
import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Fake store service.
 */
@Service("fakestoreproductservice")
public class FakeStoreService implements ProductService {

    private final RestTemplate restTemplate;

    /**
     * Instantiates a new Fake store service.
     *
     * @param restTemplate the rest template
     */
    public FakeStoreService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Product getProductById(Integer id) {
        ResponseEntity<FakeStoreProductDTO> responseEntity = restTemplate.getForEntity("https://fakestoreapi.com/products/" + id, FakeStoreProductDTO.class);
        var fakeStoreProductResponseDTO = responseEntity.getBody();

        if (fakeStoreProductResponseDTO == null)
            return null;

        return fakeStoreProductResponseDTO.toProduct();
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        FakeStoreProductDTO[] fakeStoreProductsDTO = restTemplate.getForObject("https://fakestoreapi.com/products", FakeStoreProductDTO[].class);

        for (FakeStoreProductDTO fakeStoreProductDTO : fakeStoreProductsDTO) {
            var product = Product.builder()
                    .imageURL(fakeStoreProductDTO.getImage())
                    .title(fakeStoreProductDTO.getTitle())
                    .price(fakeStoreProductDTO.getPrice())
                    .description(fakeStoreProductDTO.getDescription())
                    .category(Category.builder()
                            .name(fakeStoreProductDTO.getCategory())
                            .build())
                    .id(fakeStoreProductDTO.getId())
                    .build();

            products.add(product);
        }

        return products;
    }

    @Override
    public Product createProduct(CreateProductRequestDTO createProductRequestDTO) {
        var fakeStoreProductDTO = FakeStoreProductDTO.builder()
                .image(createProductRequestDTO.getImage())
                .title(createProductRequestDTO.getTitle())
                .price(createProductRequestDTO.getPrice())
                .description(createProductRequestDTO.getDescription())
                .category(createProductRequestDTO.getCategory())
                .build();

        ResponseEntity<FakeStoreProductDTO> responseEntity = restTemplate.postForEntity("https://fakestoreapi.com/products", fakeStoreProductDTO, FakeStoreProductDTO.class);

        return responseEntity.getBody().toProduct();
    }

    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        FakeStoreCategoryDTO[] fakeStoresCategoryDTO = restTemplate.getForObject("https://fakestoreapi.com/products/categories", FakeStoreCategoryDTO[].class);
        for (FakeStoreCategoryDTO fakeStoreCategoryDTO : fakeStoresCategoryDTO) {
            categories.add(Category.builder().name(fakeStoreCategoryDTO.getCategory()).build());
        }
        return categories;
    }

    @Override
    public List<Product> getProductsInSpecificCategory(String categoryName) {
        List<Product> products = new ArrayList<>();
        FakeStoreProductDTO[] fakeStoreProductsDTO = restTemplate.getForObject("https://fakestoreapi.com/products/category/" + categoryName, FakeStoreProductDTO[].class);

        for (FakeStoreProductDTO fakeStoreProductDTO : fakeStoreProductsDTO) {
            var product = Product.builder()
                    .id(fakeStoreProductDTO.getId())
                    .imageURL(fakeStoreProductDTO.getImage())
                    .title(fakeStoreProductDTO.getTitle())
                    .price(fakeStoreProductDTO.getPrice())
                    .description(fakeStoreProductDTO.getDescription())
                    .category(Category.builder()
                            .name(fakeStoreProductDTO.getCategory())
                            .build())
                    .build();

            products.add(product);
        }

        return products;
    }

    @Override
    public Product updateProduct(Integer productId, CreateProductRequestDTO createProductRequestDTO) {
        FakeStoreProductDTO fakeStoreProductDTO = restTemplate.patchForObject("https://fakestoreapi.com/products/" + productId, createProductRequestDTO, FakeStoreProductDTO.class);

        var product = Product.builder()
                .id(fakeStoreProductDTO.getId())
                .category(Category.builder()
                        .name(fakeStoreProductDTO.getCategory())
                        .build())
                .description(fakeStoreProductDTO.getDescription())
                .price(fakeStoreProductDTO.getPrice())
                .title(fakeStoreProductDTO.getTitle())
                .imageURL(fakeStoreProductDTO.getImage())
                .build();

        return product;
    }

    @Override
    public void deleteProduct(Integer id) {

    }

    @Override
    public Page<Product> getPaginatedProduct(Integer pageNo, Integer pageSize) {
        return null;
    }
}
