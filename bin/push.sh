#!/bin/bash

echo -e "[default]\naccess_key = $AWS_KEY\nsecret_key = $AWS_SECRET" > ~/.s3cfg

s3cmd -c ~/.s3cfg put build/artifacts/javarepl-intellij-dev.build.zip s3://albertlatacz.published/javarepl-intellij-$JAVAREPL_BUILD_NUMBER.zip

rm ~/.s3cfg