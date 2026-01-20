FROM eclipse-temurin:17-jdk

WORKDIR /app

# Copy Maven wrapper + pom
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Download dependencies (cached layer)
RUN ./mvnw dependency:go-offline

# Copy source
COPY src src

# Build the app
RUN ./mvnw clean package -DskipTests

# Expose port (Render injects PORT)
EXPOSE 8080

# Run the app
CMD ["java", "-jar", "target/*.jar"]