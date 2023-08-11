package com.example.meosasshu.controller;

import com.example.meosasshu.dto.request.ReviewCreationDTO;
import com.example.meosasshu.dto.response.ReviewResDTO;
import com.example.meosasshu.security.user.CurrentUser;
import com.example.meosasshu.security.user.UserDetailsImpl;
import com.example.meosasshu.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping("/products/{productId}/reviews")
    public ResponseEntity<Page<ReviewResDTO>> getProductReviews(@PathVariable Long productId,
                @PageableDefault(sort = "id", size=10,direction = Sort.Direction.DESC) Pageable pageable){
        Page<ReviewResDTO> dto = reviewService.getProductReviews(pageable,productId);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<ReviewResDTO> getReview(@PathVariable Long reviewId){
        ReviewResDTO dto = reviewService.getReviewById(reviewId);
        return ResponseEntity.ok(dto);
    }

    @Secured("ROLE_USER")
    @PostMapping("/products/{productId}/reviews")
    public ResponseEntity<Long> createReviewForProduct(
            @PathVariable Long productId,@CurrentUser UserDetailsImpl userDetails,
            @ModelAttribute ReviewCreationDTO reviewCreateDTO) throws IOException, NoSuchAlgorithmException {
        Long reviewId = reviewService.createReviewForProduct(productId, reviewCreateDTO, userDetails);
        return ResponseEntity.ok(reviewId);
    }

    @Secured("ROLE_USER")
    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<Long> updateReview(
            @PathVariable Long reviewId,@CurrentUser UserDetailsImpl userDetails,
            @ModelAttribute ReviewCreationDTO reviewUpdateDTO) throws IOException, NoSuchAlgorithmException {
        Long savedId = reviewService.updateReview(reviewId, reviewUpdateDTO,userDetails);
        return ResponseEntity.ok(savedId);
    }

    @Secured("ROLE_USER")
    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long reviewId, @CurrentUser UserDetailsImpl userDetails) {
        reviewService.deleteReview(reviewId,userDetails);
        return ResponseEntity.noContent().build();
    }

    @Secured("ROLE_USER")
    @GetMapping("/reviews")
    public ResponseEntity<Page<ReviewResDTO>> getReviewsByAuthorId(
            @CurrentUser UserDetailsImpl userDetails,
            @PageableDefault(sort = "id", size=10,direction = Sort.Direction.DESC) Pageable pageable) {
        Page<ReviewResDTO> dto = reviewService.getReviewsByAuthorId(pageable, userDetails.getAccount().getId());
        return ResponseEntity.ok(dto);
    }

}
