package com.notifications.validation;

import java.util.List;

/**
 * Resultado de una validación de notificación.
 */
public record ValidationResult(
    boolean valid,
    List<String> errors
) {
    public static ValidationResult success() {
        return new ValidationResult(true, List.of());
    }

    public static ValidationResult failure(String error) {
        return new ValidationResult(false, List.of(error));
    }

    public static ValidationResult failure(List<String> errors) {
        return new ValidationResult(false, List.copyOf(errors));
    }
}
