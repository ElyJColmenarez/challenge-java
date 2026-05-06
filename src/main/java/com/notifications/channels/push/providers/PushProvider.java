package com.notifications.channels.push.providers;

import com.notifications.core.Notification;
import com.notifications.core.NotificationResult;

/**
 * Interfaz para implementaciones específicas de proveedores Push.
 */
public interface PushProvider {
    NotificationResult send(Notification notification);
    String getProviderName();
}
