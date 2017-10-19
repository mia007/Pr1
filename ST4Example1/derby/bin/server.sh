#!/bin/sh
#################################################
BASE="$(cd "`dirname ${BASH_SOURCE[0]}`" && pwd)"
. $BASE/setenv.sh
#################################################

case $1 in
	start|stop)
		$DERBY_HOME/bin/${1}NetworkServer
	;;
	status)
		if netstat -an | grep ':1527\s' > /dev/null; then
			echo "Derby is running..." 
		else
			echo "Derby is stopped"
		fi
	;;
	*)
		echo "Usage `basename $0` (start|stop|status)"
	;;
esac
