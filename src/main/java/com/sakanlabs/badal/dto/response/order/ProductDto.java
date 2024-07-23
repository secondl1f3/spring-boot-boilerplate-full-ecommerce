package com.sakanlabs.badal.dto.response.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private String id;

    private String productName;

    private String description;

    private int quantity;

    private BigDecimal price;

}
