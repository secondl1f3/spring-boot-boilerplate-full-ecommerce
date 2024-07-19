package com.sakanlabs.badal.service;


import com.sakanlabs.badal.dto.response.order.OrderDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {

    OrderDto createOrder(OrderDto orderDto);
    OrderDto getOrderById(String orderId);
    Page<OrderDto> getAllOrders(String userId, Pageable pageable);
    OrderDto updateOrder(String orderId, OrderDto updatedOrderDto);
    void deleteOrder(String orderId);
}
