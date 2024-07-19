package com.sakanlabs.badal.dto.response.order;

import com.sakanlabs.badal.util.OrderPaymentStatus;
import com.sakanlabs.badal.util.OrderPaymentType;
import com.sakanlabs.badal.util.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private UUID id;

    private String userId;

    private String productId;

    private int totalQuantity;

    private BigDecimal totalPrice;

    private OrderStatus orderStatus;

    private OrderPaymentStatus paymentStatus;

    private OrderPaymentType paymentType;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
