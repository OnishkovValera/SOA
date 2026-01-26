FROM gradle:8.13-jdk17-alpine AS build
WORKDIR /app

# Copy only the files needed for dependency resolution
COPY settings.gradle.kts build.gradle.kts gradle.properties ./
COPY gradle gradle
COPY shared-libs/build.gradle.kts shared-libs/
COPY vehicle-service/build.gradle.kts vehicle-service/

# Cache dependencies
RUN gradle :vehicle-service:dependencies --no-daemon || true

# Copy the rest of the source code
COPY shared-libs shared-libs
COPY vehicle-service vehicle-service

# Build the application
RUN gradle :vehicle-service:bootJar --no-daemon

FROM eclipse-temurin:17-jre-alpine AS run
WORKDIR /app
COPY --from=build /app/vehicle-service/build/libs/vehicle-service*.jar app.jar
COPY standalone.xml /opt/jboss/wildfly/standalone/configuration

EXPOSE 8080
CMD ["java", "-jar", "app.jar"]




