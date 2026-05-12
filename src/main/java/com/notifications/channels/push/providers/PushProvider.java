package com.notifications.channels.push.providers;

import com.notifications.core.NotificationResult;
import com.notifications.core.PushNotification;

/**
 * Interfaz para proveedores de Push Notifications.
 */
public interface PushProvider {
    NotificationResult send(PushNotification notification);
    String getProviderName();
}
