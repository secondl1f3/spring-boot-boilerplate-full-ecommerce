package com.sakanlabs.badal.service.impl;

import com.sakanlabs.badal.dto.response.order.OrderDto;
import com.sakanlabs.badal.entity.Order;
import com.sakanlabs.badal.entity.Product;
import com.sakanlabs.badal.entity.specification.criteria.PaginationCriteria;
import com.sakanlabs.badal.exception.NotFoundException;
import com.sakanlabs.badal.mapper.OrderEntityMapper;
import com.sakanlabs.badal.repository.OrderRepository;
import com.sakanlabs.badal.service.OrderService;
import com.sakanlabs.badal.util.OrderPaymentStatus;
import com.sakanlabs.badal.util.OrderStatus;
import com.sakanlabs.badal.util.PageRequestBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderEntityMapper orderEntityMapper;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        Order order = orderEntityMapper.mapToOrder(orderDto);
        order.setOrderStatus(OrderStatus.NEW);
        order.setPaymentStatus(orderDto.getPaymentStatus());
        order.setPaymentType(orderDto.getPaymentType());
        Order savedOrder = orderRepository.save(order);
        return OrderEntityMapper.mapToOrderDto(savedOrder);
    }

    @Override
    public OrderDto getOrderById(String orderId) {
        Order order =  orderRepository.findById(UUID.fromString(orderId))
                .orElseThrow(() ->
                        new NotFoundException("Order doesn't exist with the given id: " + orderId));
        return OrderEntityMapper.mapToOrderDto(order);
    }

    @Override
    public Page<OrderDto> getAllOrders(String userId, Pageable pageable) {
        Page<Order> orders = orderRepository.findByUserId(UUID.fromString(userId), pageable);
        return orders.map(OrderEntityMapper::mapToOrderDto);
    }

    @Override
    public OrderDto updateOrder(String orderId, OrderDto updatedOrderDto) {
        Order order = orderRepository.findById(UUID.fromString(orderId))
                .orElseThrow(() ->
                        new NotFoundException("Order doesn't exist with the given id: " + orderId));

        Order updatedOrder = orderEntityMapper.mapToOrder(updatedOrderDto);
        updatedOrder.setOrderStatus(updatedOrderDto.getOrderStatus());
        updatedOrder.setPaymentStatus(updatedOrderDto.getPaymentStatus());
        updatedOrder = orderRepository.save(updatedOrder);
        return OrderEntityMapper.mapToOrderDto(updatedOrder);
    }

    @Override
    public void deleteOrder(String orderId) {
        Order order = orderRepository.findById(UUID.fromString(orderId))
                .orElseThrow(() ->
                        new NotFoundException("Order doesn't exist with the given id: " + orderId));

        orderRepository.deleteById(UUID.fromString(orderId));
    }
}
