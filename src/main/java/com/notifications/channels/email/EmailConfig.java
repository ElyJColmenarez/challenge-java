package com.notifications.channels.email;

import lombok.Builder;
import lombok.Getter;

/**
 * Configuración para el canal de Email.
 */
@Getter
@Builder
public class EmailConfig {
    private final String apiKey;
    private final String fromAddress;
    private final String domain; // Requerido por Mailgun
    private final String providerType; // "SENDGRID" o "MAILGUN"
}
