package com.notifications.channels.push;

import com.notifications.channels.push.providers.FirebaseFcmProvider;
import com.notifications.channels.push.providers.PushProvider;
import com.notifications.core.AbstractNotificationChannel;
import com.notifications.core.NotificationResult;
import com.notifications.core.PushNotification;
import com.notifications.validation.NotificationValidator;

/**
 * Canal de notificación para Push Notifications.
 */
public class PushChannel extends AbstractNotificationChannel<PushNotification> {
    private final PushProvider provider;

    public PushChannel(PushConfig config) {
        this.provider = new FirebaseFcmProvider(config.getProjectId());
    }

    @Override
    protected Class<PushNotification> getNotificationClass() {
        return PushNotification.class;
    }

    @Override
    protected void validateSpecific(PushNotification push) {
        if (!NotificationValidator.isValidToken(push.recipient())) {
            throw new IllegalArgumentException("Device Token cannot be empty");
        }
    }

    @Override
    protected NotificationResult performSend(PushNotification notification) {
        return provider.send(notification);
    }

    @Override
    public String getChannelName() {
        return "PUSH";
    }
}
