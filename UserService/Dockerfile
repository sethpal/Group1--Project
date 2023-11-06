# Use an official Java runtime as a parent image
FROM openjdk:17.0.1-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file into the container
COPY target/Users.jar /app/Users.jar

# Expose the port your application runs on
EXPOSE 8080

# Run your application
CMD ["java", "-jar", "Users.jar"]
