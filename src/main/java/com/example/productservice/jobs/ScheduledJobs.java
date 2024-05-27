package com.example.productservice.jobs;

import com.example.productservice.repository.ProductReepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * The type Scheduled jobs.
 */
@Component
public class ScheduledJobs {

    private ProductReepository productReepository;

    /**
     * Instantiates a new Scheduled jobs.
     *
     * @param productReepository the product reepository
     */
    public ScheduledJobs(ProductReepository productReepository) {
        this.productReepository = productReepository;
    }

    /**
     * Getproduct details.
     */
    @Scheduled(cron = "2 * * * * *")
    public void getproductDetails(){
        List<String> products = productReepository.getAllProductTilesForCategory(1);
        System.out.println(products.toString());
    }
}
