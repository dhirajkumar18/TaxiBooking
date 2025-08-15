# ---------- Build Stage ----------
FROM eclipse-temurin:17-jdk-jammy as builder

WORKDIR /app

# Copy Gradle wrapper and build files first (cache optimization)
COPY TaxiBookingApp/gradlew .
COPY TaxiBookingApp/gradle gradle
COPY TaxiBookingApp/build.gradle .
COPY TaxiBookingApp/settings.gradle .

# Ensure gradlew is executable
RUN chmod +x gradlew

# Pre-fetch dependencies (for faster builds)
RUN ./gradlew dependencies --no-daemon || true

# Copy the rest of the source
COPY TaxiBookingApp/ .

# Reapply permissions after final copy
RUN chmod +x gradlew

# Build the JAR (skip tests for faster builds)
RUN ./gradlew clean build -x test --no-daemon


# ---------- Runtime Stage ----------
FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app

# Copy only the built JAR from the builder stage
COPY --from=builder /app/build/libs/*.jar app.jar

# Expose the application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
