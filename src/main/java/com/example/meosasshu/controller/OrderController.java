package com.example.meosasshu.controller;

import com.example.meosasshu.dto.request.CreateOrderReqDTO;
import com.example.meosasshu.dto.response.OrderResDTO;
import com.example.meosasshu.security.user.CurrentUser;
import com.example.meosasshu.security.user.UserDetailsImpl;
import com.example.meosasshu.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderService orderService;

    @Secured("ROLE_USER")
    @PostMapping
    public ResponseEntity<Long> createOrder(@RequestBody CreateOrderReqDTO createOrderReqDTO, @CurrentUser UserDetailsImpl userDetails) {
        Long orderId = orderService.createOrder(createOrderReqDTO, userDetails.getAccount());
        return ResponseEntity.ok(orderId);
    }

    @Secured("ROLE_USER")
    @PutMapping("/{orderId}")
    public ResponseEntity<Void> cancelOrder(@PathVariable Long orderId, @CurrentUser UserDetailsImpl userDetails) {
        orderService.cancelOrder(orderId, userDetails.getAccount());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Secured("ROLE_USER")
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResDTO> getOrder(@PathVariable Long orderId, @CurrentUser UserDetailsImpl userDetails) {
        OrderResDTO dto = orderService.getOrder(orderId, userDetails.getAccount());
        return ResponseEntity.ok(dto);
    }

    @Secured("ROLE_USER")
    @GetMapping
    public ResponseEntity<Page<OrderResDTO>> getOrders(
            @CurrentUser UserDetailsImpl userDetails,
            @PageableDefault(sort = "id", size=10,direction = Sort.Direction.DESC) Pageable pageable) {
        Page<OrderResDTO> dto = orderService.getOrders(pageable, userDetails.getAccount().getId());
        return ResponseEntity.ok(dto);
    }
}
