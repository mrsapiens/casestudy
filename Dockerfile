# Use Maven image with OpenJDK to build the application
FROM maven:3.9.9-ibm-semeru-21-jammy AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven POM file to the container
COPY pom.xml .

# Copy the source code to the container
COPY src ./src

# Build the application with Maven, producing a .jar file
RUN mvn clean package

# Use a lightweight OpenJDK image to run the application
FROM openjdk:21

# Expose port 8080 for the application
EXPOSE 8080

# Copy the built .jar file from the build stage to the current image
COPY --from=build /app/target/*.jar app.jar

# Define the command to run the application
ENTRYPOINT ["java", "-jar", "/app.jar"]