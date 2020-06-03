#!/usr/bin/env bash

if [ -z "$DOCKER_PUBLISH_TO" ]; then
  echo "Please set DOCKER_PUBLISH_TO to your Docker registry account name"
else
    docker build . -t $DOCKER_PUBLISH_TO/java-presence:latest
fi
