package com.notifications.channels.slack;

import com.notifications.channels.slack.providers.SlackProvider;
import com.notifications.channels.slack.providers.SlackWebhookProvider;
import com.notifications.core.AbstractNotificationChannel;
import com.notifications.core.NotificationResult;
import com.notifications.core.SlackNotification;
import com.notifications.validation.NotificationValidator;

/**
 * Canal de notificación para Slack.
 */
public class SlackChannel extends AbstractNotificationChannel<SlackNotification> {
    private final SlackProvider provider;

    public SlackChannel(SlackConfig config) {
        this.provider = new SlackWebhookProvider(config.getWebhookUrl(), config.getUsername());
    }

    @Override
    protected Class<SlackNotification> getNotificationClass() {
        return SlackNotification.class;
    }

    @Override
    protected void validateSpecific(SlackNotification slack) {
        if (!NotificationValidator.isValidToken(slack.recipient())) {
            throw new IllegalArgumentException("Slack channel/recipient cannot be empty");
        }
    }

    @Override
    protected NotificationResult performSend(SlackNotification notification) {
        return provider.send(notification);
    }

    @Override
    public String getChannelName() {
        return "SLACK";
    }
}
