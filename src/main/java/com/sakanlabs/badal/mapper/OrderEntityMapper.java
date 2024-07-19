package com.sakanlabs.badal.mapper;

import com.sakanlabs.badal.dto.response.order.OrderDto;
import com.sakanlabs.badal.entity.Order;
import com.sakanlabs.badal.entity.Product;
import com.sakanlabs.badal.entity.User;
import com.sakanlabs.badal.repository.ProductRepository;
import com.sakanlabs.badal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class OrderEntityMapper {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    public Order mapToOrder(OrderDto orderDto) {
        Order order = new Order();
        User user = userRepository.getReferenceById(UUID.fromString(orderDto.getUserId()));
        order.setUser(user);
        order.setTotalQuantity(orderDto.getTotalQuantity());
        order.setTotalPrice(orderDto.getTotalPrice());
        Product product = productRepository.getReferenceById(UUID.fromString(orderDto.getProductId()));
        order.setProduct(product);
        return order;
    }

    public static OrderDto mapToOrderDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setUserId(order.getUser().getId().toString());
        orderDto.setTotalQuantity(order.getTotalQuantity());
        orderDto.setTotalPrice(order.getTotalPrice());
        orderDto.setCreatedAt(order.getCreatedAt());
        orderDto.setUpdatedAt(order.getUpdatedAt());
        orderDto.setOrderStatus(order.getOrderStatus());
        orderDto.setPaymentStatus(order.getPaymentStatus());
        orderDto.setPaymentType(order.getPaymentType());
        return orderDto;
    }

}
