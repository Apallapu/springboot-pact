package com.ankamma.controller;

import com.ankamma.model.Product;
import com.ankamma.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    com.ankamma.service.ProductService ProductService;

    public ProductController(ProductService ProductService) {
        super();
        this.ProductService = ProductService;
    }
    @PostMapping(value = "/product")
    public Product save(@RequestBody Product product) {
        return ProductService.save(product);
    }

    @GetMapping(value = "/product")
    public Iterable<Product> all() {
        return ProductService.all();
    }

    @GetMapping(value = "/product/{productId}")
    public Product findByProductId(@PathVariable Integer productId) {
        return ProductService.findByProductId(productId);
    }
}
