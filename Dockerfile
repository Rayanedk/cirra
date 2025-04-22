# Étape 1 : image de base avec Java
FROM openjdk:17-jdk-slim

# Étape 2 : dossier de travail
WORKDIR /app

# Étape 3 : copier le .jar
COPY target/test-0.0.1-SNAPSHOT.jar app.jar

# Étape 4 : exposer le port
EXPOSE 8080

# Étape 5 : lancer l’application
ENTRYPOINT ["java", "-jar", "app.jar"]
