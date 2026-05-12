package com.notifications.channels.push.providers;

import com.notifications.core.NotificationResult;
import com.notifications.core.PushNotification;
import lombok.extern.slf4j.Slf4j;

/**
 * Proveedor de Push Notifications usando Firebase Cloud Messaging (Simulado).
 */
@Slf4j
public class FirebaseFcmProvider implements PushProvider {
    private final String projectId;

    public FirebaseFcmProvider(String projectId) {
        this.projectId = projectId;
    }

    @Override
    public NotificationResult send(PushNotification push) {
        if (projectId == null || projectId.isEmpty()) {
            return NotificationResult.failed("Firebase Project ID is missing", null);
        }

        log.info("[SIMULATED] Sending Push Notification via Firebase - Project: {}, Token: {}, Title: {}", 
                 projectId, push.recipient(), push.title());
        
        return NotificationResult.success("Push Notification sent successfully via Firebase");
    }

    @Override
    public String getProviderName() {
        return "Firebase FCM";
    }
}
