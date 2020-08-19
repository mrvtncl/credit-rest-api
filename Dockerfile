FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG WAR_FILE
COPY ${WAR_FILE} creditapp.war
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/creditapp.war"]