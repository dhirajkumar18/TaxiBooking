# Stage 1: Build the app
FROM gradle:8.4-jdk17 AS build
WORKDIR /home/gradle/project
COPY --chown=gradle:gradle . .
RUN gradle bootJar --no-daemon

# Stage 2: Run the app
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /home/gradle/project/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
