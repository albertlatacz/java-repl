#!/usr/bin/env bash

DOCKER_HUB_REPOSITORY=$1

function tagsFile() {
  REPOSITORY=$1
  SUFFIX=$2
  echo "${REPOSITORY//\//_}.${SUFFIX}.tags"
}

function getTags() {
    REPOSITORY=$1
    TAGS_FILE=$2
    curl -s "https://registry.hub.docker.com/v2/repositories/${REPOSITORY}/tags/" | jq ".results[].name" > "${TAGS_FILE}"
}

function restartContaner() {
    REPOSITORY=$1
    PORTS=$2
    echo "New version of ${REPOSITORY} available. Deploying..."
    docker stop $(docker ps -q --filter ancestor=${REPOSITORY})
    docker rm $(docker ps -q -f status=exited)
    docker rmi $(docker images -q -f dangling=true)
    docker pull ${REPOSITORY}
    docker run --restart=always -d -p ${PORTS} ${REPOSITORY}
}


CURRENT_TAGS_FILE=`tagsFile ${DOCKER_HUB_REPOSITORY} "current"`
NEW_TAGS_FILE=`tagsFile ${DOCKER_HUB_REPOSITORY} "new"`
if [ -f ${CURRENT_TAGS_FILE} ];
then
   getTags ${DOCKER_HUB_REPOSITORY} ${NEW_TAGS_FILE}
   FILES_DIFF=`diff ${CURRENT_TAGS_FILE} ${NEW_TAGS_FILE}`

   if [ "${FILES_DIFF}" != "" ];
   then
      getTags ${DOCKER_HUB_REPOSITORY} ${CURRENT_TAGS_FILE}
      restartContaner ${DOCKER_HUB_REPOSITORY} "8090:8090"
   fi
else
   getTags ${DOCKER_HUB_REPOSITORY} ${CURRENT_TAGS_FILE}
   restartContaner ${DOCKER_HUB_REPOSITORY} "8090:8090"
fi










