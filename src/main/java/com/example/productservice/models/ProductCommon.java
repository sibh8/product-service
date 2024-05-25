package com.example.productservice.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

/**
 * The type Product common.
 */
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductCommon implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private Instant createdAt;
    private Instant updatedAt;
    private String createdByUserId;
}
