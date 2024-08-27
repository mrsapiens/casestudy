#!/bin/bash

# Script to remove Docker containers and images

# Define the container name and image name
CONTAINER_NAME="brokage-firm-container"
IMAGE_NAME="brokage-firm"

# Stop and remove the running container
echo "Stopping and removing Docker container: $CONTAINER_NAME"
docker stop $CONTAINER_NAME
docker rm $CONTAINER_NAME

# Check if the container was removed successfully
if [ $? -eq 0 ]; then
    echo "Docker container $CONTAINER_NAME removed successfully."
else
    echo "Failed to remove Docker container $CONTAINER_NAME. It might not exist."
fi

# Remove the Docker image
echo "Removing Docker image: $IMAGE_NAME"
docker rmi $IMAGE_NAME

# Check if the image was removed successfully
if [ $? -eq 0 ]; then
    echo "Docker image $IMAGE_NAME removed successfully."
else
    echo "Failed to remove Docker image $IMAGE_NAME. It might not exist or be in use."
fi
