package com.example.meosasshu.service;

import com.example.meosasshu.dto.response.*;
import com.example.meosasshu.entity.Product;
import com.example.meosasshu.exception.ProductNotFoundException;
import com.example.meosasshu.repository.ProductRepository;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.completion.CompletionResult;
import com.theokanning.openai.service.OpenAiService;
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
    private final OpenAiService openAiService;
    public Page<ProductPagingDTO> getAllProducts(Pageable pageable) {
       return productRepository.findAll(pageable).map(ProductPagingDTO::createDto);

    }

    public ProductDetailDTO getProductById(Long productId) {

        Product product = productRepository.findById(productId).orElseThrow(
                ProductNotFoundException::new
        );

        String shortDescription = generateSimplifiedDescription(product.getDescription());
//        String shortDescription = "\n\n1. 예시\n2. 예시\n3. 예시";

        ProductDetailDTO productDetailDTO = ProductDetailDTO.createDto(product);
        productDetailDTO.setShortDescription(shortDescription);

        return productDetailDTO;
    }
    private String generateSimplifiedDescription(String originalDescription) {
        // ChatGPT 서비스 호출을 위한 메시지 생성
        String gptPrompt = "한국인 노인이 쉽게 이해할 수 있도록 글을 최대한 짧고 간략하게 3줄 요약해줘.\n" +
                "예시: 1. 편안하고 흡수력 뛰어난 성인용 기저귀.\"\n" +
                "2. 피부 건강에 안전한 선택.\n" +
                "3. 최고 품질 성인용 기저귀를 저렴한 가격에." +
                "\n\n 요약할 글: "
                + originalDescription; // 제품의 상세내용을 이어서 추가

        // ChatGPT 서비스 호출
        CompletionResult completion = openAiService.createCompletion(CompletionRequest.builder()
                .model("text-davinci-003")
                .prompt(gptPrompt)
                .temperature(0.3)
                .maxTokens(256) // 적절한 요약 길이로 설정
                .build()
        );

        // ChatGPT 응답에서 요약된 텍스트 추출
        return completion.getChoices().get(0).getText();
    }

    public OrderFormResDTO getOrderForm(Long productId, Long quantity) {
        Product product = productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);

        product.checkSufficientStock(quantity);
        OrderProductDTO orderProductDTO = OrderProductDTO.createDto(product,quantity);

        return OrderFormResDTO.createDto(List.of(orderProductDTO));
    }


    public Page<ProductPagingDTO> getTopSellingProducts(Pageable pageable) {
        return  productRepository.findAllByOrderBySalesCountDesc(pageable).map(ProductPagingDTO::createDto);
    }

    public Page<ProductPagingDTO> searchProducts(String keyword, Pageable pageable) {
        return productRepository.findAllByNameContaining(keyword,pageable).map(ProductPagingDTO::createDto);
    }
}
