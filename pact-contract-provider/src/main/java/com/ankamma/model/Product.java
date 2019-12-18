package com.ankamma.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "product")
public class Product implements Serializable {
    public Integer getId() {
        return id;
    }

    public Product(Integer id, String produtName, String productPrice) {
        this.id = id;
        this.produtName = produtName;
        this.productPrice = productPrice;
    }

    public void setId(Integer id) {
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String produtName;

    private String productPrice;

}
