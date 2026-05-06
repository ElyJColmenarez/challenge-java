package com.notifications.channels.email.providers;

import com.notifications.core.EmailNotification;
import com.notifications.core.Notification;
import com.notifications.core.NotificationResult;
import lombok.extern.slf4j.Slf4j;

/**
 * Implementación simulada de SendGrid.
 */
@Slf4j
public class SendGridProvider implements EmailProvider {
    private final String apiKey;

    public SendGridProvider(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public NotificationResult send(Notification notification) {
        if (!(notification instanceof EmailNotification email)) {
            return NotificationResult.failed("Expected EmailNotification", null);
        }

        if (apiKey == null || apiKey.isEmpty()) {
            return NotificationResult.failed("SendGrid API Key is missing", null);
        }

        log.info("[SIMULATED] Sending Email via SendGrid - To: {}, Subject: {}, Content: {}", 
                 email.recipient(), email.subject(), email.content());
        
        return NotificationResult.success("Email sent successfully via SendGrid");
    }

    @Override
    public String getProviderName() {
        return "SendGrid";
    }
}
