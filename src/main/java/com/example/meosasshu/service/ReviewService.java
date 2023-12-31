package com.example.meosasshu.service;

import com.example.meosasshu.dto.request.ReviewCreationDTO;
import com.example.meosasshu.dto.response.ReviewResDTO;
import com.example.meosasshu.entity.Keyword;
import com.example.meosasshu.entity.Product;
import com.example.meosasshu.entity.Review;
import com.example.meosasshu.exception.KeywordNotFoundException;
import com.example.meosasshu.exception.PermissionDeniedException;
import com.example.meosasshu.exception.ProductNotFoundException;
import com.example.meosasshu.common.file.File;
import com.example.meosasshu.common.file.FileDto;
import com.example.meosasshu.common.file.FileRepository;
import com.example.meosasshu.common.file.FileService;
import com.example.meosasshu.exception.ReviewNotFoundException;
import com.example.meosasshu.repository.KeywordRepository;
import com.example.meosasshu.repository.ProductRepository;
import com.example.meosasshu.repository.ReviewRepository;
import com.example.meosasshu.common.security.user.UserDetailsImpl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final FileService fileService;
    private final FileRepository fileRepository;
    private final ProductRepository productRepository;
    private final KeywordRepository keywordRepository;

    public Page<ReviewResDTO> getProductReviews(Pageable pageable, Long productId) {
        return reviewRepository.findAllByProductId(productId, pageable).map(this::convertToDto);
    }

    private ReviewResDTO convertToDto(Review review) {
        ReviewResDTO dto = new ReviewResDTO();
        dto.setId(review.getId());
        dto.setAuthorName(review.getAuthor().getName());
        dto.setComment(review.getComment());

        List<String> selectedKeywords = new ArrayList<>();
        for(Keyword keyword:review.getSelectedKeywords()){
            selectedKeywords.add(keyword.getKeywordName());
        }
        dto.setSelectedKeywords(selectedKeywords);

        // Fetch the file using fileRepository


        Long fileId = review.getFileId();
        if (fileId != null) {
            File file = fileRepository.findById(fileId).orElse(null);
            if (file != null) {
                String filePath = fileService.getFilePathFromFile(file);
                dto.setImageUrl(filePath);
            }
        } else {
            dto.setImageUrl(null);
        }


        return dto;
    }

    public Long createReviewForProduct(Long productId, ReviewCreationDTO reviewCreateDTO, UserDetailsImpl userDetails) throws IOException, NoSuchAlgorithmException {
        FileDto fileDto = null;
        if(reviewCreateDTO.getFile()!=null){
            fileDto = fileService.uploadFile(reviewCreateDTO.getFile());
        }

        Product product = productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);

        List<Keyword> selectedKeywords = new ArrayList<>();
        convertStringListToKeywordList(reviewCreateDTO.getKeywords(), selectedKeywords);

        Review review;
        if(fileDto!=null){
            review = new Review(reviewCreateDTO.getComment(),fileDto.getId(),userDetails.getAccount(),product,selectedKeywords);
        }else{
            review = new Review(reviewCreateDTO.getComment(),null,userDetails.getAccount(),product,selectedKeywords);
        }

        Review saved = reviewRepository.save(review);
        return saved.getId();
    }

    public Long updateReview(Long reviewId, ReviewCreationDTO reviewUpdateDTO, UserDetailsImpl userDetails) throws IOException, NoSuchAlgorithmException {
        Review review = reviewRepository.findById(reviewId).orElseThrow(ReviewNotFoundException::new);

        verifyReviewAuthor(userDetails, review);

        List<Keyword> selectedKeywords = new ArrayList<>();
        convertStringListToKeywordList(reviewUpdateDTO.getKeywords(), selectedKeywords);

        FileDto fileDto = null;
        if(reviewUpdateDTO.getFile()!=null){
            fileDto = fileService.uploadFile(reviewUpdateDTO.getFile());
        }

        review.update(userDetails.getAccount(),reviewUpdateDTO.getComment(),(fileDto!=null)?fileDto.getId():null,selectedKeywords);
        return review.getId();
    }

    private void convertStringListToKeywordList(List<String> stringList, List<Keyword> selectedKeywords) {
        if(stringList==null) return;
        for(String keyword: stringList){
            Keyword entity = keywordRepository.findById(keyword).orElseThrow(KeywordNotFoundException::new);
            selectedKeywords.add(entity);
        }
    }

    public void deleteReview(Long reviewId, UserDetailsImpl userDetails) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(ReviewNotFoundException::new);
        verifyReviewAuthor(userDetails, review);
        
        reviewRepository.delete(review);
    }
    private static void verifyReviewAuthor(UserDetailsImpl userDetails, Review review) {
        if(review.getAuthor().getId()!= userDetails.getAccount().getId()){
            throw new PermissionDeniedException();
        }
    }


    public ReviewResDTO getReviewById(Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(ReviewNotFoundException::new);
        return convertToDto(review);
    }

    public Page<ReviewResDTO> getReviewsByAuthorId(Pageable pageable, Long id) {
        return reviewRepository.findAllByAuthorId(id,pageable).map(this::convertToDto);
    }
}
