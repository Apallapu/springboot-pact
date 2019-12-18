package com.ankamma;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.DslPart;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.PactSpecVersion;
import au.com.dius.pact.model.RequestResponsePact;
import com.ankamma.client.ProductClient;
import com.ankamma.model.Product;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductConsumerTest {

    @Rule
    public PactProviderRuleMk2 stubProvider = new PactProviderRuleMk2("product_provider",PactSpecVersion.V3, this);
    @Autowired
    private ProductClient productClient;


    @Pact(state = "create-product", provider = "product_provider", consumer = "product_client")
    public RequestResponsePact callCreateProduct(PactDslWithProvider builder) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);

        PactDslJsonBody  product=new PactDslJsonBody().stringValue("productName", "TV")
                .stringType("productPrice", "12345.0")
                .integerType("id", 100);


        return builder.given("create-product").uponReceiving("test-create-product")
                .path("/api/product")
                .body(product)
                .method("POST")
                .willRespondWith()
                .headers(headers)
                .status(200).body(product).toPact();
    }
    @Test
    @PactVerification(fragment = "callCreateProduct")
    public void verifyCreateProductPact() {
        Product product = productClient.createProduct(new Product(1L, "TV","1330.0"));
        Assert.assertNotNull(product);
        Assert.assertNotNull(product.getId());
    }

    @Pact(state = "findBy-ProductId", provider = "product_provider", consumer = "product_client")
    public RequestResponsePact callGetProduct(PactDslWithProvider builder) {
        DslPart body = new PactDslJsonBody().integerType("id",1).stringType("produtName","TV").stringType("productPrice","1234.0");
        return builder.given("findBy-ProductId").uponReceiving("test-findBy-ProductId")
                .path("/api/product/").query("1").method("GET").willRespondWith().status(200).body(body).toPact();

    }

    @Test
    @PactVerification(fragment = "callGetProduct")
    public void verifyGetProductPact() {
        Product product = productClient.getAllProductById(1);
        Assert.assertNotNull(product);
        Assert.assertNotNull(product.getId());
    }

    @Pact(state = "getall-product", provider = "product_provider", consumer = "product_client")
    public RequestResponsePact callGetAllProduct(PactDslWithProvider builder) {
        DslPart body = new PactDslJsonBody().integerType("id",1).stringType("produtName","TV").stringType("productPrice","1234.0");
        return builder.given("getall-product").uponReceiving("test-getall-product")
                .path("/api/product/").method("GET").willRespondWith().status(200).body(body).toPact();

    }

    @Test
    @PactVerification(fragment = "callGetAllProduct")
    public void verifyGetAllProductPact() {
        List<Product> product = productClient.getAllProduct();
        Assert.assertNotNull(product);
        Assert.assertNotNull(product.get(0).getId());
    }


}
