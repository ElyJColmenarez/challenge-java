package com.notifications.channels.slack;

import com.notifications.channels.slack.providers.SlackProvider;
import com.notifications.channels.slack.providers.SlackWebhookProvider;
import com.notifications.core.Notification;
import com.notifications.core.NotificationChannel;
import com.notifications.core.NotificationResult;
import com.notifications.core.SlackNotification;
import com.notifications.validation.NotificationValidator;
import com.notifications.validation.ValidationResult;

/**
 * Canal de notificación para Slack.
 */
public class SlackChannel implements NotificationChannel {
    private final SlackProvider provider;

    public SlackChannel(SlackConfig config) {
        this.provider = new SlackWebhookProvider(config.getWebhookUrl(), config.getUsername());
    }

    @Override
    public NotificationResult send(Notification notification) {
        if (!(notification instanceof SlackNotification slack)) {
            return NotificationResult.invalid("Expected SlackNotification but received: " + notification.getClass().getSimpleName());
        }

        ValidationResult commonValidation = NotificationValidator.validateCommon(notification);
        if (!commonValidation.valid()) {
            return NotificationResult.invalid(String.join(", ", commonValidation.errors()));
        }

        if (!NotificationValidator.isValidToken(slack.recipient())) {
            return NotificationResult.invalid("Slack channel/recipient cannot be empty");
        }

        return provider.send(notification);
    }

    @Override
    public String getChannelName() {
        return "SLACK";
    }
}
