# 🚀 Notifications Library (Java 21+)

Librería de notificaciones unificada, agnóstica a frameworks y altamente extensible. Diseñada bajo principios **SOLID** y aprovechando las últimas características de **Java 21** (Sealed Interfaces, Records, Pattern Matching).

---

## 👤 Guía de Usuario

*Para desarrolladores que desean integrar el envío de notificaciones en sus aplicaciones.*

### 1. Instalación

Agrega la dependencia a tu `pom.xml`:

```xml
<dependency>
    <groupId>com.notifications</groupId>
    <artifactId>notifications-lib</artifactId>
    <version>1.0.0</version>
</dependency>
```

### 2. Configuración Inicial

La librería utiliza un **NotificationService** central para orquestar los envíos. Debes registrar los canales que deseas utilizar:

```java
// 1. Inicializar el servicio
NotificationService service = new NotificationService();

// 2. Registrar canales (Ejemplo: Email y SMS)
service.registerChannel(NotificationChannelFactory.createEmailChannel(
    EmailConfig.builder().apiKey("SG.xxx").providerType("SENDGRID").build()
));

service.registerChannel(NotificationChannelFactory.createSmsChannel(
    SmsConfig.builder().accountSid("ACxxx").authToken("xxx").providerType("TWILIO").build()
));
```

### 3. Envío de Notificaciones

Utiliza los **Records** específicos para cada tipo de mensaje. El envío puede ser síncrono o asíncrono.

```java
// Crear la notificación
Notification email = new EmailNotification("user@example.com", "Hola", "Contenido", null);

// Envío Síncrono
NotificationResult result = service.send("EMAIL", email);

// Envío Asíncrono (No bloqueante)
service.sendAsync("EMAIL", email).thenAccept(res -> {
    if (res.isSuccess()) System.out.println("Enviado!");
});
```

---

## 🛠️ Guía de Desarrollador

*Para ingenieros que desean extender la librería o entender su arquitectura interna.*

### Arquitectura Core

La librería se basa en una arquitectura de **Estrategia Concéntrica**:
1.**NotificationService (Facade):** Orquestador principal.

2.**NotificationChannel (Strategy):** Interfaz que define un canal (Email, SMS, etc).
3.**Providers (Strategy Interno):** Cada canal delega el envío real a un proveedor específico.

### Cómo Extender la Librería

#### A. Agregar un Nuevo Canal (Ejemplo: WhatsApp)

1.**Define el modelo:** Crea un nuevo `record WhatsAppNotification` e inclúyelo en los `permits` de la interfaz `Notification`.

2.**Crea el Canal:** Crea `WhatsAppChannel` implementando `NotificationChannel`.

3.**Configura el Canal:** Crea `WhatsAppConfig` para sus credenciales.

4.**Actualiza la Factory:** Añade el método de creación en `NotificationChannelFactory`.

#### B. Agregar un Nuevo Proveedor a un Canal Existente

Si deseas agregar un proveedor de SMS diferente a Twilio (ejemplo: Amazon SNS):

1.Implementa la interfaz `SmsProvider` en una nueva clase `AmazonSnsSmsProvider`.

2.Actualiza la lógica de `SmsChannel.createProvider()` para que reconozca el nuevo tipo de proveedor desde la configuración.

### Manejo de Errores

No lanzamos excepciones de flujo. Usamos el patrón **Result Type** mediante el record `NotificationResult`. Esto permite que el cliente maneje fallos de validación o red de forma declarativa.

---

## 🐳 Ejecución y Pruebas

### Docker Compose (Recomendado)

Para compilar y ejecutar el demo funcional:

```bash
docker-compose up --build
```

### Ejecución Local

```bash
mvn clean package
java -jar target/notifications-lib-1.0.0.jar
```

---

## 🔒 Seguridad y Mejores Prácticas

* **Inmutabilidad:** Todos los modelos de datos son `records` para garantizar que la notificación no sea alterada durante el proceso de envío.

* **Agnosticismo:** Prohibido el uso de anotaciones de frameworks (`@Component`, `@Service`). La configuración es 100% via código Java.

* **Validación:** Se incluye validación automática de Email (RFC) y Teléfonos (E.164) antes de cualquier intento de envío.

---
