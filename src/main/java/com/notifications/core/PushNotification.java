package com.notifications.core;

import java.util.Map;

/**
 * Record público para notificaciones Push.
 */
public record PushNotification(
    String recipient, 
    String title, 
    String content, 
    Map<String, String> extraData
) implements Notification {
    @Override public String getRecipient() { return recipient; }
    @Override public String getContent() { return content; }
}
