# Étape 1 : construire le .jar avec Maven
FROM maven:3.9.4-eclipse-temurin-17 as builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Étape 2 : image finale
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=builder /app/target/test-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
