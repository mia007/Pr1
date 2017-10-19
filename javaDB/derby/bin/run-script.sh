#!/bin/sh
#################################################
BASE="$(cd "`dirname ${BASH_SOURCE[0]}`" && pwd)"
. $BASE/setenv.sh
#################################################

#################################################
# WARNING!
# If an encoding of your scripts is a default encoding of your OS
# you have to comment the following two lines
SQL_SCRIPT="`dirname $1`/.`basename $1`.from_Cp1251"
iconv -fCp1251 $1 > $SQL_SCRIPT
#################################################

pushd $BASE/../db > /dev/null
$DERBY_HOME/bin/ij ${SQL_SCRIPT:-$1}
popd > /dev/null