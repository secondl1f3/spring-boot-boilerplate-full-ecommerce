package com.sakanlabs.badal.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UploadPaymentDto {
    private String orderId;
    private String proofUrl;
    private String userId;
    private String paymentId;
}
