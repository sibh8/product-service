package com.example.productservice.service;

import com.example.productservice.dto.CreateProductRequestDTO;
import com.example.productservice.dto.FakeStoreCategoryDTO;
import com.example.productservice.dto.FakeStoreProductDTO;
import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreService implements ProductService {

    private RestTemplate restTemplate;

    public FakeStoreService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Product getProductById(Integer id) {
        ResponseEntity<FakeStoreProductDTO> responseEntity = restTemplate.getForEntity("https://fakestoreapi.com/products/" + id, FakeStoreProductDTO.class);
        var fakeStoreProductResponseDTO = responseEntity.getBody();
        return fakeStoreProductResponseDTO.toProduct();
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        FakeStoreProductDTO[] fakeStoreProductsDTO = restTemplate.getForObject("https://fakestoreapi.com/products", FakeStoreProductDTO[].class);

        for (FakeStoreProductDTO fakeStoreProductDTO : fakeStoreProductsDTO) {
            products.add(Product.builder()
                    .id(fakeStoreProductDTO.getId())
                    .imageURL(fakeStoreProductDTO.getImage())
                    .title(fakeStoreProductDTO.getTitle())
                    .price(fakeStoreProductDTO.getPrice())
                    .description(fakeStoreProductDTO.getDescription())
                    .category(Category.builder()
                            .name(fakeStoreProductDTO.getCategory())
                            .build())
                    .build());
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
            products.add(Product.builder()
                    .id(fakeStoreProductDTO.getId())
                    .imageURL(fakeStoreProductDTO.getImage())
                    .title(fakeStoreProductDTO.getTitle())
                    .price(fakeStoreProductDTO.getPrice())
                    .description(fakeStoreProductDTO.getDescription())
                    .build());
        }

        return products;
    }
}
