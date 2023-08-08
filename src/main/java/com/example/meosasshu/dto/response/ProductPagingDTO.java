package com.example.meosasshu.dto.response;

import com.example.meosasshu.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 상품 목록 페이지에서 보일 간단한 상품정보
 * */
@Getter @Setter
public class ProductPagingDTO {
    private Long id;
    private String brand;
    private String productName;
    private String thumbnailUrl;
    private double price;


    public static ProductPagingDTO createDto(Product product) {
        ProductPagingDTO dto = new ProductPagingDTO();
        dto.setId(product.getId());
        dto.setProductName(product.getName());
        dto.setBrand(product.getBrand());
        dto.setThumbnailUrl(product.getThumbnail());
        dto.setPrice(product.getPrice());

        return dto;
    }
}
