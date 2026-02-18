#
#FROM openjdk:17-jdk-slim
#
#WORKDIR /app
#
#COPY target/invoicing-app-0.0.1-SNAPSHOT.jar invoicing-app-0.0.1-SNAPSHOT.jar
#COPY src/main/resources/data.sql /app/resources/data.sql
#
## Run the application
#ENTRYPOINT ["java", "-jar", "invoicing-app-0.0.1-SNAPSHOT.jar"]


# Build stage - Using Maven with JDK 17
FROM maven:3.9-eclipse-temurin-17 AS builder

WORKDIR /app

# Copy pom.xml first for dependency caching
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy application source
COPY src src

# Build with Maven
RUN mvn clean package -DskipTests

# Runtime stage - Using JRE 17 to match build stage
FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

# Install only curl (tiny footprint)
RUN apt-get update && \
    apt-get install -y --no-install-recommends curl=7.81.* && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# Copy built application
COPY --from=builder /app/target/invoicing-app-0.0.1-SNAPSHOT.jar app.jar

# Extreme JVM memory optimization (256MB total)
ENV JAVA_OPTS="-XX:MaxRAM=256m \
               -XX:MaxRAMPercentage=70 \
               -XX:+UseSerialGC \
               -XX:+TieredCompilation \
               -XX:TieredStopAtLevel=1 \
               -XX:+ExitOnOutOfMemoryError \
               -XX:+UseContainerSupport"

# Lightweight health check
HEALTHCHECK --interval=30s --timeout=3s \
  CMD curl -f http://localhost:10001/actuator/health || exit 1

# Optimized entrypoint
ENTRYPOINT ["sh", "-c", "exec java $JAVA_OPTS -jar app.jar"]

EXPOSE 10001