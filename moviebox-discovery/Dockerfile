# Build Jar
FROM maven:3-jdk-8 AS builder

# Create a directory named build for sources
RUN mkdir -p /build
# Change working directory
WORKDIR /build
# Copy pom xml
COPY pom.xml /build
# Download all required dependencies into one layer
RUN mvn -B dependency:resolve dependency:resolve-plugins
# Copy source code
COPY src /build/src
# Build application
RUN mvn package -DskipTests

# Build Image
FROM openjdk:14-jdk-alpine3.10

# Expose on port 8080
EXPOSE 8080

# Maintainer
MAINTAINER "Burak Koken"

# Set App home folder
ENV APP_HOME /app
# Possibility to set JVM options
ENV JAVA_OPTS=""

# Create a app directory
RUN mkdir $APP_HOME
# Create folder to save configuration files
RUN mkdir $APP_HOME/config
# Create folder with application logs
RUN mkdir $APP_HOME/log

VOLUME $APP_HOME/log
VOLUME $APP_HOME/config

# Copy executable jar file from the builder
COPY --from=builder /build/target/*.jar moviebox-discovery.jar

# Run
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-jar","/moviebox-discovery.jar"]