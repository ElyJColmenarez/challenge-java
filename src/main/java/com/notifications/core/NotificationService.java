package com.notifications.core;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * Fachada principal para la gestión y envío de notificaciones.
 */
@Slf4j
public class NotificationService {
    private final Map<String, NotificationChannel> channels = new HashMap<>();

    /**
     * Registra un canal en el servicio.
     * @param channel Canal a registrar.
     */
    public void registerChannel(NotificationChannel channel) {
        channels.put(channel.getChannelName().toUpperCase(), channel);
        log.info("Registered channel: {}", channel.getChannelName());
    }

    /**
     * Envía una notificación a través de un canal específico de forma síncrona.
     * @param channelName Nombre del canal (EMAIL, SMS, PUSH, SLACK).
     * @param notification Datos de la notificación.
     * @return Resultado del envío.
     */
    public NotificationResult send(String channelName, Notification notification) {
        NotificationChannel channel = channels.get(channelName.toUpperCase());
        if (channel == null) {
            return NotificationResult.invalid("Channel not registered: " + channelName);
        }
        return channel.send(notification);
    }

    /**
     * Envía una notificación de forma asíncrona.
     * @param channelName Nombre del canal.
     * @param notification Datos de la notificación.
     * @return Future con el resultado.
     */
    public CompletableFuture<NotificationResult> sendAsync(String channelName, Notification notification) {
        NotificationChannel channel = channels.get(channelName.toUpperCase());
        if (channel == null) {
            return CompletableFuture.completedFuture(
                    NotificationResult.invalid("Channel not registered: " + channelName)
            );
        }
        return channel.sendAsync(notification);
    }
}
