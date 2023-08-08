package com.example.meosasshu.entity;

import jakarta.persistence.*;
import lombok.Getter;

/**
 * 리뷰에 사용하는 키워드
 * */
@Entity @Getter
public class Keyword {
    @Id
    @Column(name = "keyword_name")
    private String keywordName;

}
