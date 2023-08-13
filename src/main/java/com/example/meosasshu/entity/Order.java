package com.example.meosasshu.entity;

import com.example.meosasshu.enums.OrderStatus;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity @Getter
@Table(name = "orders")
public class Order extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="account_id")
    private Account account;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderProduct> orderProducts = new ArrayList<>();

    private double totalPrice;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="delivery_id")
    private Delivery delivery;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public static Order createOrder(Account account, Delivery delivery, List<OrderProduct> orderProducts){
        Order order = new Order();
        order.account = account;
        order.status = OrderStatus.ORDER;
        order.totalPrice = 0;
        for (OrderProduct orderProduct : orderProducts) {
            order.addOrderProduct(orderProduct);
            orderProduct.getProduct().order(orderProduct.getQuantity());
        }
        order.delivery = delivery;
        return order;
    }

    private void addOrderProduct(OrderProduct orderProduct) {
        orderProducts.add(orderProduct);
        orderProduct.setOrder(this);
        totalPrice += orderProduct.getProduct().getPrice()*orderProduct.getQuantity();
    }

    public void cancel() {
        this.status = OrderStatus.CANCEL;
        for (OrderProduct orderProduct : orderProducts) {
            orderProduct.getProduct().cancel(orderProduct.getQuantity());
        }
    }
}
