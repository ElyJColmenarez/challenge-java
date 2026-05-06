package com.notifications.channels.push;

import lombok.Builder;
import lombok.Getter;

/**
 * Configuración para el canal de Push Notifications.
 */
@Getter
@Builder
public class PushConfig {
    private final String serviceAccountPath;
    private final String projectId;
}
