# Use an official OpenJDK runtime as a parent image
FROM openjdk:21
# Set the working directory to /app
WORKDIR /app
# Copy the current directory contents into the container at /app
COPY target/bookmarker-api.jar /app
# Make port 8080 available to the world outside this container
EXPOSE 8080
# Run springboot-mysql-minikube.jar when the container launches
CMD ["java", "-jar", "bookmarker-api.jar"]