package com.notifications.core;

/**
 * Representa el estado final del intento de envío de una notificación.
 */
public enum NotificationStatus {
    /**
     * El envío fue exitoso.
     */
    SUCCESS,

    /**
     * El envío falló debido a un error técnico o del proveedor.
     */
    FAILED,

    /**
     * La notificación fue rechazada por fallas de validación.
     */
    INVALID
}
