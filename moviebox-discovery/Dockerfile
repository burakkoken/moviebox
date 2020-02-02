# Build Jar
FROM maven:3.6-jdk-13 AS builder

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
FROM adoptopenjdk/openjdk13-openj9

# Maintainer
MAINTAINER "Burak Koken"

# Possibility to set JVM options
#ENV JAVA_OPTS=""

# Copy executable jar file from the builder
COPY --from=builder /build/target/*.jar /app/moviebox-discovery.jar

# Run
ENTRYPOINT ["java", "-Xscmx80M", "-Xshareclasses:cacheDir=/cache", "-Xquickstart", "-Djava.security.egd=file:/dev/./urandom","-jar","/app/moviebox-discovery.jar"]