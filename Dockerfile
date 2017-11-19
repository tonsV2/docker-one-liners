# https://docs.docker.com/engine/userguide/eng-image/multistage-build/#name-your-build-stages

FROM openjdk:8-jdk-alpine as builder
ADD . /app
WORKDIR /app
RUN ./mvnw -Dspring.profiles.active=test package

FROM openjdk:8-jre-alpine
COPY --from=builder /app/target/docker-one-liner-0.0.1-SNAPSHOT.jar /app.jar
CMD java -Dspring.profiles.active=test -Dserver.port=$PORT -jar /app.jar
EXPOSE 8080
