
FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/invoicing-app-0.0.1-SNAPSHOT.jar invoicing-app-0.0.1-SNAPSHOT.jar
COPY src/main/resources/data.sql /app/resources/data.sql

# Run the application
ENTRYPOINT ["java", "-jar", "invoicing-app-0.0.1-SNAPSHOT.jar"]