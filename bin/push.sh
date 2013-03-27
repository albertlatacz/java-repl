#!/bin/bash

echo -e "[default]\naccess_key = $AWS_KEY\nsecret_key = $AWS_SECRET" > ~/.s3cfg

s3cmd put build/artifacts/javarepl-intellij-dev.build.zip s3://albertlatacz.published/javarepl-intellij-dev.build.zip

rm ~/.s3cfg