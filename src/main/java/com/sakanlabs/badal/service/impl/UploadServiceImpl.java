package com.sakanlabs.badal.service.impl;

import com.sakanlabs.badal.entity.OrderPayment;
import com.sakanlabs.badal.entity.User;
import com.sakanlabs.badal.exception.NotFoundException;
import com.sakanlabs.badal.exception.UploadException;
import com.sakanlabs.badal.repository.PaymentRepository;
import com.sakanlabs.badal.repository.UserRepository;
import com.sakanlabs.badal.service.UploadService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UploadServiceImpl implements UploadService {

    @Value("${upload.directory}")
    private String uploadDirectory;

    @Value("${server.url}")
    private String serverUrl;

    private final PaymentRepository paymentRepository;

    private final UserRepository userRepository;

    public String uploadPaymentProof(MultipartFile file, String paymentId) throws IOException {
        if (file.isEmpty() || !isImage(file)) {
            throw new UploadException("Invalid file. Only PNG and JPG are allowed.");
        }

        String fileName = UUID.randomUUID().toString() + getExtension(file.getOriginalFilename());
        Path filePath = Paths.get(uploadDirectory, fileName);
        Files.createDirectories(filePath.getParent());
        Files.copy(file.getInputStream(), filePath);
        String fileUrl = serverUrl + "/uploads/" + fileName;

        OrderPayment orderPayment = paymentRepository.findById(UUID.fromString(paymentId))
                .orElseThrow(() ->
                        new NotFoundException("Payment doesn't exist with the given id: " + paymentId));
        orderPayment.setProofUrl(fileUrl);
        paymentRepository.save(orderPayment);

        return fileUrl;
    }

    @Override
    public String uploadProfilePicture(MultipartFile file, String userId) throws IOException {

        if (file.isEmpty() || !isImage(file)) {
            throw new UploadException("Invalid file. Only PNG and JPG are allowed.");
        }

        String fileName = UUID.randomUUID().toString() + getExtension(file.getOriginalFilename());
        Path filePath = Paths.get(uploadDirectory, fileName);
        Files.createDirectories(filePath.getParent());
        Files.copy(file.getInputStream(), filePath);
        String fileUrl = serverUrl + "/uploads/" + fileName;

        User user = userRepository.findById(UUID.fromString(userId))
                .orElseThrow(() ->
                        new NotFoundException("User doesn't exist with the given id: " + userId));
        user.setAvatar(fileUrl);
        userRepository.save(user);

        return fileUrl;
    }

    private boolean isImage(MultipartFile file) {
        String contentType = file.getContentType();
        return contentType.equals("image/png") || contentType.equals("image/jpeg");
    }

    private String getExtension(String fileName) {
        if (fileName.contains(".")) {
            return fileName.substring(fileName.lastIndexOf("."));
        } else {
            return "";
        }
    }
}
