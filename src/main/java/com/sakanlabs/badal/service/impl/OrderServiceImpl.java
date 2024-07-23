package com.sakanlabs.badal.service.impl;

import com.sakanlabs.badal.dto.response.order.OrderDto;
import com.sakanlabs.badal.entity.Order;
import com.sakanlabs.badal.entity.OrderPayment;
import com.sakanlabs.badal.entity.Product;
import com.sakanlabs.badal.exception.NotFoundException;
import com.sakanlabs.badal.mapper.OrderEntityMapper;
import com.sakanlabs.badal.mapper.OrderPaymentMapper;
import com.sakanlabs.badal.repository.OrderRepository;
import com.sakanlabs.badal.repository.PaymentRepository;
import com.sakanlabs.badal.repository.ProductRepository;
import com.sakanlabs.badal.service.OrderService;
import com.sakanlabs.badal.util.OrderPaymentStatus;
import com.sakanlabs.badal.util.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderEntityMapper orderEntityMapper;

    @Autowired
    private OrderPaymentMapper orderPaymentMapper;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        Order order = orderEntityMapper.mapToOrder(orderDto);
        order.setOrderStatus(OrderStatus.NEW);

        Product product = productRepository.findById(UUID.fromString(orderDto.getProduct().getId()))
                .orElseThrow(() -> new NotFoundException("Product doesn't exist with the given id: " + orderDto.getProduct().getId()));
        order.setProduct(product);

        OrderPayment orderPayment = new OrderPayment();
        orderPayment.setStatus(OrderPaymentStatus.WAIT);
        orderPayment.setType(orderDto.getOrderPayment().getType());
        orderPayment = paymentRepository.save(orderPayment);
        order.setOrderPayment(orderPayment);

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

        order = orderEntityMapper.mapToOrder(updatedOrderDto);
        order.setOrderStatus(updatedOrderDto.getOrderStatus());

        OrderPayment orderPayment = orderPaymentMapper.mapToOrderPayment(updatedOrderDto.getOrderPayment());
        order.setOrderPayment(orderPayment);

        order = orderRepository.save(order);
        return OrderEntityMapper.mapToOrderDto(order);
    }

    @Override
    public void deleteOrder(String orderId) {
        Order order = orderRepository.findById(UUID.fromString(orderId))
                .orElseThrow(() ->
                        new NotFoundException("Order doesn't exist with the given id: " + orderId));

        orderRepository.deleteById(UUID.fromString(orderId));
    }
}
