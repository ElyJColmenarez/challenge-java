package com.notifications.channels.slack.providers;

import com.notifications.core.Notification;
import com.notifications.core.NotificationResult;
import com.notifications.core.SlackNotification;
import lombok.extern.slf4j.Slf4j;

/**
 * Implementación simulada de Slack Webhook.
 */
@Slf4j
public class SlackWebhookProvider implements SlackProvider {
    private final String webhookUrl;
    private final String username;

    public SlackWebhookProvider(String webhookUrl, String username) {
        this.webhookUrl = webhookUrl;
        this.username = username;
    }

    @Override
    public NotificationResult send(Notification notification) {
        if (!(notification instanceof SlackNotification slack)) {
            return NotificationResult.failed("Expected SlackNotification", null);
        }

        if (webhookUrl == null || webhookUrl.isEmpty()) {
            return NotificationResult.failed("Slack Webhook URL is missing", null);
        }

        // Si la notificación no trae username, usamos el configurado en el proveedor
        String effectiveUser = (slack.username() != null && !slack.username().isEmpty()) 
                                ? slack.username() 
                                : this.username;

        log.info("[SIMULATED] Sending Slack Message - Webhook: {}, User: {}, Channel: {}, Text: {}", 
                 webhookUrl, effectiveUser, slack.recipient(), slack.content());
        
        return NotificationResult.success("Slack message sent successfully");
    }

    @Override
    public String getProviderName() {
        return "SlackWebhook";
    }
}
