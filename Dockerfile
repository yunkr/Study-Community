# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the current directory contents into the container at /app
COPY build/libs/Study-Community-0.0.1-SNAPSHOT.jar /app/Study-Community-0.0.1-SNAPSHOT.jar

# Make port 8080 available to the world outside this container
EXPOSE 8080

ENV spring.datasource.url=jdbc:mysql://localhost:3306/studycommunity
ENV spring.datasource.username=root
ENV spring.datasource.password=1234

# Run the jar file
ENTRYPOINT ["java", "-jar", "Study-Community-0.0.1-SNAPSHOT.jar"]