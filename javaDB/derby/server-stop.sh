#!/bin/sh
#################################################
BASE="$(cd "`dirname ${BASH_SOURCE[0]}`" && pwd)"
#################################################

$BASE/bin/server.sh stop
