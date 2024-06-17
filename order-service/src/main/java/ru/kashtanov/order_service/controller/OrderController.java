package ru.kashtanov.order_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.kashtanov.order_service.dto.AnOrderRequestDto;
import ru.kashtanov.order_service.dto.OrderLineItemsDto;
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
    public AnOrderRequestDto getSingle(){

        List<OrderLineItemsDto>orderLineItems = new ArrayList<>();
        OrderLineItemsDto itemsDto = new OrderLineItemsDto("OO1",10.2,5);
        orderLineItems.add(itemsDto);

        AnOrderRequestDto anOrderRequestDto = new AnOrderRequestDto("A01",orderLineItems);

        return anOrderRequestDto;
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
