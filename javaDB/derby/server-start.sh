#!/bin/sh
#################################################
BASE="$(cd "`dirname ${BASH_SOURCE[0]}`" && pwd)"
#################################################

[ ! -d $BASE/db ] && mkdir $BASE/db

pushd $BASE/db > /dev/null
$BASE/bin/server.sh start &
popd > /dev/null
