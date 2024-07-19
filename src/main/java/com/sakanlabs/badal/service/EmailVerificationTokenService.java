package com.sakanlabs.badal.service;

import com.sakanlabs.badal.entity.EmailVerificationToken;
import com.sakanlabs.badal.entity.User;
import com.sakanlabs.badal.exception.BadRequestException;
import com.sakanlabs.badal.exception.NotFoundException;
import com.sakanlabs.badal.repository.EmailVerificationTokenRepository;
import com.sakanlabs.badal.util.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import static com.sakanlabs.badal.util.Constants.EMAIL_VERIFICATION_TOKEN_LENGTH;

@Service
public class EmailVerificationTokenService {
    private final EmailVerificationTokenRepository emailVerificationTokenRepository;

    private final MessageSourceService messageSourceService;

    private final Long expiresIn;

    /**
     * Email verification token constructor.
     *
     * @param emailVerificationTokenRepository EmailVerificationTokenRepository
     * @param messageSourceService             MessageSourceService
     * @param expiresIn                        Long
     */
    public EmailVerificationTokenService(
        EmailVerificationTokenRepository emailVerificationTokenRepository,
        MessageSourceService messageSourceService,
        @Value("${app.registration.email.token.expires-in}") Long expiresIn
    ) {
        this.emailVerificationTokenRepository = emailVerificationTokenRepository;
        this.messageSourceService = messageSourceService;
        this.expiresIn = expiresIn;
    }

    /**
     * Is e-mail verification token expired?
     *
     * @param token EmailVerificationToken
     * @return boolean
     */
    public boolean isEmailVerificationTokenExpired(EmailVerificationToken token) {
        return token.getExpirationDate().before(new Date());
    }

    /**
     * Create email verification token from user.
     *
     * @param user User
     * @return EmailVerificationToken
     */
    public EmailVerificationToken create(User user) {
        String newToken = new RandomStringGenerator(EMAIL_VERIFICATION_TOKEN_LENGTH).next();
        Date expirationDate = Date.from(Instant.now().plusSeconds(expiresIn));
        Optional<EmailVerificationToken> oldToken = emailVerificationTokenRepository.findByUserId(user.getId());
        EmailVerificationToken emailVerificationToken;

        if (oldToken.isPresent()) {
            emailVerificationToken = oldToken.get();
            emailVerificationToken.setToken(newToken);
            emailVerificationToken.setExpirationDate(expirationDate);
        } else {
            emailVerificationToken = EmailVerificationToken.builder()
                .user(user)
                .token(newToken)
                .expirationDate(Date.from(Instant.now().plusSeconds(expiresIn)))
                .build();
        }

        return emailVerificationTokenRepository.save(emailVerificationToken);
    }

    /**
     * Get email verification token by token.
     *
     * @param token String
     * @return User
     */
    public User getUserByToken(String token) {
        EmailVerificationToken emailVerificationToken = emailVerificationTokenRepository.findByToken(token)
            .orElseThrow(() -> new NotFoundException(messageSourceService.get("not_found_with_param",
                new String[]{messageSourceService.get("token")})));

        if (isEmailVerificationTokenExpired(emailVerificationToken)) {
            throw new BadRequestException(messageSourceService.get("expired_with_param",
                new String[]{messageSourceService.get("token")}));
        }

        return emailVerificationToken.getUser();
    }

    /**
     * Delete email verification token by user ID.
     *
     * @param userId UUID
     */
    public void deleteByUserId(UUID userId) {
        emailVerificationTokenRepository.deleteByUserId(userId);
    }
}
