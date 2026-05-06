package com.notifications.channels.email.providers;

import com.notifications.core.EmailNotification;
import com.notifications.core.Notification;
import com.notifications.core.NotificationResult;
import lombok.extern.slf4j.Slf4j;

/**
 * Implementación simulada de Mailgun.
 */
@Slf4j
public class MailgunProvider implements EmailProvider {
    private final String apiKey;
    private final String domain;

    public MailgunProvider(String apiKey, String domain) {
        this.apiKey = apiKey;
        this.domain = domain;
    }

    @Override
    public NotificationResult send(Notification notification) {
        if (!(notification instanceof EmailNotification email)) {
            return NotificationResult.failed("Expected EmailNotification", null);
        }

        if (apiKey == null || domain == null) {
            return NotificationResult.failed("Mailgun credentials (API Key or Domain) are missing", null);
        }

        log.info("[SIMULATED] Sending Email via Mailgun - Domain: {}, To: {}, Subject: {}", 
                 domain, email.recipient(), email.subject());
        
        return NotificationResult.success("Email sent successfully via Mailgun");
    }

    @Override
    public String getProviderName() {
        return "Mailgun";
    }
}
