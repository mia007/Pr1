@ECHO OFF

CHCP 1251 > NUL

CALL %~dp0setenv.bat

CALL "%DERBY_HOME%\bin\ij.bat" %1
