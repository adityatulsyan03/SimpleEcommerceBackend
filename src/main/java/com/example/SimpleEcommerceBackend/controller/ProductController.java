package com.example.SimpleEcommerceBackend.controller;

import com.example.SimpleEcommerceBackend.model.Product;
import com.example.SimpleEcommerceBackend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/")
    public String greet() {
        return "Hello World";
    }

    @PostMapping(value = "/products")
    public ResponseEntity<String> addProduct(
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("brand") String brand,
            @RequestParam("price") BigDecimal price,
            @RequestParam("category") String category,
            @RequestParam("releaseDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date releaseDate,
            @RequestParam("available") boolean available,
            @RequestParam("quantity") int quantity,
            @RequestParam("image") MultipartFile imageFile
    ) {
        try {
            Product product = new Product();
            product.setName(name);
            product.setDescription(description);
            product.setBrand(brand);
            product.setPrice(price);
            product.setCategory(category);
            product.setReleaseDate(releaseDate);
            product.setAvailable(available);
            product.setQuantity(quantity);

            product.setImageName(imageFile.getOriginalFilename());
            product.setImageType(imageFile.getContentType());
            product.setImageData(imageFile.getBytes());

            service.addProduct(product);

            return ResponseEntity.ok("Product added successfully!");

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading product");
        }
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(service.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(
            @PathVariable int id
    ) {
        Product product = service.getProductById(id);

        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PutMapping("/products")
    public ResponseEntity<?> updateProduct(
            @RequestParam("id") int id,
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("brand") String brand,
            @RequestParam("price") BigDecimal price,
            @RequestParam("category") String category,
            @RequestParam("releaseDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date releaseDate,
            @RequestParam("available") boolean available,
            @RequestParam("quantity") int quantity,
            @RequestParam("image") MultipartFile imageFile
    ) {
        try {
            Product product = new Product();
            product.setId(id);
            product.setName(name);
            product.setDescription(description);
            product.setBrand(brand);
            product.setPrice(price);
            product.setCategory(category);
            product.setReleaseDate(releaseDate);
            product.setAvailable(available);
            product.setQuantity(quantity);

            product.setImageName(imageFile.getOriginalFilename());
            product.setImageType(imageFile.getContentType());
            product.setImageData(imageFile.getBytes());

            if (service.getProductById(product.getId()) == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            service.updateProduct(product);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading product");
        }
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<?> deleteProduct(
            @PathVariable int id
    ) {
        if (service.getProductById(id) ==null) {
            return new ResponseEntity<>("Product doesn't exist",HttpStatus.NOT_FOUND);
        }
        service.deleteProduct(id);
        return new ResponseEntity<>("Deleted",HttpStatus.OK);
    }


    @GetMapping("/product/search")
    public ResponseEntity<List<Product>> searchProduct(
            @RequestParam String name
    ){
        System.out.println("Searching with "+name);
        List<Product> product = service.searchProduct(name);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

}
