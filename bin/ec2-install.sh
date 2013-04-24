#!/bin/bash

echo "Installing java-repl..."

wget 'http://albertlatacz.published.s3.amazonaws.com/javarepl/javarepl.jar' -O /home/ubuntu/javarepl.jar

nohup java -jar /home/ubuntu/javarepl.jar --simple-console --sandboxed --port=8001 2>&1 > javarepl.out