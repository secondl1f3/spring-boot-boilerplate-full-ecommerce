package com.sakanlabs.badal.mapper;

import com.sakanlabs.badal.dto.response.order.OrderPaymentDto;
import com.sakanlabs.badal.entity.OrderPayment;
import org.springframework.stereotype.Component;

@Component
public class OrderPaymentMapper {

    public static OrderPaymentDto mapToOrderPaymentDto(OrderPayment orderPayment) {

        if (null == orderPayment) {
            return null;
        }

        OrderPaymentDto orderPaymentDto = new OrderPaymentDto();
        orderPaymentDto.setId(orderPayment.getId().toString());
        orderPaymentDto.setType(orderPayment.getType());
        orderPaymentDto.setStatus(orderPayment.getStatus());
        if (null != orderPayment.getConfirmedAt()) {
            orderPaymentDto.setConfirmedAt(orderPayment.getConfirmedAt());
        }
        if (null != orderPayment.getConfirmedBy()) {
            orderPaymentDto.setConfirmedBy(orderPayment.getConfirmedBy());
        }
        if (null != orderPayment.getProofUrl()) {
            orderPaymentDto.setProofUrl(orderPayment.getProofUrl());
        }
        return orderPaymentDto;
    }

    public OrderPayment mapToOrderPayment(OrderPaymentDto orderPaymentDto) {
        OrderPayment orderPayment = new OrderPayment();
        orderPayment.setStatus(orderPaymentDto.getStatus());
        orderPayment.setType(orderPaymentDto.getType());
        if (null != orderPaymentDto.getConfirmedAt()) {
            orderPayment.setConfirmedAt(orderPaymentDto.getConfirmedAt());
        }
        if (null != orderPaymentDto.getConfirmedBy()) {
            orderPayment.setConfirmedBy(orderPaymentDto.getConfirmedBy());
        }
        if (null != orderPaymentDto.getProofUrl()) {
            orderPayment.setProofUrl(orderPaymentDto.getProofUrl());
        }
        return orderPayment;
    }
}
