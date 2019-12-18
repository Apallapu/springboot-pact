package com.ankamma.controller;

import com.ankamma.client.ProductClient;
import com.ankamma.model.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    ProductClient productClient;

    public ProductController(ProductClient productClient) {
        super();
        this.productClient = productClient;
    }

    @PostMapping(value = "/product")
    public Product save(@RequestBody Product product) {
        return productClient.createProduct(product);
    }

    @GetMapping(value = "/product")
    public Iterable<Product> all() {
        return productClient.getAllProduct();
    }

    @GetMapping(value = "/product/{productId}")
    public Product findByProductId(@PathVariable Integer productId) {
        return productClient.getAllProductById(productId);
    }
}
