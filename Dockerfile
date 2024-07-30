FROM openjdk:17-jdk-slim

RUN apt-get update && \
    apt-get install -y maven && \
    apt-get clean

WORKDIR /app

COPY posting-book/pom.xml /app/
RUN mvn dependency:go-offline

COPY posting-book/src /app/src

RUN mvn clean package

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "target/posting-book-0.0.1-SNAPSHOT.jar"]
