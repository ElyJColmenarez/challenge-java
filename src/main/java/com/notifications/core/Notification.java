package com.notifications.core;

/**
 * Interfaz sellada para representar los diferentes tipos de notificaciones.
 * Al estar en el mismo paquete, puede permitir records definidos en archivos separados.
 */
public sealed interface Notification 
    permits EmailNotification, SmsNotification, PushNotification, SlackNotification {
    
    String getRecipient();
    String getContent();
}
