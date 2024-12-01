# Etapa 1: Construcción del artefacto
FROM maven:3.9-eclipse-temurin-17 AS builder

# Establecer directorio de trabajo en el contenedor
WORKDIR /app

# Copiar los archivos de configuración de Maven y el código fuente al contenedor
COPY pom.xml .
COPY src ./src

# Construir el artefacto usando Maven
RUN mvn clean package -DskipTests

# Etapa 2: Imagen ligera para ejecución
FROM eclipse-temurin:17-jdk-alpine

# Crear un directorio para la aplicación
WORKDIR /app

# Copiar el JAR generado desde la etapa de construcción
COPY --from=builder /app/target/*.jar app.jar

# Exponer el puerto de la aplicación
EXPOSE 8080

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "app.jar"]
