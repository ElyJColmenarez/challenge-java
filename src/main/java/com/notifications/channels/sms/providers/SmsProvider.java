package com.notifications.channels.sms.providers;

import com.notifications.core.NotificationResult;
import com.notifications.core.SmsNotification;

/**
 * Interfaz para proveedores de SMS.
 */
public interface SmsProvider {
    NotificationResult send(SmsNotification notification);
    String getProviderName();
}
