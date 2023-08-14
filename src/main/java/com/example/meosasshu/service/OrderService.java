package com.example.meosasshu.service;

import com.example.meosasshu.dto.request.CreateOrderReqDTO;
import com.example.meosasshu.dto.request.OrderProductReqDTO;
import com.example.meosasshu.dto.response.OrderResDTO;
import com.example.meosasshu.entity.*;
import com.example.meosasshu.exception.OrderNotFoundException;
import com.example.meosasshu.exception.PermissionDeniedException;
import com.example.meosasshu.exception.ProductNotFoundException;
import com.example.meosasshu.repository.OrderRepository;
import com.example.meosasshu.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    public Long createOrder(CreateOrderReqDTO createOrderReqDTO, Account account) {
        Delivery delivery = Delivery.createDelivery(createOrderReqDTO.getDelivery());
        List<OrderProduct> orderProducts = new ArrayList<>();
        for(OrderProductReqDTO orderProductReqDTO : createOrderReqDTO.getOrderProducts()) {
            Product product = productRepository.findById(orderProductReqDTO.getProductId()).orElseThrow(ProductNotFoundException::new);
            OrderProduct orderProduct = OrderProduct.createOrderProduct(product, orderProductReqDTO.getQuantity());
            orderProducts.add(orderProduct);
        }
        Order order = Order.createOrder(account, delivery, orderProducts);
        orderRepository.save(order);
        return order.getId();
    }

    public void cancelOrder(Long orderId, Account account) {
        Order order = orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);
        if(order.getAccount().getId() != account.getId()) {
            throw new PermissionDeniedException();
        }
        order.cancel();
    }

    public OrderResDTO getOrder(Long orderId, Account account) {
        Order order = orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);
        if(order.getAccount().getId() != account.getId()) {
            throw new PermissionDeniedException();
        }
        return OrderResDTO.createDto(order);
    }

    public Page<OrderResDTO> getOrders(Pageable pageable,Long accountId) {
        return orderRepository.findAllByAccountId(accountId,pageable).map(OrderResDTO::createDto);
    }
}
