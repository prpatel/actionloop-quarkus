#!/bin/bash
echo "Script Called with"
echo "$@"
# send an ack if required
if test -n "$__OW_WAIT_FOR_ACK"
  then
    echo "__OW_WAIT_FOR_ACK in ENV"
    echo '{"ok":true}' >&3
  else
    echo "__OW_WAIT_FOR_ACK not in ENV"
fi
# read input forever line by line
while read line
do
   # log in stdout
   echo "msg="hello patrick"
   # produce the result - note the fd3
   echo '{"hello": "patrick"}' >&3
done
