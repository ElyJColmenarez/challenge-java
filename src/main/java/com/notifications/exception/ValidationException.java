package com.notifications.exception;

/**
 * Excepción lanzada cuando fallan las validaciones de una notificación.
 */
public class ValidationException extends NotificationException {
    public ValidationException(String message) {
        super(message);
    }
}
