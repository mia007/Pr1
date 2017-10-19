:::::::::::::::::::::::::::::::::::::::
:: must point to JDK 7+ (not JRE!)
:: SET JAVA_HOME=C:\java\jdk1.7.0_51

:::::::::::::::::::::::::::::::::::::::
:: don't change the follogin expressions
SET DERBY_HOME=%JAVA_HOME%\db
SET DERBY_OPTS=-Dderby.system.home=%~dp0..\db
