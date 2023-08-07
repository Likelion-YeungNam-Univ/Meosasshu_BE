package com.example.meosasshu.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Review extends BaseEntity{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="review_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="account_id")
    private Account author;

    private Long image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product_id")
    private Product product;

//    @NotBlank
//    private Double rating;
    @NotBlank
    private String comment;

    @ManyToMany
    @JoinTable(
            name = "review_keyword",
            joinColumns = {@JoinColumn(name = "review_id", referencedColumnName = "review_id")},
            inverseJoinColumns = {@JoinColumn(name = "keyword", referencedColumnName = "keyword")})
    private List<Keyword> selectedKeywords = new ArrayList<>();

}
