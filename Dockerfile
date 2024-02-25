FROM openjdk:17-buster
LABEL authors="Electron3D"

ARG JAR_FILE=target/*.jar

COPY target /

COPY ${JAR_FILE} notepad-1.0.jar

ENTRYPOINT ["java","-jar","/notepad-1.0.jar"]