package com.notifications.channels.slack;

import lombok.Builder;
import lombok.Getter;

/**
 * Configuración para el canal de Slack.
 */
@Getter
@Builder
public class SlackConfig {
    private final String webhookUrl;
    private final String channelName;
    private final String username;
}
