package com.sakanlabs.badal.dto.response.order;

import com.sakanlabs.badal.util.OrderPaymentStatus;
import com.sakanlabs.badal.util.OrderPaymentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderPaymentDto {

    private String id;

    private OrderPaymentType type;

    private OrderPaymentStatus status;

    private String proofUrl;

    private LocalDateTime confirmedAt;

    private String confirmedBy;
}
