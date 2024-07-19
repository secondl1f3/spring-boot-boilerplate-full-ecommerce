package com.sakanlabs.badal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class RefreshTokenExpiredException extends TokenExpiredException {
    public RefreshTokenExpiredException() {
        super("Refresh token is expired!");
    }

    public RefreshTokenExpiredException(final String message) {
        super(message);
    }
}
