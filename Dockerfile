# Use OpenJDK 17 slim base image for building the project
FROM openjdk:17-slim AS build

# Install apt-get and Maven
RUN apt-get update && apt-get install -y apt-transport-https curl && \
    curl -fsSL https://deb.nodesource.com/setup_16.x | bash - && \
    apt-get install -y maven

# Set the working directory
WORKDIR /app

# Copy the pom.xml and source code
COPY pom.xml /app/
COPY src /app/src

# Build the project (WAR file)
RUN mvn clean package -DskipTests

# Use the same OpenJDK 17 image to run the application
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the WAR file from the build stage
COPY --from=build /app/target/demo-0.0.1-SNAPSHOT.war /app/demo.war

# Expose the application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "/app/demo.war"]
