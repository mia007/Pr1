@ECHO OFF

CHCP 1251 > NUL

CALL %~dp0setenv.bat

IF "%1" == "start" CALL "%DERBY_HOME%\bin\startNetworkServer.bat"

IF "%1" == "stop" CALL "%DERBY_HOME%\bin\stopNetworkServer.bat"

IF "%1" == "status" (
	netstat -ano | findstr -r "1527.*LISTENING" >NUL 2>&1 ^
		&& ECHO Derby is running... ^
		|| ECHO Derby is stopped
)

IF "%1" == "" ECHO "Usage: %~nx0 (start|stop|status)"

EXIT
