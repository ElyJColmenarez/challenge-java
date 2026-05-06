package com.notifications.channels.sms;

import com.notifications.channels.sms.providers.SmsProvider;
import com.notifications.channels.sms.providers.TwilioProvider;
import com.notifications.channels.sms.providers.VonageProvider;
import com.notifications.core.Notification;
import com.notifications.core.NotificationChannel;
import com.notifications.core.NotificationResult;
import com.notifications.core.SmsNotification;
import com.notifications.validation.NotificationValidator;
import com.notifications.validation.ValidationResult;

/**
 * Canal de notificación para SMS.
 */
public class SmsChannel implements NotificationChannel {
    private final SmsProvider provider;

    public SmsChannel(SmsConfig config) {
        this.provider = createProvider(config);
    }

    private SmsProvider createProvider(SmsConfig config) {
        if ("VONAGE".equalsIgnoreCase(config.getProviderType())) {
            return new VonageProvider(config.getApiKey(), config.getApiSecret());
        }
        return new TwilioProvider(config.getAccountSid(), config.getAuthToken());
    }

    @Override
    public NotificationResult send(Notification notification) {
        if (!(notification instanceof SmsNotification sms)) {
            return NotificationResult.invalid("Expected SmsNotification but received: " + notification.getClass().getSimpleName());
        }

        ValidationResult commonValidation = NotificationValidator.validateCommon(notification);
        if (!commonValidation.valid()) {
            return NotificationResult.invalid(String.join(", ", commonValidation.errors()));
        }

        if (!NotificationValidator.isValidPhone(sms.recipient())) {
            return NotificationResult.invalid("Invalid phone format (E.164 required): " + sms.recipient());
        }

        return provider.send(notification);
    }

    @Override
    public String getChannelName() {
        return "SMS";
    }
}
