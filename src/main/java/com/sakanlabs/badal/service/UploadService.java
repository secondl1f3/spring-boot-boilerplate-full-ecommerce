package com.sakanlabs.badal.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UploadService {

    String uploadPaymentProof(MultipartFile file, String paymentId) throws IOException;

    String uploadProfilePicture(MultipartFile file, String userId) throws IOException;

}
