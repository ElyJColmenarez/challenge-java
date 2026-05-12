package com.notifications.channels.sms;

import com.notifications.channels.sms.providers.SmsProvider;
import com.notifications.channels.sms.providers.TwilioProvider;
import com.notifications.channels.sms.providers.VonageProvider;
import com.notifications.core.AbstractNotificationChannel;
import com.notifications.core.NotificationResult;
import com.notifications.core.SmsNotification;
import com.notifications.validation.NotificationValidator;

/**
 * Canal de notificación para SMS.
 */
public class SmsChannel extends AbstractNotificationChannel<SmsNotification> {
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
    protected Class<SmsNotification> getNotificationClass() {
        return SmsNotification.class;
    }

    @Override
    protected void validateSpecific(SmsNotification sms) {
        if (!NotificationValidator.isValidPhone(sms.recipient())) {
            throw new IllegalArgumentException("Invalid phone format (E.164 required): " + sms.recipient());
        }
    }

    @Override
    protected NotificationResult performSend(SmsNotification notification) {
        return provider.send(notification);
    }

    @Override
    public String getChannelName() {
        return "SMS";
    }
}
