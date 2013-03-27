#!/bin/bash

echo -e "[default]\naccess_key = $AWS_KEY\nsecret_key = $AWS_SECRET" > ~/.s3cfg

s3cmd -c ~/.s3cfg put build/artifacts/javarepl-intellij-$JAVAREPL_BUILD_NUMBER.zip s3://albertlatacz.published/javarepl/intellij-plugin/javarepl-intellij-$JAVAREPL_BUILD_NUMBER.zip
s3cmd -c ~/.s3cfg put build/artifacts/javarepl-intellij-$JAVAREPL_BUILD_NUMBER.zip s3://albertlatacz.published/javarepl/intellij-plugin/javarepl-intellij-latest.zip

s3cmd -c ~/.s3cfg put build/artifacts/javarepl-$JAVAREPL_BUILD_NUMBER.jar s3://albertlatacz.published/javarepl/javarepl-$JAVAREPL_BUILD_NUMBER.jar
s3cmd -c ~/.s3cfg put build/artifacts/javarepl-$JAVAREPL_BUILD_NUMBER.jar s3://albertlatacz.published/javarepl/javarepl-latest.jar

rm ~/.s3cfg