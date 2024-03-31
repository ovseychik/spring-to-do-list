FROM openjdk:23-jdk-slim
LABEL authors="sergey"

EXPOSE 8080 443

ARG JAR_FILE=target/*.jar

ADD ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","/app.jar"]