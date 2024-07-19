package com.sakanlabs.badal.event;

import com.sakanlabs.badal.entity.User;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class UserEmailVerificationSendEvent extends ApplicationEvent {
    private final User user;

    public UserEmailVerificationSendEvent(Object source, User user) {
        super(source);
        this.user = user;
    }
}
