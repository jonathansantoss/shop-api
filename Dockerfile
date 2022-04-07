FROM openjdk:11-jdk

VOLUME /tmp
ARG JAR_FILE=target/shop-api-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app.jar"]