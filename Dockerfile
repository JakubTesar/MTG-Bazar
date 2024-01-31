# Use the official Gradle image to build the application
FROM gradle:8.1.1-jdk17 AS build
WORKDIR /workspace/app

COPY . /workspace/app
RUN gradle wrapper

# Build the application
RUN ./gradlew clean build

# Create a smaller runtime image
FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
ARG DEPENDENCY=/workspace/app/build/dependency

# Set the entry point for the application
ENTRYPOINT ["java", "-cp", "app:app/lib/*", "MTGAY.Application"]
