package ru.kashtanov.order_service.dto;

import ru.kashtanov.order_service.model.OrderLineItems;

import java.util.List;

public class AnOrderRequestDto {
    private String orderNumber;
    private List<OrderLineItemsDto> orderLineItemsDtoList;

    public AnOrderRequestDto() {
    }

    public AnOrderRequestDto(String orderNumber, List<OrderLineItemsDto> orderLineItemsDtoList) {
        this.orderNumber = orderNumber;
        this.orderLineItemsDtoList = orderLineItemsDtoList;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public List<OrderLineItemsDto> getOrderLineItemsDtoList() {
        return orderLineItemsDtoList;
    }

    public void setOrderLineItemsDtoList(List<OrderLineItemsDto> orderLineItemsDtoList) {
        this.orderLineItemsDtoList = orderLineItemsDtoList;
    }

    @Override
    public String toString() {
        return "AnOrderRequestDto{" +
                "orderNumber='" + orderNumber + '\'' +
                ", orderLineItemsDtoList=" + orderLineItemsDtoList +
                '}';
    }
}
