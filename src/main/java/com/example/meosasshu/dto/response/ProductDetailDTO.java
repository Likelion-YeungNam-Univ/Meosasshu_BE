package com.example.meosasshu.dto.response;

import com.example.meosasshu.entity.Product;
import lombok.Getter;
import lombok.Setter;

/**
 * 단일 상품 페이지에서 보여줄 자세한 상품정보
 * */
@Getter @Setter
public class ProductDetailDTO {
        private Long id;
        private String brand;
        private String name;
        private double price;
        private long stockQuantity;
        private String thumbnailUrl; // 대표 이미지의 URL
        private String description;

        private String descriptionImageUrl; //상품 설명 이미지의 URL

        private String shortDescription; //gpt를 이용해 요약한 내용
        private String category; // 카테고리 이름


    public static ProductDetailDTO createDto(Product product) {
            ProductDetailDTO dto = new ProductDetailDTO();
            dto.setId(product.getId());
            dto.setName(product.getName());
            dto.setCategory(product.getCategory().getName());
            dto.setBrand(product.getBrand());
            dto.setPrice(product.getPrice());
            dto.setDescription(product.getDescription());

            /**
             * 원래는 상품 등록시에 GPT요약을 하려고했는데...
             * 상품등록을 하지않으니 조회할때마다 하는걸로?.. 일단은 GPT 적용하지 않음
             * */
            dto.setShortDescription(product.getShortDescription());

            dto.setStockQuantity(product.getStockQuantity());
            dto.setThumbnailUrl(product.getThumbnail());
            dto.setDescriptionImageUrl(product.getDescriptionImage());

            return dto;
    }
}
