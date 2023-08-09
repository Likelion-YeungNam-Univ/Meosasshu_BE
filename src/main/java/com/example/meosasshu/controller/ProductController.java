package com.example.meosasshu.controller;

import com.example.meosasshu.dto.response.OrderFormResDTO;
import com.example.meosasshu.dto.response.ProductDetailDTO;
import com.example.meosasshu.dto.response.ProductPagingDTO;
import com.example.meosasshu.dto.response.ReviewResDTO;
import com.example.meosasshu.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;
    @GetMapping
    public ResponseEntity<Page<ProductPagingDTO>> getAllProducts(
            @PageableDefault(sort = "id", size=10,direction = Sort.Direction.DESC) Pageable pageable){
        Page<ProductPagingDTO> productDtos = productService.getAllProducts(pageable);

        return ResponseEntity.ok(productDtos);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDetailDTO> getProductById(@PathVariable Long productId){
        ProductDetailDTO dto = productService.getProductById(productId);
        return ResponseEntity.ok(dto);
    }

    @Secured("ROLE_USER")
    @GetMapping("/{productId}/order")
    public ResponseEntity<OrderFormResDTO> getOrderForm(@PathVariable Long productId, @RequestParam Long quantity){
        OrderFormResDTO dto = productService.getOrderForm(productId,quantity);
        return ResponseEntity.ok(dto);
    }

//    @GetMapping("/{productId}/reviews")
//    public ResponseEntity<Page<ReviewResDTO>> getProductReviews(@PathVariable Long productId,
//                @PageableDefault(sort = "id", size=10,direction = Sort.Direction.DESC) Pageable pageable){
//
//        Page<ReviewResDTO> dto = productService.getProductReviews(pageable,productId);
//        return ResponseEntity.ok(dto);
//    }

    @GetMapping("/top-sellers")
    public ResponseEntity<Page<ProductPagingDTO>> getTopSellingProducts(
            @PageableDefault(sort = "salesCount", size = 10, direction = Sort.Direction.DESC) Pageable pageable) {
        Page<ProductPagingDTO> dto = productService.getTopSellingProducts(pageable);
        return ResponseEntity.ok(dto);
    }

}
