package ru.kashtanov.inventory_service.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

public class InventoryRequestDto {

    private String skuCode;
    private Integer quantity;

    public InventoryRequestDto() {
    }

    public InventoryRequestDto(String skuCode, Integer quantity) {
        this.skuCode = skuCode;
        this.quantity = quantity;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "InventoryRequestDto{" +
                "skuCode='" + skuCode + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
