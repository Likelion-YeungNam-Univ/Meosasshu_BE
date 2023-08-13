package com.example.meosasshu.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class OrderProduct extends BaseEntity{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_product_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product_id")
    private Product product;

    private long quantity;

    public static OrderProduct createOrderProduct(Product product, Long quantity) {
        OrderProduct newOrderProduct = new OrderProduct();
        newOrderProduct.product = product;
        newOrderProduct.quantity = quantity;
        return newOrderProduct;
    }
}
