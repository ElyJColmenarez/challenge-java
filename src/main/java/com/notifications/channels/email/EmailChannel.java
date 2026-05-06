package com.notifications.channels.email;

import com.notifications.channels.email.providers.EmailProvider;
import com.notifications.channels.email.providers.MailgunProvider;
import com.notifications.channels.email.providers.SendGridProvider;
import com.notifications.core.EmailNotification;
import com.notifications.core.Notification;
import com.notifications.core.NotificationChannel;
import com.notifications.core.NotificationResult;
import com.notifications.validation.NotificationValidator;
import com.notifications.validation.ValidationResult;

/**
 * Canal de notificación para Email.
 */
public class EmailChannel implements NotificationChannel {
    private final EmailProvider provider;

    public EmailChannel(EmailConfig config) {
        this.provider = createProvider(config);
    }

    private EmailProvider createProvider(EmailConfig config) {
        if ("MAILGUN".equalsIgnoreCase(config.getProviderType())) {
            return new MailgunProvider(config.getApiKey(), config.getDomain());
        }
        // Por defecto SendGrid
        return new SendGridProvider(config.getApiKey());
    }

    @Override
    public NotificationResult send(Notification notification) {
        if (!(notification instanceof EmailNotification email)) {
            return NotificationResult.invalid("Expected EmailNotification but received: " + notification.getClass().getSimpleName());
        }

        ValidationResult commonValidation = NotificationValidator.validateCommon(notification);
        if (!commonValidation.valid()) {
            return NotificationResult.invalid(String.join(", ", commonValidation.errors()));
        }

        if (!NotificationValidator.isValidEmail(email.recipient())) {
            return NotificationResult.invalid("Invalid email format: " + email.recipient());
        }

        return provider.send(notification);
    }

    @Override
    public String getChannelName() {
        return "EMAIL";
    }
}
