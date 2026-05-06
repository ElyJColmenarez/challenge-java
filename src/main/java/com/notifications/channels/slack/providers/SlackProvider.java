package com.notifications.channels.slack.providers;

import com.notifications.core.Notification;
import com.notifications.core.NotificationResult;

/**
 * Interfaz para implementaciones específicas de proveedores de Slack.
 */
public interface SlackProvider {
    NotificationResult send(Notification notification);
    String getProviderName();
}
