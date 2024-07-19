package com.sakanlabs.badal.service;

import com.sakanlabs.badal.entity.JwtToken;
import com.sakanlabs.badal.exception.NotFoundException;
import com.sakanlabs.badal.repository.JwtTokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class JwtTokenService {
    private final JwtTokenRepository jwtTokenRepository;

    private final MessageSourceService messageSourceService;

    /**
     * Find a JWT token by user id and refresh token.
     *
     * @param id           UUID
     * @param refreshToken String
     * @return JwtToken
     */
    public JwtToken findByUserIdAndRefreshToken(UUID id, String refreshToken) {
        return jwtTokenRepository.findByUserIdAndRefreshToken(id, refreshToken)
            .orElseThrow(() -> new NotFoundException(messageSourceService.get("not_found_with_param",
                new String[]{messageSourceService.get("token")})));
    }

    /**
     * Find a JWT token by token or refresh token.
     *
     * @param token String
     * @return JwtToken
     */
    public JwtToken findByTokenOrRefreshToken(String token) {
        return jwtTokenRepository.findByTokenOrRefreshToken(token, token)
            .orElseThrow(() -> new NotFoundException(messageSourceService.get("not_found_with_param",
                new String[]{messageSourceService.get("token")})));
    }

    /**
     * Save a JWT token.
     *
     * @param jwtToken JwtToken
     */
    public void save(JwtToken jwtToken) {
        jwtTokenRepository.save(jwtToken);
    }

    /**
     * Delete a JWT token.
     *
     * @param jwtToken JwtToken
     */
    public void delete(JwtToken jwtToken) {
        jwtTokenRepository.delete(jwtToken);
        log.info("Deleted token: {}", jwtToken);
    }
}
