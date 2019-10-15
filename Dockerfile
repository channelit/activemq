FROM maven:3.6.2-jdk-11 AS maven-build
USER root
RUN mkdir /app
ADD . /app/.
RUN cd /app && mvn install

FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=maven-build /app/target/*.jar /app

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "activemq-1.0-SNAPSHOT.jar"]