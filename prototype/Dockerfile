# Use Java 17 (matches Spring Boot 3)
FROM eclipse-temurin:17-jdk

# Set working directory
WORKDIR /app

# Copy Maven wrapper and pom
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Download dependencies (cached layer)
RUN ./mvnw dependency:go-offline

# Copy source
COPY src src

# Build the app
RUN ./mvnw clean package -DskipTests

# Expose port (Render sets PORT env var)
EXPOSE 8080