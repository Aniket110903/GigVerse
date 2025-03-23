package demo.demo.services;

import demo.demo.dto.orderDTO.OrderDTO;
import demo.demo.entities.Order;

import java.util.List;

public abstract class OrderServices {
    public  abstract Order createOrder(OrderDTO payload, String buyerId);
    public  abstract List<OrderDTO> getOrders(Boolean flag, String userId);
}
