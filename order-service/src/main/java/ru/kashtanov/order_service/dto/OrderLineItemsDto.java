package ru.kashtanov.order_service.dto;

public class OrderLineItemsDto {
    private String skuCode;
    private Double price;
    private Integer quantity;

    public OrderLineItemsDto() {
    }

    public OrderLineItemsDto(String skuCode, Double price, Integer quantity) {
        this.skuCode = skuCode;
        this.price = price;
        this.quantity = quantity;
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
        return "OrderLineItemsDto{" +
                "skuCode='" + skuCode + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
