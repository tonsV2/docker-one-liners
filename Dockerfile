FROM openjdk:8-jdk-alpine AS builder
WORKDIR /app
ADD . /app
RUN ./mvnw -DskipTests clean package

FROM openjdk:8-jre-alpine
COPY --from=builder /app/target/docker-one-liner-0.0.1-SNAPSHOT.jar /app/
CMD java -Dserver.port=$PORT -jar /app/*.jar
HEALTHCHECK --interval=5s --timeout=3s CMD wget --quiet --tries=1 --spider http://localhost:8080/api/tagsByRank || exit 1
