package com.example.productservice.repository.projection;

/**
 * The interface Product id and title projection.
 */
public interface ProductIdAndTitleProjection {
    /**
     * Gets id.
     *
     * @return the id
     */
    Integer getId();

    /**
     * Gets title.
     *
     * @return the title
     */
    String getTitle();

    /**
     * Gets description.
     *
     * @return the description
     */
    String getDescription();
}
