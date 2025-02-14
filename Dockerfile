FROM openjdk:23-jdk-slim as build
EXPOSE 8080
ADD src\main\java\com\example\tone_analyser_api\ToneAnalyserApiApplication.java docker.jar
ENTRYPOINT ["java","-jar","docker.jar"]
