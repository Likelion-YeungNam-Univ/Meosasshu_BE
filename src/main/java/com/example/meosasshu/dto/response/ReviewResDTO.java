package com.example.meosasshu.dto.response;

import com.example.meosasshu.entity.Keyword;
import com.example.meosasshu.entity.Review;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
/**
 * 리뷰페이지에 보여줄 데이터
 * */
@Getter @Setter
public class ReviewResDTO {
    private Long id;
    private String authorName; // 리뷰 작성자의 이름
    private String comment;
    private List<String> selectedKeywords; // 선택된 키워드 목록
    private String imageUrl; // 등록한 이미지 URL

}

