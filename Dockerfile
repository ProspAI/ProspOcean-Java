FROM gradle:7.4.2-jdk17 AS builder
WORKDIR /home/gradle/project
COPY . .
RUN gradle clean build --no-daemon

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=builder /home/gradle/project/build/libs/ProspOcean-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
