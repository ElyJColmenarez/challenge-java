package com.notifications.core;

import java.util.Map;

/**
 * Record público para notificaciones de Email.
 */
public record EmailNotification(
    String recipient, 
    String subject, 
    String content, 
    Map<String, String> extraData
) implements Notification {
    @Override public String getRecipient() { return recipient; }
    @Override public String getContent() { return content; }
}
