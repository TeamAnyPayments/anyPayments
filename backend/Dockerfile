FROM openjdk:17-alpine
COPY build/libs/*.jar app.jar
ENV MYSQL_URL \
    MYSQL_PORT \
    MYSQL_USER \
    MYSQL_PASSWORD \
    MYSQL_DATABASE
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=server", "app.jar"]