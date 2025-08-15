# -------- Build Stage --------
FROM eclipse-temurin:17-jdk-jammy as builder

WORKDIR /app

# Copy Gradle files separately (better caching)
COPY TaxiBookingApp/gradlew .
COPY TaxiBookingApp/gradle gradle
COPY TaxiBookingApp/build.gradle .
COPY TaxiBookingApp/settings.gradle .

# Download dependencies
RUN chmod +x gradlew
RUN ./gradlew dependencies --no-daemon || true

# Copy the full source code
COPY TaxiBookingApp/ .

# Build the JAR (skip tests for faster build)
RUN ./gradlew clean build -x test --no-daemon

# -------- Runtime Stage --------
FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

# Copy the built JAR from the builder stage
COPY --from=builder /app/build/libs/*.jar app.jar

# Expose port 8080
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
