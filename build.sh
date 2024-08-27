#!/bin/bash

# Script to build the Docker image

# Define the image name
IMAGE_NAME="brokage-firm"

# Build the Docker image
echo "Building Docker image: $IMAGE_NAME"
docker build -t $IMAGE_NAME .

# Check if the build was successful
if [ $? -eq 0 ]; then
    echo "Docker image $IMAGE_NAME built successfully."
else
    echo "Failed to build Docker image $IMAGE_NAME."
    exit 1
fi