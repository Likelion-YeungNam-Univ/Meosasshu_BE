package com.example.meosasshu.dto.request;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
/**
 * 리뷰 작성 요청받는 DTO
 * */
public class ReviewCreationDTO {
    private Long productId;
    private String comment;
    private MultipartFile image; // 단일 이미지 파일

    private List<String> keywords;

}
