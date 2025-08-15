# ---------- Build Stage ----------
FROM eclipse-temurin:17-jdk-jammy AS builder

WORKDIR /app

# Copy Gradle wrapper and build files first (cache dependencies)
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .

# Ensure gradlew is executable
RUN chmod +x gradlew

# Pre-fetch dependencies (cached unless build.gradle changes)
RUN ./gradlew dependencies --no-daemon || true

# Copy the rest of the source code
COPY . .

# Reapply permissions (COPY can reset them)
RUN chmod +x gradlew

# Build the Spring Boot application JAR (skip tests)
RUN ./gradlew clean build -x test --no-daemon


# ---------- Runtime Stage ----------
FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app

# Copy only the JAR from the builder stage
COPY --from=builder /app/build/libs/*.jar app.jar

# Expose the application port
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
