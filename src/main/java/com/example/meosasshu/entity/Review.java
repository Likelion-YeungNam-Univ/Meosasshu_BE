package com.example.meosasshu.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity @Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review extends BaseEntity{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="review_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="account_id")
    private Account author;

    @Column(name="file_id")
    private Long fileId;

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
            inverseJoinColumns = {@JoinColumn(name = "keyword_name", referencedColumnName = "keyword_name")})
    private List<Keyword> selectedKeywords = new ArrayList<>();





    public Review(String comment, Long fileId, Account account, Product product, List<Keyword> selectedKeywords) {
        super();
        this.author = account;
        this.comment = comment;
        this.product = product;
        this.selectedKeywords = selectedKeywords;
        this.fileId = fileId;
    }

    public void update(Account author, String comment, Long fileId, List<Keyword> selectedKeywords) {
        this.author = author;
        this.comment = comment;
        this.fileId=fileId;
        this.selectedKeywords = selectedKeywords;

    }
}
