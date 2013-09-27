#!/bin/bash
# */5 * * * * /home/ubuntu/restart-javarepl.sh

PID=`ps -eo 'tty pid args' | grep 'javarepl.jar' | grep -v grep | tr -s ' ' | cut -f2 -d ' '`

if [ -z "$PID" ]
then
    ./ec2-install.sh
    echo "Started Process at `date`"
else
    echo "Process is already Running with PIS=$PID"
fi
