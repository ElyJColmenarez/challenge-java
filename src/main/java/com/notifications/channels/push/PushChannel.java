package com.notifications.channels.push;

import com.notifications.channels.push.providers.FirebaseFcmProvider;
import com.notifications.channels.push.providers.PushProvider;
import com.notifications.core.Notification;
import com.notifications.core.NotificationChannel;
import com.notifications.core.NotificationResult;
import com.notifications.core.PushNotification;
import com.notifications.validation.NotificationValidator;
import com.notifications.validation.ValidationResult;

/**
 * Canal de notificación para Push Notifications.
 */
public class PushChannel implements NotificationChannel {
    private final PushProvider provider;

    public PushChannel(PushConfig config) {
        this.provider = new FirebaseFcmProvider(config.getProjectId());
    }

    @Override
    public NotificationResult send(Notification notification) {
        if (!(notification instanceof PushNotification push)) {
            return NotificationResult.invalid("Expected PushNotification but received: " + notification.getClass().getSimpleName());
        }

        ValidationResult commonValidation = NotificationValidator.validateCommon(notification);
        if (!commonValidation.valid()) {
            return NotificationResult.invalid(String.join(", ", commonValidation.errors()));
        }

        if (!NotificationValidator.isValidToken(push.recipient())) {
            return NotificationResult.invalid("Device Token cannot be empty");
        }

        return provider.send(notification);
    }

    @Override
    public String getChannelName() {
        return "PUSH";
    }
}
