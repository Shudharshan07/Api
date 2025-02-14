FROM openjdk:23-jdk-slim as build


WORKDIR /app


COPY gradle /app/gradle
COPY gradlew /app/
COPY build.gradle /app/


COPY src /app/src


RUN ./gradlew build --no-daemon


FROM openjdk:23-jdk-slim


WORKDIR /app


COPY --from=build /app/build/libs/ToneAnalyserApiApplication.jar /app/ToneAnalyserApiApplication.jar


EXPOSE 8080


ENTRYPOINT ["java", "-jar", "/app/ToneAnalyserApiApplication.jar"]
