package com.sakanlabs.badal.mapper;

import com.sakanlabs.badal.dto.response.order.OrderDto;
import com.sakanlabs.badal.dto.response.order.OrderPaymentDto;
import com.sakanlabs.badal.dto.response.order.ProductDto;
import com.sakanlabs.badal.entity.Order;
import com.sakanlabs.badal.entity.User;
import com.sakanlabs.badal.repository.PaymentRepository;
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

    @Autowired
    private PaymentRepository paymentRepository;

    public Order mapToOrder(OrderDto orderDto) {
        Order order = new Order();
        User user = userRepository.getReferenceById(UUID.fromString(orderDto.getUserId()));
        order.setUser(user);
        order.setTotalQuantity(orderDto.getTotalQuantity());
        order.setTotalPrice(orderDto.getTotalPrice());
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

        ProductDto productDto = new ProductDto();
        productDto.setId(order.getProduct().getId().toString());
        productDto.setProductName(order.getProduct().getName());
        orderDto.setProduct(productDto);

        OrderPaymentDto orderPaymentDto = new OrderPaymentDto();
        orderPaymentDto.setStatus(order.getOrderPayment().getStatus());
        orderPaymentDto.setType(order.getOrderPayment().getType());
        orderDto.setOrderPayment(orderPaymentDto);

        return orderDto;
    }

}
