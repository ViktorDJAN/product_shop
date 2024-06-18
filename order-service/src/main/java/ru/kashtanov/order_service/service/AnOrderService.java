package ru.kashtanov.order_service.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import ru.kashtanov.order_service.dto.AnOrderRequestDto;
import ru.kashtanov.order_service.dto.InventoryResponse;
import ru.kashtanov.order_service.dto.OrderLineItemsDto;
import ru.kashtanov.order_service.model.AnOrder;
import ru.kashtanov.order_service.model.OrderLineItems;
import ru.kashtanov.order_service.repository.AnOrderRepository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnOrderService {
    private final AnOrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;

    public AnOrderService(AnOrderRepository orderRepository, WebClient.Builder webClientBuilder) {
        this.orderRepository = orderRepository;

        this.webClientBuilder = webClientBuilder;
    }


    public void makeAnOrder(AnOrderRequestDto orderRequestDto) {
        //Creating an Order
        AnOrder anOrder = new AnOrder();

        anOrder.setOrderNumber(orderRequestDto.getOrderNumber());

        List<OrderLineItems> orderLineItemsList = orderRequestDto
                .getOrderLineItemsDtoList()
                .stream()
                .map(orderLineItemsDto -> mapToOrderLineItemsDto(orderLineItemsDto)).collect(Collectors.toList());
        anOrder.setOrderLineItemsList(orderLineItemsList);

        List<String> skuCodes = anOrder.getOrderLineItemsList()
                .stream()
                .map(orderLineItems -> orderLineItems.getSkuCode())
                .collect(Collectors.toList());

        // Call Inventory-service ,and place the order if the product is in the stock
        // Here we appeal to Inventory-service for checking inventory is in stock or not
        InventoryResponse[] inventoryResponseArray = webClientBuilder.build().get()
                .uri("http://inventory-service/api/v1/inventory_scope/is_in_stock",
                        uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();
        boolean resultIsInStock = Arrays.stream(inventoryResponseArray)
                .allMatch(inventoryResponse -> inventoryResponse.isInStock()); // one of items is false => false

        if (resultIsInStock) {
            orderRepository.save(anOrder);
        } else {
            throw new IllegalStateException("There is not such inventory in the common stock");
        }
        System.out.println("The order is successfully placed " + anOrder.getOrderId());
    }


    public OrderLineItems mapToOrderLineItemsDto(OrderLineItemsDto orderLineItemsDto) {
        return new OrderLineItems(orderLineItemsDto.getSkuCode()
                , orderLineItemsDto.getPrice()
                , orderLineItemsDto.getQuantity());
    }

    public List<AnOrder> findAllOrders() {
        return orderRepository.findAll();
    }


}
