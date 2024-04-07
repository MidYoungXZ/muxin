#!/bin/sh
function runShell(){
    DIR=`dirname $0`
    cd $DIR
     java -Dloader.path=libs -Xmx8g -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=7081 -jar `ls | grep *mgr*jar`
    echo $!> tpid
    echo Starting...
    echo please use tailf logs/*.log to check success or not.  
}
if [ ! -f "tpid" ];
then
   runShell
else
    tpid=$(cat "tpid")
    running=$(ps -ef |awk '{print $2}'|grep -w $tpid)
    if [ "$running" ];
    then
        echo "Process(pid:$tpid) is already running."
    else
        runShell
    fi
fi
