package ru.kashtanov.order_service.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
public class AnOrder {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String orderNumber;
    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderLineItems> orderLineItemsList;

    public AnOrder() {
    }

    public AnOrder(Long orderId,
                   String orderNumber,
                   List<OrderLineItems> orderLineItemsList) {
        this.id = orderId;
        this.orderNumber = orderNumber;
        this.orderLineItemsList = orderLineItemsList;
    }

    public Long getOrderId() {
        return id;
    }

    public void setOrderId(Long orderId) {
        this.id = orderId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public List<OrderLineItems> getOrderLineItemsList() {
        return orderLineItemsList;
    }

    public void setOrderLineItemsList(List<OrderLineItems> orderLineItemsList) {
        this.orderLineItemsList = orderLineItemsList;
    }
}
