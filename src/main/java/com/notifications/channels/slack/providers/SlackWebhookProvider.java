package com.notifications.channels.slack.providers;

import com.notifications.core.NotificationResult;
import com.notifications.core.SlackNotification;
import lombok.extern.slf4j.Slf4j;

/**
 * Proveedor de Slack usando Webhooks (Simulado).
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
    public NotificationResult send(SlackNotification slack) {
        if (webhookUrl == null || webhookUrl.isEmpty()) {
            return NotificationResult.failed("Slack Webhook URL is missing", null);
        }

        // El record SlackNotification puede traer su propio username, si no, usamos el del proveedor
        String sender = (slack.username() != null) ? slack.username() : this.username;

        log.info("[SIMULATED] Sending Slack Message via Webhook - Channel: {}, Sender: {}, Message: {}", 
                 slack.recipient(), sender, slack.content());
        
        return NotificationResult.success("Slack message sent successfully");
    }

    @Override
    public String getProviderName() {
        return "Slack Webhook";
    }
}
