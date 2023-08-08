package com.example.meosasshu.service;

import com.example.meosasshu.dto.response.*;
import com.example.meosasshu.entity.Product;
import com.example.meosasshu.exception.ProductNotExistException;
import com.example.meosasshu.repository.ProductRepository;
import com.example.meosasshu.repository.ReviewRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;
    public Page<ProductPagingDTO> getAllProducts(Pageable pageable) {
       return productRepository.findAll(pageable).map(ProductPagingDTO::createDto);

    }

    public ProductDetailDTO getProductById(Long productId) {
        return productRepository.findById(productId).map(ProductDetailDTO::createDto).orElseThrow(
                ProductNotExistException::new);
    }

    public OrderFormResDTO getOrderForm(Long productId, Long quantity) {
        Product product = productRepository.findById(productId).orElseThrow(
                ProductNotExistException::new
        );



        OrderProductDTO orderProductDTO = OrderProductDTO.createDto(product);

//        product.removeStock(quantity);

        orderProductDTO.setQuantity(quantity);
        orderProductDTO.setTotalPrice(quantity*product.getPrice());

        return OrderFormResDTO.createDto(List.of(orderProductDTO));
    }

    public Page<ReviewResDTO> getProductReviews(Pageable pageable, Long productId) {
        return reviewRepository.findAllByProductId(productId,pageable).map(ReviewResDTO::createDto);
    }
}
