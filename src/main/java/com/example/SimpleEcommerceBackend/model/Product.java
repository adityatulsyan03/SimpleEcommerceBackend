package com.example.SimpleEcommerceBackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
public class Product {

    private int productId;
    private String productName;
    private int productPrice;

}
