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
COPY --from=build /workspace/app/build/libs/*.jar app.jar

# Set the entry point for the application
ENTRYPOINT ["java","-jar","/app.jar"]
