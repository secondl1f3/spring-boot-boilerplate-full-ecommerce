package com.sakanlabs.badal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UploadException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public UploadException() {
        super("Upload Failed!");
    }

    public UploadException(final String message) {
        super(message);
    }
}
