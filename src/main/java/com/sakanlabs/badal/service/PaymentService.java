package com.sakanlabs.badal.service;

import com.sakanlabs.badal.dto.request.UploadPaymentDto;

public interface PaymentService {

    void uploadProof(UploadPaymentDto uploadPaymentDto);

    void confirmPayment(UploadPaymentDto uploadPaymentDto);
}
