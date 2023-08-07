package com.example.meosasshu.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Product extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    private String brand;
    private String name;
    private double price;

    private Long thumbnail;
    private Long descriptionImage;

    private Long stockQuantity;
    private String description;
    private String shortDescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="category_id")
    private Category category;

}
