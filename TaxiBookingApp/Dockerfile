# Use official OpenJDK 17 as build image
FROM eclipse-temurin:17-jdk-jammy as builder

WORKDIR /app

# Copy Gradle files first to leverage caching
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .

# Download dependencies
RUN ./gradlew dependencies --no-daemon || true

# Copy the rest of the project
COPY . .

# Build the JAR
RUN ./gradlew clean build -x test --no-daemon

# Run image
FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
