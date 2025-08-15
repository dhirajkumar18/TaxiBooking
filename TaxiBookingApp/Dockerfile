# Use official OpenJDK 17 as build image
FROM eclipse-temurin:17-jdk-jammy as builder

WORKDIR /app

# Copy Gradle files from TaxiBookingApp folder
COPY TaxiBookingApp/gradlew .
COPY TaxiBookingApp/gradle gradle
COPY TaxiBookingApp/build.gradle .
COPY TaxiBookingApp/settings.gradle .

# Download dependencies
RUN ./gradlew dependencies --no-daemon || true

# Copy the rest of the project
COPY TaxiBookingApp/ .

# Build the JAR
RUN ./gradlew clean build -x test --no-daemon

# Run image
FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
