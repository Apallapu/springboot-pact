package com.ankamma.client;

import com.ankamma.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@Component
public class ProductClient {

    @Autowired
    RestTemplate restTemplate;
    @Value("${productserviceUrl}")
    private String productserviceUrl;

    public Product createProduct(Product product) {

            StringBuilder url = new StringBuilder();
            url.append(productserviceUrl).append("/api/product");

            return restTemplate.postForObject(url.toString(), product, Product.class);

    }

    public List<Product> getAllProduct() {

        StringBuilder url = new StringBuilder();
        url.append(productserviceUrl).append("/api/all");
        return restTemplate.getForObject(url.toString(), List.class);

    }

    public Product getAllProductById(Integer productId) {

        StringBuilder url = new StringBuilder();
        url.append(productserviceUrl).append("/api/product/"+productId);
        return restTemplate.getForObject(url.toString(), Product.class);

    }


}
