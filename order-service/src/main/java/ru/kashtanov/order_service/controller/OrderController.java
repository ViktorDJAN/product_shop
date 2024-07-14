package ru.kashtanov.order_service.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.kashtanov.order_service.dto.AnOrderRequestDto;
import ru.kashtanov.order_service.dto.OrderLineItemsDto;
import ru.kashtanov.order_service.model.AnOrder;
import ru.kashtanov.order_service.model.OrderLineItems;
import ru.kashtanov.order_service.service.AnOrderService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

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
        return new AnOrderRequestDto("A01",orderLineItems);
    }

    @PostMapping("/place_order")
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name="inventory",fallbackMethod = "fallbackMethod")
    @TimeLimiter(name="inventory")
    public CompletableFuture<String> placeAnOrder(@RequestBody AnOrderRequestDto anOrderRequestDto){
        orderService.makeAnOrder(anOrderRequestDto);
        return CompletableFuture.supplyAsync(()->"The order is placed successfully");
    }

    public CompletableFuture<String>  fallbackMethod(AnOrderRequestDto orderRequestDto,RuntimeException runtimeException){
        return CompletableFuture.supplyAsync(()->"Oops! Something went wrong, please order some time!");
    }

    @GetMapping("/all")
    public List<AnOrder> getAllOrders(){
       return orderService.findAllOrders();
    }
}
