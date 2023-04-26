# Use an official OpenJDK runtime as a parent image
FROM openjdk:8-jdk-alpine

# Set the working directory to /app
WORKDIR /app

# Copy the current directory contents into the container at /app
COPY . /app

# Compile the application
RUN javac GameMenu.java

# Set the default command to run when the container starts
CMD ["java", "Main"]
