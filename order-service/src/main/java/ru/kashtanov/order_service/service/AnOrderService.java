package ru.kashtanov.order_service.service;

import org.springframework.stereotype.Service;
import ru.kashtanov.order_service.dto.AnOrderRequestDto;
import ru.kashtanov.order_service.dto.OrderLineItemsDto;
import ru.kashtanov.order_service.model.AnOrder;
import ru.kashtanov.order_service.model.OrderLineItems;
import ru.kashtanov.order_service.repository.AnOrderRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnOrderService {
    private final AnOrderRepository orderRepository;

    public AnOrderService(AnOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    public void makeAnOrder(AnOrderRequestDto orderRequestDto){
        AnOrder anOrder = new AnOrder();
        anOrder.setOrderNumber(orderRequestDto.getOrderNumber());

        List<OrderLineItems> orderLineItemsList = orderRequestDto
                .getOrderLineItemsDtoList()
                .stream()
                .map(orderLineItemsDto -> mapToOrderLineItemsDto(orderLineItemsDto)).collect(Collectors.toList());
        anOrder.setOrderLineItemsList(orderLineItemsList);
        orderRepository.save(anOrder);
        System.out.println("The order is successfully placed " + anOrder.getOrderId());

    }
    public OrderLineItems mapToOrderLineItemsDto(OrderLineItemsDto orderLineItemsDto){
        return new OrderLineItems(orderLineItemsDto.getSkuCode()
                ,orderLineItemsDto.getPrice()
                ,orderLineItemsDto.getQuantity());
    }

    public List<AnOrder> findAllOrders(){
        return orderRepository.findAll();
    }


}
