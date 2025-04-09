package com.example.SimpleEcommerceBackend.service;

import com.example.SimpleEcommerceBackend.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ProductService {

    List<Product> products = new ArrayList<>(Arrays.asList(
            new Product(101, "Pen", 10),
            new Product(102, "Box", 50)
    ));

    public List<Product> getAllProducts() {
        return products;
    }

    public Product getProductById(int id) {
        return products.stream()
                .filter(p-> p.getProductId()==id)
                .findFirst()
                .orElse(new Product(0,"No Product Found",0));
    }

    public void AddProduct(Product product) {
        products.add(product);
    }

    public void DeleteProduct(int id) {
        products.removeIf(p -> p.getProductId()==id);
    }

    public void UpdateProduct(Product product) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProductId()==product.getProductId()) {
                products.set(i, product);
            }
        }
    }

}
