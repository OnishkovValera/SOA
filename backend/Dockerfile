FROM gradle:8.13-alpine AS build
WORKDIR /app

COPY settings.gradle.kts settings.gradle.kts
COPY build.gradle.kts build.gradle.kts
COPY gradle gradle
COPY gradlew gradlew
RUN gradle build -x test --no-daemon || true

COPY . .
RUN gradle :vehicle-service:bootJar --no-deamon

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build vehicle-service/build/libs/vehicle-service*.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]

