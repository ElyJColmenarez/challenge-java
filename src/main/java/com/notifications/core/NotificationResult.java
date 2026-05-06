package com.notifications.core;

/**
 * Encapsula el resultado de una operación de notificación.
 * Utiliza Record para inmutabilidad por defecto.
 */
public record NotificationResult(
    NotificationStatus status,
    String message,
    Throwable error
) {
    public static NotificationResult success(String message) {
        return new NotificationResult(NotificationStatus.SUCCESS, message, null);
    }

    public static NotificationResult failed(String message, Throwable error) {
        return new NotificationResult(NotificationStatus.FAILED, message, error);
    }

    public static NotificationResult invalid(String message) {
        return new NotificationResult(NotificationStatus.INVALID, message, null);
    }

    public boolean isSuccess() {
        return status == NotificationStatus.SUCCESS;
    }
}
