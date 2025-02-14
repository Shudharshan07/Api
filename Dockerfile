# Use an official OpenJDK runtime as a parent image for the build stage
FROM openjdk:23-jdk-slim as build

# Set the working directory inside the container
WORKDIR /app

# Copy the Gradle wrapper and build files (like build.gradle) to the container
COPY gradle /app/gradle
COPY gradlew /app/
COPY build.gradle /app/

# Ensure gradlew is executable
RUN chmod +x gradlew

# Copy the source code of the project
COPY src /app/src

# Run Gradle build to package the application as a JAR
RUN ./gradlew build --no-daemon

# Use a smaller image for the runtime environment
FROM openjdk:23-jdk-slim

# Set the working directory for the runtime stage
WORKDIR /app

# Copy the JAR file from the build stage to the runtime stage
COPY --from=build /app/build/libs/ToneAnalyserApiApplication.jar /app/ToneAnalyserApiApplication.jar

# Expose the port your app runs on (default 8080 for Spring Boot)
EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java", "-jar", "/app/ToneAnalyserApiApplication.jar"]
