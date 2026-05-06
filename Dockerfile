# Build Stage
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Run Stage
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /app/target/notifications-lib-1.0.0.jar ./notifications-lib.jar
# Incluimos slf4j-simple en el classpath para ver los logs en consola
ENTRYPOINT ["java", "-cp", "notifications-lib.jar", "com.notifications.example.NotificationExample"]
