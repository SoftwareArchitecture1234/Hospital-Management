# Use an official OpenJDK runtime as a parent image
FROM openjdk:21-jdk-slim

# Set the working directory in the container
WORKDIR /app

# First copy only the POM file to cache dependencies
COPY pom.xml .
# You might want to add a step here to download dependencies if using Maven

# Then copy the source files
COPY src ./src

# Build the application (if you want to build inside the container)
# RUN mvn package

# Alternatively, build the jar docker build -t authservice .locally first, then copy it
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

# Expose the port your application runs on
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]