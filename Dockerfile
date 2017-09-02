FROM openjdk:8-jdk-alpine
ADD target/docker-one-liner-0.0.1-SNAPSHOT.jar /app.jar
#CMD ./mvnw spring-boot:run -Dspring.profiles.active=docker
CMD CMD java -jar -Dspring.profiles.active=docker /app.jar"]
EXPOSE 8080
