Add alias front end? https://github.com/isamert/addalias


# Http
## Find tags by rank
http :8080/tagsByRank

## Search tags by partial tag
http :8080/tagsStartingWith?name=java

## Search oneliners by all tags
http :8080/findByAllTags <<< '["db", "sql"]'

## Post new tag
http :8080/tags <<< '{"name": "name", "description": "description"}'

# Docker
## Test
docker run -it --rm -v "$PWD":/app:Z -v "$HOME"/.m2:/root/.m2:Z -w /app openjdk:8-jdk-alpine ./mvnw clean test -Dspring.profiles.active=test

## Run with in-memory database
docker run -it --rm -v "$PWD":/app:Z -v "$HOME"/.m2:/root/.m2:Z -w /app maven:3-jdk-8-alpine mvn spring-boot:run -Dspring.profiles.active=test

## Run with in-memory database using maven wrapper
docker run -it --rm -v "$PWD":/app:Z -v "$HOME"/.m2:/root/.m2:Z -w /app openjdk:8-jdk-alpine ./mvnw spring-boot:run -Dspring.profiles.active=test

## Build and run (multi stage)
docker build -t docker-oneliner .

docker run -p 8080:8080 -e DATASOURCE_URL=jdbc:postgresql://192.168.0.17:5432/oneliner -e SPRING_PROFILES_ACTIVE=docker docker-oneliner

## Gradle
docker run -it --rm -v "$PWD":/app -w /app gradle:alpine gradle tasks



docker run -it --rm -v "$PWD":/app -v "$HOME"/.m2:/root/.m2 -w /app maven:3.5-jdk-8-alpine mvn package -DskipTests 


./mvnw clean package -Dspring.profiles.active=test && docker build -t oneliner.nuc:5000/oneliner . && docker push oneliner.nuc:5000/oneliner
