#!/bin/bash

address=${1}
user=${2}
key_path=${3}

component=${4}
task=${5}

ssh -i ${key_path} ${user}@${address} < ./${component}/${task}.sh

exit
