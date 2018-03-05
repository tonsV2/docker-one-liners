FROM openjdk:8-jdk-alpine as builder
ADD . /app
WORKDIR /app
RUN ./mvnw -Dspring.profiles.active=test -DskipTests clean package

FROM openjdk:8-jre-alpine
COPY --from=builder /app/target/docker-one-liner-0.0.1-SNAPSHOT.jar /app/
CMD java -Dserver.port=$PORT -Dspring.profiles.active=$SPRING_PROFILE -jar /app/*.jar
EXPOSE 8080
