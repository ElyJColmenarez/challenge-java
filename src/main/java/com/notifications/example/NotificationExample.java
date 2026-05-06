package com.notifications.example;

import com.notifications.channels.email.EmailConfig;
import com.notifications.channels.slack.SlackConfig;
import com.notifications.channels.sms.SmsConfig;
import com.notifications.core.*;
import com.notifications.factory.NotificationChannelFactory;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Slf4j
public class NotificationExample {

    public static void main(String[] args) {
        NotificationService service = new NotificationService();

        // 1. Configurar y registrar canales
        service.registerChannel(NotificationChannelFactory.createEmailChannel(
                EmailConfig.builder().apiKey("SG.12345").providerType("SENDGRID").build()
        ));

        service.registerChannel(NotificationChannelFactory.createSmsChannel(
                SmsConfig.builder().accountSid("AC123").authToken("secret").providerType("TWILIO").build()
        ));

        service.registerChannel(NotificationChannelFactory.createSlackChannel(
                SlackConfig.builder().webhookUrl("https://hooks.slack.com/services/T/B").username("Bot").build()
        ));

        // 2. Enviar Email (Usando Record EmailNotification)
        Notification email = new EmailNotification(
                "ely.colmenarez@example.com", 
                "Bienvenido Moderno", 
                "Gracias por unirte. Usamos Java 21!", 
                Map.of("priority", "high")
        );

        NotificationResult emailResult = service.send("EMAIL", email);
        log.info("Email result: {} - {}", emailResult.status(), emailResult.message());

        // 3. Enviar SMS (Usando Record SmsNotification - Asíncrono)
        Notification sms = new SmsNotification("+573001234567", "Tu código es: 998877");

        log.info("Starting async SMS send...");
        CompletableFuture<NotificationResult> smsFuture = service.sendAsync("SMS", sms);
        
        smsFuture.thenAccept(result -> 
            log.info("SMS Async result: {} - {}", result.status(), result.message())
        );

        // 4. Enviar Slack (Usando Record SlackNotification)
        Notification slack = new SlackNotification("#general", "Nueva alerta de sistema", "BotNotificador");

        NotificationResult slackResult = service.send("SLACK", slack);
        log.info("Slack result: {} - {}", slackResult.status(), slackResult.message());

        // Esperar un momento para ver el resultado asíncrono
        try { Thread.sleep(1000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }
}
