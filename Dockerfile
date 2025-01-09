# Use the official OpenJDK 21 image to build the project
FROM openjdk:21 AS build

# Install Maven
RUN apt-get update && apt-get install -y maven

# Set the working directory
WORKDIR /app

# Copy the pom.xml and source code
COPY pom.xml /app/
COPY src /app/src

# Build the project (WAR file)
RUN mvn clean package -DskipTests

# Use the official OpenJDK 21 image to run the application
FROM openjdk:21-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the WAR file from the build stage
COPY --from=build /app/target/demo-0.0.1-SNAPSHOT.war /app/demo.war

# Expose the application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "/app/demo.war"]
