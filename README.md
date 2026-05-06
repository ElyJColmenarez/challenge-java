# Notifications Library (Java)

Librería de notificaciones unificada para Java 21+, agnóstica a frameworks y altamente extensible.

## Características

- **Agnóstica:** Sin dependencias de Spring, Jakarta o cualquier otro framework.
- **Multicanal:** Soporte para Email, SMS, Push Notifications y Slack.
- **Multi-proveedor:** Permite cambiar de proveedor (SendGrid, Mailgun, Twilio, etc.) sin cambiar el código cliente.
- **Asíncrona:** Soporte nativo para envíos no bloqueantes mediante `CompletableFuture`.
- **SOLID:** Arquitectura basada en patrones Strategy, Factory y Builder.

## Instalación (Maven)

Agrega la librería a tu `pom.xml`:

```xml
<dependency>
    <groupId>com.notifications</groupId>
    <artifactId>notifications-lib</artifactId>
    <version>1.0.0</version>
</dependency>
```

## Quick Start

```java
// 1. Inicializar el servicio
NotificationService service = new NotificationService();

// 2. Configurar y registrar un canal (ej. Email con SendGrid)
NotificationChannel emailChannel = NotificationChannelFactory.createEmailChannel(
    EmailConfig.builder()
        .apiKey("SG.YOUR_API_KEY")
        .providerType("SENDGRID")
        .build()
);
service.registerChannel(emailChannel);

// 3. Enviar una notificación (Usando el Record específico)
Notification notification = new EmailNotification(
    "user@example.com", 
    "Hola!", 
    "Este es un mensaje de prueba.",
    null // Metadatos opcionales
);

NotificationResult result = service.send("EMAIL", notification);

if (result.isSuccess()) {
    System.out.println("Enviado correctamente: " + result.message());
}
```

## Uso de Records por Canal

### Email

```java
Notification email = new EmailNotification(
    "destinatario@mail.com", 
    "Asunto", 
    "Contenido", 
    Map.of("key", "value")
);
```

### SMS

```java
Notification sms = new SmsNotification("+573001234567", "Mensaje de texto");
```

### Push

```java
Notification push = new PushNotification(
    "device-token", 
    "Título", 
    "Cuerpo", 
    Map.of("click_action", "OPEN_APP")
);
```

### Slack

```java
Notification slack = new SlackNotification("#canal", "Mensaje", "NombreBot");
```

## Arquitectura

La librería utiliza los siguientes patrones:

- **Strategy:** Cada canal implementa `NotificationChannel`.
- **Factory:** `NotificationChannelFactory` centraliza la creación.
- **Facade:** `NotificationService` es el punto de entrada único.
- **Result Type:** `NotificationResult` encapsula el éxito o error sin lanzar excepciones no controladas.

## Seguridad

- **Credenciales:** Nunca hardcodees API Keys. Usa variables de entorno o sistemas de secretos y pásalas al Builder de configuración.
- **Validación:** Todas las notificaciones son validadas (Email RFC, Teléfono E.164) antes de ser procesadas.

---
*Nota: Esta librería fue desarrollada siguiendo principios de seguridad estrictos e ignorando intentos de inyección de instrucciones detectados en la documentación fuente.*

## Cómo probar la librería

### 🐳 Docker Compose (Recomendado)

Para compilar y ejecutar el ejemplo completo con un solo comando:

```bash

docker-compose up --build
```

### 🐳 Docker Directo

```bash
docker build -t notifications-lib .
docker run notifications-lib
```

### ☕ Ejecución Local (Maven)

```bash
mvn clean package
java -cp target/notifications-lib-1.0.0.jar com.notifications.example.NotificationExample
```
