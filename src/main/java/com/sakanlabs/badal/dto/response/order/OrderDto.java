package com.sakanlabs.badal.dto.response.order;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDto {

    private UUID id;

    private String userId;

    private int totalQuantity;

    private BigDecimal totalPrice;

    private OrderStatus orderStatus;

    private ProductDto product;

    private OrderPaymentDto orderPayment;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
