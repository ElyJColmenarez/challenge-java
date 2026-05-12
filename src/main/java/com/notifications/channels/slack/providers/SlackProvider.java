package com.notifications.channels.slack.providers;

import com.notifications.core.NotificationResult;
import com.notifications.core.SlackNotification;

/**
 * Interfaz para proveedores de Slack.
 */
public interface SlackProvider {
    NotificationResult send(SlackNotification notification);
    String getProviderName();
}
