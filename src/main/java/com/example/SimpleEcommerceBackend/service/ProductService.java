package com.example.SimpleEcommerceBackend.service;

import com.example.SimpleEcommerceBackend.model.Product;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ProductService {

    List<Product> products = Arrays.asList(
            new Product(101, "Pen", 10),
            new Product(102, "Box", 50)
    );

    public List<Product> getAllProducts() {
        return products;
    }

}
