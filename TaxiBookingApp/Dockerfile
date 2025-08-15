# 1️⃣ Use a Gradle image with JDK 17 for building
FROM gradle:8.7.0-jdk17 AS builder

# Set working directory inside the container
WORKDIR /app

# Copy only Gradle config first (for caching dependencies)
COPY build.gradle settings.gradle gradle.properties ./
COPY gradle ./gradle

# Download dependencies
RUN gradle dependencies --no-daemon || return 0

# Now copy the rest of the application
COPY . .

# Build the application without tests
RUN gradle clean build -x test --no-daemon

# 2️⃣ Use a smaller JDK image for running the app
FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

# Copy built jar from builder stage
COPY --from=builder /app/build/libs/*.jar app.jar

# Expose the port your app runs on
EXPOSE 8080

# Run the Spring Boot app
ENTRYPOINT ["java", "-jar", "app.jar"]
