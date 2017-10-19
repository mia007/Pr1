@ECHO OFF

CALL %~dp0bin/run-script.bat %~dp0..\sql\dbcreate-derby.sql

PAUSE