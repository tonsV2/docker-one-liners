FROM openjdk:8-jdk-alpine AS builder
WORKDIR /app
ADD . /app
RUN ./mvnw -DskipTests clean package

FROM openjdk:8-jre-alpine
WORKDIR /app
COPY --from=builder /src/target/*-0.0.1-SNAPSHOT.jar .
CMD exec java -Dserver.port=$PORT -jar /app/*.jar
HEALTHCHECK --interval=10s --timeout=3s CMD wget --quiet --tries=1 --spider http://localhost:8080/api/tagsByRank || exit 1
