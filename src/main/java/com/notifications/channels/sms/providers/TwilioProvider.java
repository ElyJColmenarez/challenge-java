package com.notifications.channels.sms.providers;

import com.notifications.core.NotificationResult;
import com.notifications.core.SmsNotification;
import lombok.extern.slf4j.Slf4j;

/**
 * Proveedor de SMS usando Twilio (Simulado).
 */
@Slf4j
public class TwilioProvider implements SmsProvider {
    private final String accountSid;
    private final String authToken;

    public TwilioProvider(String accountSid, String authToken) {
        this.accountSid = accountSid;
        this.authToken = authToken;
    }

    @Override
    public NotificationResult send(SmsNotification sms) {
        if (accountSid == null || authToken == null) {
            return NotificationResult.failed("Twilio credentials missing", null);
        }

        log.info("[SIMULATED] Sending SMS via Twilio - To: {}, Body: {}", 
                 sms.recipient(), sms.content());
        
        return NotificationResult.success("SMS sent successfully via Twilio");
    }

    @Override
    public String getProviderName() {
        return "Twilio";
    }
}
