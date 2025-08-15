FROM eclipse-temurin:17-jdk-jammy as builder

WORKDIR /app

# Copy Gradle wrapper and build files
COPY TaxiBookingApp/gradlew .
COPY TaxiBookingApp/gradle gradle
COPY TaxiBookingApp/build.gradle .
COPY TaxiBookingApp/settings.gradle .

# Ensure gradlew is executable
RUN chmod +x gradlew

# Pre-fetch dependencies
RUN ./gradlew dependencies --no-daemon || true

# Copy the rest of the source
COPY TaxiBookingApp/ .

# IMPORTANT: Reapply permissions after final copy
RUN chmod +x gradlew

# Build the JAR
RUN ./gradlew clean build -x test --no-daemon
