#!/bin/bash

# Script to run the Docker container

# Define the container name and image name
CONTAINER_NAME="brokage-firm-container"
IMAGE_NAME="brokage-firm"

# Run the Docker container
echo "Running Docker container: $CONTAINER_NAME"
docker run -d --name $CONTAINER_NAME -p 8080:8080 $IMAGE_NAME

# Check if the container started successfully
if [ $? -eq 0 ]; then
    echo "Docker container $CONTAINER_NAME is running."
else
    echo "Failed to start Docker container $CONTAINER_NAME."
    exit 1
fi
