package com.example.productservice.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * The type Application configuration.
 */
@Configuration
public class ApplicationConfiguration {
    /**
     * Create rest template rest template.
     *
     * @return the rest template
     */
    @Bean
    public RestTemplate createRestTemplate() {
        return new RestTemplate();
    }
}
