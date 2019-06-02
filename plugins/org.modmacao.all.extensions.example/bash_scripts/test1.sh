#!/bin/bash

address=${1}
user=${2}
key_path=${3}

component=${3}
task=${4}

case "$1" in
	DEPLOY) echo "do deploy";;
	CONFIGURE) echo "do configure";;
	START) echo "do start";;
	STOP) echo "do stop";;
	UNDEPLOY) echo "do undeploy";;
	*) echo "action ${1} is invalid!";;
esac
