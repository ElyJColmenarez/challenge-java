package com.notifications.channels.email;

import com.notifications.channels.email.providers.EmailProvider;
import com.notifications.channels.email.providers.MailgunProvider;
import com.notifications.channels.email.providers.SendGridProvider;
import com.notifications.core.AbstractNotificationChannel;
import com.notifications.core.EmailNotification;
import com.notifications.core.NotificationResult;
import com.notifications.validation.NotificationValidator;

/**
 * Canal de notificación para Email.
 */
public class EmailChannel extends AbstractNotificationChannel<EmailNotification> {
    private final EmailProvider provider;

    public EmailChannel(EmailConfig config) {
        this.provider = createProvider(config);
    }

    private EmailProvider createProvider(EmailConfig config) {
        if ("MAILGUN".equalsIgnoreCase(config.getProviderType())) {
            return new MailgunProvider(config.getApiKey(), config.getDomain());
        }
        return new SendGridProvider(config.getApiKey());
    }

    @Override
    protected Class<EmailNotification> getNotificationClass() {
        return EmailNotification.class;
    }

    @Override
    protected void validateSpecific(EmailNotification email) {
        if (!NotificationValidator.isValidEmail(email.recipient())) {
            throw new IllegalArgumentException("Invalid email format: " + email.recipient());
        }
    }

    @Override
    protected NotificationResult performSend(EmailNotification notification) {
        // Aseguramos el envío al proveedor con el tipo específico
        return provider.send(notification);
    }

    @Override
    public String getChannelName() {
        return "EMAIL";
    }
}
