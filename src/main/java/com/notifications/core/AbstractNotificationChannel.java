package com.notifications.core;

import com.notifications.validation.NotificationValidator;
import com.notifications.validation.ValidationResult;

/**
 * Clase base para canales de notificación que implementa la lógica común.
 * Utiliza el patrón Template Method para centralizar la validación y delegación.
 * 
 * @param <T> Tipo específico de Notification que maneja este canal.
 */
public abstract class AbstractNotificationChannel<T extends Notification> implements NotificationChannel {

    protected abstract Class<T> getNotificationClass();
    
    protected abstract NotificationResult performSend(T notification);

    /**
     * Hook opcional para validaciones específicas del canal.
     */
    protected void validateSpecific(T notification) throws IllegalArgumentException {
        // Por defecto no hace nada, las subclases pueden sobrescribirlo
    }

    @Override
    public final NotificationResult send(Notification notification) {
        // 1. Validar Tipo
        if (!getNotificationClass().isInstance(notification)) {
            return NotificationResult.invalid("Expected " + getNotificationClass().getSimpleName() + 
                                            " but received: " + notification.getClass().getSimpleName());
        }

        @SuppressWarnings("unchecked")
        T specificNotification = (T) notification;

        // 2. Validar Común
        ValidationResult commonValidation = NotificationValidator.validateCommon(specificNotification);
        if (!commonValidation.valid()) {
            return NotificationResult.invalid(String.join(", ", commonValidation.errors()));
        }

        // 3. Validar Específico (Template Method Hook)
        try {
            validateSpecific(specificNotification);
        } catch (IllegalArgumentException e) {
            return NotificationResult.invalid(e.getMessage());
        }

        // 4. Ejecutar Envío
        return performSend(specificNotification);
    }
}
