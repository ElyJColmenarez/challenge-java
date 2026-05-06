package com.notifications.exception;

/**
 * Excepción base para la librería de notificaciones.
 */
public class NotificationException extends RuntimeException {
    public NotificationException(String message) {
        super(message);
    }

    public NotificationException(String message, Throwable cause) {
        super(message, cause);
    }
}
