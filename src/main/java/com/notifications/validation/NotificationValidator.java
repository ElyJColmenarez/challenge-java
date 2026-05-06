package com.notifications.validation;

import com.notifications.core.Notification;
import java.util.regex.Pattern;

/**
 * Validador para asegurar la integridad de los datos de notificación.
 */
public class NotificationValidator {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
    private static final Pattern E164_PATTERN = Pattern.compile("^\\+[1-9]\\d{1,14}$");

    /**
     * Valida una dirección de email.
     * @param email Dirección de email.
     * @return true si es válida.
     */
    public static boolean isValidEmail(String email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }

    /**
     * Valida un número de teléfono en formato E.164.
     * @param phone Número de teléfono.
     * @return true si es válido.
     */
    public static boolean isValidPhone(String phone) {
        return phone != null && E164_PATTERN.matcher(phone).matches();
    }

    /**
     * Valida que un token no esté vacío.
     * @param token Token de dispositivo o Slack Webhook.
     * @return true si es válido.
     */
    public static boolean isValidToken(String token) {
        return token != null && !token.trim().isEmpty();
    }

    /**
     * Valida los datos comunes de una notificación.
     * @param notification Notificación a validar.
     * @return Resultado de la validación.
     */
    public static ValidationResult validateCommon(Notification notification) {
        if (notification == null) {
            return ValidationResult.failure("Notification cannot be null");
        }
        if (notification.getContent() == null || notification.getContent().trim().isEmpty()) {
            return ValidationResult.failure("Content cannot be empty");
        }
        if (notification.getRecipient() == null || notification.getRecipient().trim().isEmpty()) {
            return ValidationResult.failure("Recipient cannot be empty");
        }
        return ValidationResult.success();
    }
}
