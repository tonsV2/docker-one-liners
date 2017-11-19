# Http
## Find tags by rank
http localhost:8081/api/tagsByRank

## Search tags by partial tag
http localhost:8081/api/tagsStartingWith?name=java

## Search oneliners by all tags
http localhost:8081/api/findByAllTags <<< '["db", "sql"]'

## Post new tag
http localhost:8081/api/tags <<< '{"name": "name", "description": "description"}'

# Docker
## Test in docker
docker run -it --rm -v "$PWD":/app:Z -v "$HOME"/.m2:/root/.m2:Z -w /app openjdk:8-jdk-alpine ./mvnw clean test -Dspring.profiles.active=test

## Run with in-memory database
docker run -it --rm -v "$PWD":/app:Z -v "$HOME"/.m2:/root/.m2:Z -w /app maven:3-jdk-8-alpine mvn spring-boot:run -Dspring.profiles.active=test

## Run with in-memory database using maven wrapper
docker run -it --rm -v "$PWD":/app:Z -v "$HOME"/.m2:/root/.m2:Z -w /app openjdk:8-jdk-alpine ./mvnw spring-boot:run -Dspring.profiles.active=test

## Gradle
docker run -it --rm -v "$PWD":/app -w /app gradle:alpine gradle tasks



docker run -it --rm -v "$PWD":/app -v "$HOME"/.m2:/root/.m2 -w /app maven:3.5-jdk-8-alpine mvn package -DskipTests 


./mvnw clean package -Dspring.profiles.active=test && docker build -t oneliner.nuc:5000/oneliner . && docker push oneliner.nuc:5000/oneliner
