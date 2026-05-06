package com.notifications.core;

import java.util.concurrent.CompletableFuture;

/**
 * Interfaz Strategy para los canales de notificación.
 */
public interface NotificationChannel {
    
    /**
     * Envía una notificación de forma síncrona.
     * @param notification Datos de la notificación.
     * @return Resultado del envío.
     */
    NotificationResult send(Notification notification);

    /**
     * Envía una notificación de forma asíncrona.
     * @param notification Datos de la notificación.
     * @return Future con el resultado del envío.
     */
    default CompletableFuture<NotificationResult> sendAsync(Notification notification) {
        return CompletableFuture.supplyAsync(() -> send(notification));
    }

    /**
     * Devuelve el nombre identificador del canal (ej. EMAIL, SMS).
     * @return Nombre del canal.
     */
    String getChannelName();
}
