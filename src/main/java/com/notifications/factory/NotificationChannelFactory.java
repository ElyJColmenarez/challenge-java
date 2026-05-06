package com.notifications.factory;

import com.notifications.channels.email.EmailChannel;
import com.notifications.channels.email.EmailConfig;
import com.notifications.channels.push.PushChannel;
import com.notifications.channels.push.PushConfig;
import com.notifications.channels.slack.SlackChannel;
import com.notifications.channels.slack.SlackConfig;
import com.notifications.channels.sms.SmsChannel;
import com.notifications.channels.sms.SmsConfig;
import com.notifications.core.NotificationChannel;

/**
 * Fábrica para la creación de canales de notificación.
 */
public class NotificationChannelFactory {

    public static NotificationChannel createEmailChannel(EmailConfig config) {
        return new EmailChannel(config);
    }

    public static NotificationChannel createSmsChannel(SmsConfig config) {
        return new SmsChannel(config);
    }

    public static NotificationChannel createPushChannel(PushConfig config) {
        return new PushChannel(config);
    }

    public static NotificationChannel createSlackChannel(SlackConfig config) {
        return new SlackChannel(config);
    }
}
