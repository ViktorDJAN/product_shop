package ru.kashtanov.order_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.kashtanov.order_service.dto.AnOrderRequestDto;
import ru.kashtanov.order_service.model.AnOrder;
import ru.kashtanov.order_service.model.OrderLineItems;
import ru.kashtanov.order_service.service.AnOrderService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/order_scope")
public class OrderController {
    private final AnOrderService orderService;

    public OrderController(AnOrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/single")
    public AnOrder getSingle(){
        OrderLineItems oli = new OrderLineItems(1L,"skuCode_1",10.0,10);
        List<OrderLineItems>orderLineItems = new ArrayList<>();
        orderLineItems.add(oli);
        return new AnOrder(1L,"A001",orderLineItems);
    }

    @PostMapping("/place_order")
    @ResponseStatus(HttpStatus.CREATED)
    public String placeAnOrder(@RequestBody AnOrderRequestDto anOrderRequestDto){
        orderService.makeAnOrder(anOrderRequestDto);
        return "The order is placed successfully";
    }

    @GetMapping("/all")
    public List<AnOrder> getAllOrders(){
       return orderService.findAllOrders();
    }
}
