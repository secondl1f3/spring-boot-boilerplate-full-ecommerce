package com.sakanlabs.badal.service.impl;

import com.sakanlabs.badal.dto.request.UploadPaymentDto;
import com.sakanlabs.badal.entity.OrderPayment;
import com.sakanlabs.badal.entity.User;
import com.sakanlabs.badal.exception.NotFoundException;
import com.sakanlabs.badal.repository.PaymentRepository;
import com.sakanlabs.badal.repository.UserRepository;
import com.sakanlabs.badal.service.PaymentService;
import com.sakanlabs.badal.util.OrderPaymentStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void uploadProof(UploadPaymentDto uploadPaymentDto) {
        Optional<OrderPayment> optionalPayment = paymentRepository.findAllByOrder_Id(uploadPaymentDto.getOrderId());
        if (optionalPayment.isPresent()) {
            OrderPayment payment = optionalPayment.get();
            payment.setProofUrl(uploadPaymentDto.getProofUrl());
            payment.setStatus(OrderPaymentStatus.PROOF_UPLOADED);
            paymentRepository.save(payment);
        } else {
            throw new NotFoundException("Payment not found");
        }
    }

    @Override
    public void confirmPayment(UploadPaymentDto uploadPaymentDto) {

        Optional<OrderPayment> optionalPayment = paymentRepository.findById(UUID.fromString(uploadPaymentDto.getPaymentId()));
        if (optionalPayment.isPresent()) {
            OrderPayment orderPayment = optionalPayment.get();
            orderPayment.setStatus(OrderPaymentStatus.PAID);
            orderPayment.setConfirmedAt(LocalDateTime.now());

            User user = userRepository.getReferenceById(UUID.fromString(uploadPaymentDto.getUserId()));
            orderPayment.setConfirmedBy(user.getName());

            paymentRepository.save(orderPayment);
        } else {
            throw new NotFoundException("Payment not found");
        }

    }
}
