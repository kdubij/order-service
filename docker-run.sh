#!/usr/bin/env bash

mvn clean package docker:build -Dspring.profiles.active=docker

docker-compose up
