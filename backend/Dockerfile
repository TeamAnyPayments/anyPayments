FROM openjdk:17-alpine
COPY build/libs/*.jar app.jar
ENV MYSQL_PASSWORD ${MYSQL_PASSWORD}
ENTRYPOINT ["java", "-jar", "app.jar"]