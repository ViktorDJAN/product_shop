package ru.kashtanov.order_service.model;

import jakarta.persistence.*;

@Entity
@Table
public class OrderLineItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String skuCode;
    private Double price;
    private Integer quantity;

    public OrderLineItems() {
    }

    public OrderLineItems(String skuCode, Double price, Integer quantity) {
        this.skuCode = skuCode;
        this.price = price;
        this.quantity = quantity;
    }

    public OrderLineItems(Long id,
                          String skuCode,
                          Double price,
                          Integer quantity) {
        this.id = id;
        this.skuCode = skuCode;
        this.price = price;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderLineItems{" +
                "id=" + id +
                ", skuCode='" + skuCode + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
