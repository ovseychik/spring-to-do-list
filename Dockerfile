FROM openjdk:23-jdk-slim
LABEL authors="sergey"

EXPOSE 8080 443

ARG JAR_FILE=target/*.jar
ENV SERVER_PATH_FULLCHAIN=/etc/letsencrypt/live/notes.ovseychik.me/fullchain.pem
ENV SERVER_PATH_PRIVATE_KEY=/etc/letsencrypt/live/notes.ovseychik.me/privkey.pem

ADD ${JAR_FILE} app.jar

COPY ${SERVER_PATH_FULLCHAIN} /app/
COPY ${SERVER_PATH_PRIVATE_KEY} /app/

ENTRYPOINT ["java","-jar","/app.jar"]