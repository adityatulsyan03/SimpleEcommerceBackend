package com.example.SimpleEcommerceBackend.controller;


import com.example.SimpleEcommerceBackend.model.Product;
import com.example.SimpleEcommerceBackend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping("/products")
    public List<Product> getProduct(){
        return productService.getAllProducts();
    }

}
