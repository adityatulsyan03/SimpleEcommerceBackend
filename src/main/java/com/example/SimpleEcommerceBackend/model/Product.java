package com.example.SimpleEcommerceBackend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private String brand;
    private BigDecimal price;
    private String category;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date releaseDate;
    private boolean available;
    private int quantity;

    private String imageName;
    private String imageType;
    @Lob //To say it is a Large Object
    private byte[] imageData;

    public Product(Integer id, String name, String description, String brand, BigDecimal price,
                   String category, Date releaseDate, boolean available, int quantity,
                   String imageName, String imageType) {
        this(id, name, description, brand, price, category, releaseDate, available, quantity, imageName, imageType, null);
    }


}
