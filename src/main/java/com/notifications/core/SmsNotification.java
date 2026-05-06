package com.notifications.core;

/**
 * Record público para notificaciones de SMS.
 */
public record SmsNotification(
    String recipient, 
    String content
) implements Notification {
    @Override public String getRecipient() { return recipient; }
    @Override public String getContent() { return content; }
}
