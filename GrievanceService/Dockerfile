# Use the official maven/Java 17 image to create a build artifact.
# https://hub.docker.com/_/maven
FROM maven:3.8.4-openjdk-17-slim AS build

ENV EUREKA_SERVER_URL=http://172.17.240.1:8761/eureka
# Set the working directory in the image to /app
WORKDIR /app

# Copy the pom.xml file to our app directory
COPY pom.xml .

# This command downloads the project dependencies and stores them in a local repository.
# It's a separate step so the dependencies will be cached unless changes to pom.xml are made.
RUN mvn dependency:go-offline -B

# Copy the rest of the code
COPY src /app/src

# Build the project
RUN mvn package -DskipTests

# Use the official openjdk image for a lean production stage of our multi-stage build.
# https://hub.docker.com/_/openjdk
# Use the version as appropriate to your application
FROM openjdk:17-jdk-alpine

# Set the working directory in the image to /app
WORKDIR /app

# Copy the jar file from the build stage
COPY --from=build /app/target/*.jar ./app.jar

# Run the application
CMD ["java", "-jar", "./app.jar"]