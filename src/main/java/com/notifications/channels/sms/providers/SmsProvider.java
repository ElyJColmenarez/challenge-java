package com.notifications.channels.sms.providers;

import com.notifications.core.Notification;
import com.notifications.core.NotificationResult;

/**
 * Interfaz para implementaciones específicas de proveedores de SMS.
 */
public interface SmsProvider {
    NotificationResult send(Notification notification);
    String getProviderName();
}
