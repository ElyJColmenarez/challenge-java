package com.notifications.channels.sms;

import lombok.Builder;
import lombok.Getter;

/**
 * Configuración para el canal de SMS.
 */
@Getter
@Builder
public class SmsConfig {
    private final String accountSid; // Twilio
    private final String authToken; // Twilio
    private final String apiKey; // Vonage
    private final String apiSecret; // Vonage
    private final String fromNumber;
    private final String providerType; // "TWILIO" o "VONAGE"
}
