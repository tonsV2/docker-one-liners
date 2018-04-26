FROM openjdk:8-jdk-alpine as builder
WORKDIR /app
ADD . /app
RUN ./mvnw -DskipTests clean package

FROM openjdk:8-jre-alpine
COPY --from=builder /app/target/docker-one-liner-0.0.1-SNAPSHOT.jar /app/
CMD java -Dserver.port=$PORT -jar /app/*.jar
