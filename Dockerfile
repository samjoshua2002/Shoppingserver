# Use the official Maven image to build the project with OpenJDK 21
FROM maven:3.8.6-openjdk AS build

# Install OpenJDK 21
RUN apt-get update && apt-get install -y openjdk-21-jdk

# Set the working directory inside the container
WORKDIR /app

# Copy the pom.xml and download the dependencies
COPY pom.xml .

# Download dependencies to cache them
RUN mvn dependency:go-offline

# Copy the source code into the container
COPY src ./src

# Build the project (WAR file)
RUN mvn clean package -DskipTests

# Use the official OpenJDK 21 image to run the application
FROM openjdk:21-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the WAR file from the build stage
COPY --from=build /app/target/demo-0.0.1-SNAPSHOT.war /app/demo.war

# Expose the port that Spring Boot runs on
EXPOSE 8080

# Run the WAR file using the Tomcat embedded in Spring Boot
ENTRYPOINT ["java", "-jar", "/app/demo.war"]
