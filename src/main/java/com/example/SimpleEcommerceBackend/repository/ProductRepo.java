package com.example.SimpleEcommerceBackend.repository;

import com.example.SimpleEcommerceBackend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {

    @Query("""
        SELECT new com.example.SimpleEcommerceBackend.model.Product(
            p.id, p.name, p.description, p.brand, p.price, p.category,
            p.releaseDate, p.available, p.quantity, p.imageName, p.imageType, null
        )
        FROM Product p
        WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%'))
           OR LOWER(p.brand) LIKE LOWER(CONCAT('%', :keyword, '%'))
           OR LOWER(p.description) LIKE LOWER(CONCAT('%', :keyword, '%'))
           OR LOWER(p.category) LIKE LOWER(CONCAT('%', :keyword, '%'))
    """)
    List<Product> searchProduct(String keyword);

}
