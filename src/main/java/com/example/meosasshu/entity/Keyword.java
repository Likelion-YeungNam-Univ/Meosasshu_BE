package com.example.meosasshu.entity;

import jakarta.persistence.*;
/**
 * 리뷰에 사용하는 키워드
 * */
@Entity
public class Keyword {
    @Id
    @Column(name = "keyword")
    private String keyword;

}
