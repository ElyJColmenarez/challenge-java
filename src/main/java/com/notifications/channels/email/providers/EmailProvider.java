package com.notifications.channels.email.providers;

import com.notifications.core.EmailNotification;
import com.notifications.core.NotificationResult;

/**
 * Interfaz para implementaciones específicas de proveedores de Email.
 */
public interface EmailProvider {
    /**
     * Realiza el envío simulado a través del proveedor.
     * @param notification Notificación de email a enviar.
     * @return Resultado del envío.
     */
    NotificationResult send(EmailNotification notification);

    /**
     * @return Nombre del proveedor.
     */
    String getProviderName();
}
