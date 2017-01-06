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

function extractName() {
    REPOSITORY=$1
    PROJECT=${REPOSITORY##*/}
    echo "${PROJECT/[-]/_}"
}

function restartContainer() {
    REPOSITORY=$1
    DOCKER_OPTIONS="$2"
    NAME=`extractName ${REPOSITORY}`
    echo "New version of ${REPOSITORY} available. Deploying..."
    docker stop $(docker ps -q --filter name=${NAME})
    docker rm $(docker ps -q --filter status=exited --filter name=${NAME})
    docker rmi $(docker images -q --filter dangling=true --filter name=${NAME})
    docker pull ${REPOSITORY}
    eval "docker run --restart=always -d --name=${NAME} ${DOCKER_OPTIONS} ${REPOSITORY}"
}

function checkForNewVersion() {
    REPOSITORY=$1
    DOCKER_OPTIONS="$2"
    CURRENT_TAGS_FILE=`tagsFile ${REPOSITORY} "current"`
    NEW_TAGS_FILE=`tagsFile ${REPOSITORY} "new"`
    if [ -f ${CURRENT_TAGS_FILE} ];
    then
       getTags ${REPOSITORY} ${NEW_TAGS_FILE}
       FILES_DIFF=`diff ${CURRENT_TAGS_FILE} ${NEW_TAGS_FILE}`

       if [ "${FILES_DIFF}" != "" ];
       then
          getTags ${REPOSITORY} ${CURRENT_TAGS_FILE}
          restartContainer "${REPOSITORY}" "${DOCKER_OPTIONS}"
       fi
    else
       getTags ${REPOSITORY} ${CURRENT_TAGS_FILE}
       restartContainer "${REPOSITORY}" "${DOCKER_OPTIONS}"
    fi
}


DOCKER_HUB_REPOSITORY=$1
DOCKER_OPTIONS="$2"

checkForNewVersion ${DOCKER_HUB_REPOSITORY} "${DOCKER_OPTIONS}"




