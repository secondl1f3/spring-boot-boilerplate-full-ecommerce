package com.sakanlabs.badal.dto.response.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {
    public String id;
    public String userId;
    public String productId;
    public String productName;
    public int quantity;
    public BigDecimal totalProductPrice;
}
