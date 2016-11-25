#!/usr/bin/env bash

docker pull albertlatacz/java-repl
docker build -t albertlatacz/java-repl:308 -t albertlatacz/javarepl --build-arg JAVA_REPL_VERSION=308 .
docker login -u="albertlatacz" -p="";
docker push albertlatacz/java-repl


docker stop $(docker ps -q --filter ancestor=albertlatacz/java-repl)
docker pull albertlatacz/java-repl
docker start $(docker create -p 8090:8090 albertlatacz/java-repl)


docker images --filter dangling=true #lists all images that are dangling and has no pointer to it
docker rmi `docker images --filter dangling=true -q` #Removes all those images.

#Kill exited containers
sudo docker ps -a | grep Exit | cut -d ' ' -f 1 | xargs sudo docker rm


docker ps -a # all containers


# clean up all exited and dangling
docker rm $(docker ps -q -f status=exited)
docker rmi $(docker images -q -f dangling=true)

docker rmi $(docker images -q albertlatacz/java-repl)

docker run --restart=always -d -p 8090:8090 albertlatacz/java-repl

curl 'https://registry.hub.docker.com/v2/repositories/albertlatacz/java-repl/tags/' | jq '.results[].name' | wc -w #count tags