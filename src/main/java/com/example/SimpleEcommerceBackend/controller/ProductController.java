package com.example.SimpleEcommerceBackend.controller;

import com.example.SimpleEcommerceBackend.model.Product;
import com.example.SimpleEcommerceBackend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public List<Product> getProduct() {
        return productService.getAllProducts();
    }

    @GetMapping("/products/{id}")
    public Product getProductById(
            @PathVariable int id
    ) {
        return productService.getProductById(id);
    }

    @PostMapping("/products")
    public void addProduct(
            @RequestBody Product product
    ) {
        System.out.println(product);
        productService.AddProduct(product);
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(
            @PathVariable int id
    ) {
        System.out.println("Product with id: "+id+" deleted.");
        productService.DeleteProduct(id);
    }

    @PutMapping("/products")
    public void updateProduct(
            @RequestBody Product product
    ){
        productService.UpdateProduct(product);
    }
}
