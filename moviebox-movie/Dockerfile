# Build Jar
FROM  maven:3.6.0-jdk-13-alpine AS builder

# Create a directory named build for sources
RUN mkdir -p /build
# Change working directory
WORKDIR /build
# Copy pom xml
COPY pom.xml /build
# Download all required dependencies into one layer
RUN mvn -B dependency:go-offline dependency:resolve-plugins
# Copy source code
COPY src /build/src
# Build application
RUN mvn package -DskipTests

# Build Image
#FROM adoptopenjdk/openjdk13-openj9
FROM adoptopenjdk/openjdk13-openj9:jre-13.0.2_8_openj9-0.18.0-alpine

# Maintainer
MAINTAINER "Burak Koken"

VOLUME /tmp

# Possibility to set JVM options
#ENV JAVA_OPTS="-Xshareclasses:cacheDir=/cache"

# Copy executable jar file from the builder
#COPY --from=builder /build/target/*.jar moviebox-movie.jar

ARG DEPENDENCY=target/dependency
COPY --from=builder /build/${DEPENDENCY}/BOOT-INF/lib         /app/lib
COPY --from=builder /build/${DEPENDENCY}/META-INF             /app/META-INF
COPY --from=builder /build/${DEPENDENCY}/BOOT-INF/classes     /app

#COPY --from=builder /build/target/*.jar /app/moviebox-movie.jar

# Run
ENTRYPOINT ["java","-Xscmx80M","-Xshareclasses:cacheDir=/cache","-Xquickstart","-Djava.security.egd=file:/dev/./urandom","-cp", "app:app/lib/*","org.codnect.moviebox.MovieboxApplication"]
#ENTRYPOINT ["java", "-Xscmx80M", "-Xshareclasses:cacheDir=/cache", "-Xquickstart", "-Djava.security.egd=file:/dev/./urandom","-jar","/app/moviebox-movie.jar"]
#RUN /bin/bash -c 'java -Xscmx80M -Xshareclasses:cacheDir=/cache -Xquickstart -jar /app/moviebox-movie.jar';
