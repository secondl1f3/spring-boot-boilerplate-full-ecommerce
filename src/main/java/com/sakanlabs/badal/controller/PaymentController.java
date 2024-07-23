package com.sakanlabs.badal.controller;

import com.sakanlabs.badal.dto.request.UploadPaymentDto;
import com.sakanlabs.badal.service.PaymentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payments")
@Tag(name = "004. Payments", description = "Payments API")
public class PaymentController extends AbstractBaseController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/upload-proof")
    public void uploadProof(@RequestBody UploadPaymentDto uploadPaymentDto) {
        paymentService.uploadProof(uploadPaymentDto);
    }

    @PostMapping("/confirm")
    public void confirmPayment(@RequestBody UploadPaymentDto uploadPaymentDto) {
        paymentService.confirmPayment(uploadPaymentDto);
    }
}
