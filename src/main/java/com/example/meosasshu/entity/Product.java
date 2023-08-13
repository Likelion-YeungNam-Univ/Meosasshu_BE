package com.example.meosasshu.entity;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
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

    /**
     * 판매자 기능 구현하지 않기때문에 seller정보를 저장하지 않고, 이미지도 url로 저장
     * */
    private String thumbnail;
    private String descriptionImage;

    private Long stockQuantity;

    @Column(length = 500)
    private String description;
    private String shortDescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="category_id")
    private Category category;

    private Long salesCount;

    /**
     * 비즈니스 로직
     * */
    @Transactional
    public void order(Long quantity) {
        checkSufficientStock(quantity);
        this.stockQuantity-=quantity;
        this.salesCount+=quantity;
    }

    public void checkSufficientStock(Long quantity) {
        if(this.getStockQuantity()<quantity)
        {
            throw new RuntimeException("재고가 부족합니다.");
        }
    }

    public void cancel(long quantity) {
        this.stockQuantity+=quantity;
        this.salesCount-=quantity;
    }
}
