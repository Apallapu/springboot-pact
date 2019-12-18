package com.ankamma.model;

import java.io.Serializable;


public class Product implements Serializable {
    public Long getId() {
        return id;
    }

    public Product(Long id, String produtName, String productPrice) {
        this.id = id;
        this.produtName = produtName;
        this.productPrice = productPrice;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProdutName() {
        return produtName;
    }

    public void setProdutName(String produtName) {
        this.produtName = produtName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }


    private Long id;

    private String produtName;

    private String productPrice;

}
