package com.notifications.channels.email.providers;

import com.notifications.core.EmailNotification;
import com.notifications.core.NotificationResult;
import lombok.extern.slf4j.Slf4j;

/**
 * Proveedor de Email usando Mailgun (Simulado).
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
    public NotificationResult send(EmailNotification email) {
        if (apiKey == null || apiKey.isEmpty() || domain == null || domain.isEmpty()) {
            return NotificationResult.failed("Mailgun credentials missing", null);
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
