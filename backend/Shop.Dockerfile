FROM gradle:8.13-jdk17-alpine AS build
WORKDIR /app

# Copy only the files needed for dependency resolution
COPY settings.gradle.kts build.gradle.kts gradle.properties ./
COPY gradle gradle
COPY shared-libs/build.gradle.kts shared-libs/
COPY shop-ear/build.gradle.kts shop-ear/
COPY shop-ejb/build.gradle.kts shop-ejb/
COPY shop-web/build.gradle.kts shop-web/

# Cache dependencies
RUN gradle :shop-ear:dependencies --no-daemon || true

# Copy the rest of the source code
COPY shared-libs shared-libs
COPY shop-ear shop-ear
COPY shop-ejb shop-ejb
COPY shop-web shop-web

# Build the application
RUN gradle :shop-ear:ear --no-daemon

FROM quay.io/wildfly/wildfly:34.0.1.Final-jdk17
COPY --from=build /app/shop-ear/build/libs/shop-app.ear /opt/jboss/wildfly/standalone/deployments/
COPY standalone.xml /opt/jboss/wildfly/standalone/configuration
EXPOSE 8080
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0"]
