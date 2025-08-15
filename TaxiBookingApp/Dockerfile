# ---------- Build Stage ----------
FROM eclipse-temurin:17-jdk-jammy AS builder

WORKDIR /app

# Copy Gradle wrapper and build files first (for caching)
COPY TaxiBookingApp/gradlew .
COPY TaxiBookingApp/gradle gradle
COPY TaxiBookingApp/build.gradle .
COPY TaxiBookingApp/settings.gradle .

# Give execute permission to gradlew
RUN chmod +x gradlew

# Pre-download dependencies (so they can be cached)
RUN ./gradlew dependencies --no-daemon || true

# Copy the rest of the source code
COPY TaxiBookingApp/ .

# Build the application (skip tests for faster build)
RUN ./gradlew clean build -x test --no-daemon

# ---------- Run Stage ----------
FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

# Copy the built JAR from builder stage
COPY --from=builder /app/build/libs/*.jar app.jar

# Expose the application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
