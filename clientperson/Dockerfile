FROM openjdk:17-jdk-slim

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo JAR al contenedor
COPY target/clientperson-0.0.1.jar clientperson.jar

# Configura el punto de entrada para ejecutar la aplicación cuando se inicie el contenedor
ENTRYPOINT ["java", "-jar", "clientperson.jar"]