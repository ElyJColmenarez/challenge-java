package com.notifications.channels.push.providers;

import com.notifications.core.Notification;
import com.notifications.core.NotificationResult;
import com.notifications.core.PushNotification;
import lombok.extern.slf4j.Slf4j;

/**
 * Implementación simulada de Firebase FCM.
 */
@Slf4j
public class FirebaseFcmProvider implements PushProvider {
    private final String projectId;

    public FirebaseFcmProvider(String projectId) {
        this.projectId = projectId;
    }

    @Override
    public NotificationResult send(Notification notification) {
        if (!(notification instanceof PushNotification push)) {
            return NotificationResult.failed("Expected PushNotification", null);
        }

        if (projectId == null || projectId.isEmpty()) {
            return NotificationResult.failed("Firebase Project ID is missing", null);
        }

        log.info("[SIMULATED] Sending Push Notification via Firebase - Project: {}, Token: {}, Title: {}, Body: {}", 
                 projectId, push.recipient(), push.title(), push.content());
        
        return NotificationResult.success("Push notification sent successfully via Firebase");
    }

    @Override
    public String getProviderName() {
        return "FirebaseFCM";
    }
}
