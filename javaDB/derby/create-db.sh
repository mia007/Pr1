#!/bin/sh
#################################################
BASE="$(cd "`dirname ${BASH_SOURCE[0]}`" && pwd)"
#################################################

$BASE/bin/run-script.sh $BASE/../sql/dbcreate-derby.sql
