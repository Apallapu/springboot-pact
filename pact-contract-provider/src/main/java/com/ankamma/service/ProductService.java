package com.ankamma.service;

import com.ankamma.exception.ProductNotFoundException;
import com.ankamma.model.Product;
import com.ankamma.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public Product save(Product product) {

        return productRepository.save(product);
    }

    public Iterable<Product> all() {

        return productRepository.findAll();
    }

    public Product findByProductId(Integer productId) {

        return productRepository.findProductById(productId).orElseThrow(
                () -> new ProductNotFoundException(HttpStatus.NOT_FOUND.toString(), "Products are Not Found"));
    }
}
