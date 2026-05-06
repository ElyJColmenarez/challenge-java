package com.notifications.core;

/**
 * Record público para notificaciones de Slack.
 */
public record SlackNotification(
    String recipient, 
    String content,
    String username
) implements Notification {
    @Override public String getRecipient() { return recipient; }
    @Override public String getContent() { return content; }
}
