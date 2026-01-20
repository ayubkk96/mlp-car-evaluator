FROM eclipse-temurin:21-jdk
WORKDIR /app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

RUN sed -i 's/\r$//' mvnw && chmod +x mvnw
RUN ./mvnw -e dependency:go-offline

COPY src src
RUN ./mvnw -e clean package -DskipTests

# ✅ copy the jar to a stable filename
RUN cp target/*.jar app.jar

EXPOSE 8080
CMD ["java", "-jar", "app.jar"]