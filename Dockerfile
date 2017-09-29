FROM openjdk:8-jre-alpine
ADD target/docker-one-liner-0.0.1-SNAPSHOT.jar /app.jar
CMD java -jar /app.jar
EXPOSE 8080
