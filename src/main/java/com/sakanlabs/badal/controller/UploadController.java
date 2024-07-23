package com.sakanlabs.badal.controller;

import com.sakanlabs.badal.exception.UploadException;
import com.sakanlabs.badal.service.UploadService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/uploads")
@Tag(name = "004. Upload", description = "Upload API")
public class UploadController {
    private final UploadService uploadService;

    @PostMapping("/payment/{paymentId}")
    public ResponseEntity<String> uploadProof(@PathVariable("paymentId") String paymentId, @RequestParam("file") MultipartFile file) {
        try {
            String fileUrl = uploadService.uploadPaymentProof(file, paymentId);
            return ResponseEntity.ok(fileUrl);
        } catch (UploadException | IOException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/user/{userId}")
    public ResponseEntity<String> uploadAvatar(@PathVariable("userId") String userId, @RequestParam("file") MultipartFile file) {
        try {
            String fileUrl = uploadService.uploadProfilePicture(file, userId);
            return ResponseEntity.ok(fileUrl);
        } catch (UploadException | IOException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
