#!/usr/bin/env bash

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

function restartContainer() {
    REPOSITORY=$1
    COMMAND="$2"
    echo "New version of ${REPOSITORY} available. Deploying..."
    docker stop $(docker ps -q --filter ancestor=${REPOSITORY})
    docker rm $(docker ps -q -f status=exited)
    docker rmi $(docker images -q -f dangling=true)
    docker pull ${REPOSITORY}
    eval "${COMMAND} ${REPOSITORY}"
}

function checkForNewVersion() {
    REPOSITORY=$1
    COMMAND="$2"
    CURRENT_TAGS_FILE=`tagsFile ${REPOSITORY} "current"`
    NEW_TAGS_FILE=`tagsFile ${REPOSITORY} "new"`
    if [ -f ${CURRENT_TAGS_FILE} ];
    then
       getTags ${REPOSITORY} ${NEW_TAGS_FILE}
       FILES_DIFF=`diff ${CURRENT_TAGS_FILE} ${NEW_TAGS_FILE}`

       if [ "${FILES_DIFF}" != "" ];
       then
          getTags ${REPOSITORY} ${CURRENT_TAGS_FILE}
          restartContainer "${REPOSITORY}" "${COMMAND}"
       fi
    else
       getTags ${REPOSITORY} ${CURRENT_TAGS_FILE}
       restartContainer "${REPOSITORY}" "${COMMAND}"
    fi
}


DOCKER_HUB_REPOSITORY=$1
COMMAND="$2"

checkForNewVersion ${DOCKER_HUB_REPOSITORY} "${COMMAND}"




