package com.ankamma;

import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactBroker;
import au.com.dius.pact.provider.junit.target.HttpTarget;
import au.com.dius.pact.provider.junit.target.Target;
import au.com.dius.pact.provider.junit.target.TestTarget;
import au.com.dius.pact.provider.spring.SpringRestPactRunner;
import com.ankamma.model.Product;
import com.ankamma.service.ProductService;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@RunWith(SpringRestPactRunner.class)
@SpringBootTest(classes = Application.class, properties = {"spring.profiles.active=test", "spring.cloud.config.enabled=false"}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
@PactBroker(host = "${pact.host}", port = "${pact.port}")
@Provider("product_provider")
public class ProductProviderTest {

    @MockBean
    ProductService productService;

    @TestTarget
    public final Target target = new HttpTarget(9050);


    @State(value = "create-product")
    public void createProduct() throws Exception {

        Product product = new Product(1, "TV", "100");
        when(productService.save(any(Product.class))).thenReturn(product);
    }


    @State(value = "getall-product")
    public void all() throws Exception {

        Product product = new Product(1, "TV", "100");
        List<Product> products = new ArrayList<>();
        products.add(product);
        when(productService.all()).thenReturn(products);
    }

    @State(value = " findBy-ProductId")
    public void findByProductId() throws Exception {

        Product product = new Product(1, "TV", "100");
        Integer productId = 1;

        when(productService.findByProductId(productId)).thenReturn(product);
    }
}