# Usar JDK 17 como base
FROM openjdk:17-jdk-slim

# Establecer directorio de trabajo
WORKDIR /app

# Copiar el JAR generado por Maven
COPY target/reto-persona-1.0-SNAPSHOT.jar app.jar

# Exponer el puerto del microservicio
EXPOSE 8081

# Ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
