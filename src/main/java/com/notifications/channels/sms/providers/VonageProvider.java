package com.notifications.channels.sms.providers;

import com.notifications.core.Notification;
import com.notifications.core.NotificationResult;
import com.notifications.core.SmsNotification;
import lombok.extern.slf4j.Slf4j;

/**
 * Implementación simulada de Vonage.
 */
@Slf4j
public class VonageProvider implements SmsProvider {
    private final String apiKey;
    private final String apiSecret;

    public VonageProvider(String apiKey, String apiSecret) {
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
    }

    @Override
    public NotificationResult send(Notification notification) {
        if (!(notification instanceof SmsNotification sms)) {
            return NotificationResult.failed("Expected SmsNotification", null);
        }

        if (apiKey == null || apiSecret == null) {
            return NotificationResult.failed("Vonage credentials missing", null);
        }

        log.info("[SIMULATED] Sending SMS via Vonage - To: {}, Text: {}", 
                 sms.recipient(), sms.content());
        
        return NotificationResult.success("SMS sent successfully via Vonage");
    }

    @Override
    public String getProviderName() {
        return "Vonage";
    }
}
