#!/usr/bin/env bash

docker pull albertlatacz/java-repl
docker build -t albertlatacz/java-repl:308 -t albertlatacz/javarepl --build-arg JAVA_REPL_VERSION=308 .
docker login -u="albertlatacz" -p="";
docker push albertlatacz/java-repl


docker stop $(docker ps -q --filter ancestor=albertlatacz/java-repl)
docker start $(docker create -p 8090:8090 albertlatacz/java-repl)
