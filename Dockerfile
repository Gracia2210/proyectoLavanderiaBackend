# Usar una imagen base de Gradle 7.2 con JDK 11 para compilar el proyecto
FROM gradle:7.2-jdk11 as builder

# Crear un directorio de trabajo
WORKDIR /app

# Copiar los archivos del proyecto Gradle
COPY build.gradle settings.gradle /app/
COPY gradlew /app/gradlew
COPY gradle /app/gradle

# Copiar el código fuente del proyecto
COPY src /app/src

# Construir la aplicación
RUN ./gradlew build --no-daemon

# Fase de construcción de la imagen final usando OpenJDK JRE
FROM openjdk:11-jre-slim

# Establecer el directorio de trabajo en el contenedor
WORKDIR /app

# Copiar el JAR generado desde el contenedor builder
COPY --from=builder /app/build/libs/*.jar app.jar

# Exponer el puerto 8080
EXPOSE 8080

# Configuración para que funcione en Google Cloud Run
ENV PORT 8080

# Comando para ejecutar la aplicación Spring Boot
CMD ["java", "-jar", "app.jar"]
