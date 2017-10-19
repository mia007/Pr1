            ===================================================
            Running Apache Derby (Java DB) distributed with JDK
            ===================================================


If you use *nix OS - run the following command from the current directory:
	 find . -name "*.sh" -exec chmod u+x {} +

===============================================================================

1. First you have to set JAVA_HOME environment variable if it has not been set yet.

You can set it inside of a 'setenv' script.

On Windows:
	bin/setenv.bat

On *nix:
	bin/setenv.sh

===============================================================================

2. To start Derby run 'server-start' script.

On Windows:
	server-start.bat

On *nix:
	server-start.sh

If Derby cannot start, maybe it is necessary to add the following permission to a file
JAVA_HOME/jre/lib/security/java.policy:

grant {
	permission java.net.SocketPermission "localhost:1527", "listen, resolve";
};

===============================================================================

3. To create database with a script of ../sql/dbcreate-derby.sql run 'create-db' script.

On Windows:
	create-db.bat

On *nix:
	create-db.sh

The newly created database has home directory located inside of 'db' directory.

===============================================================================

4. To stop Derby run 'server-stop' script.

On Windows:
	server-stop.bat

On *nix:
	server-stop.sh

===============================================================================

5. If you want check a status of Derby run 'server-status' script.

On Windows:
	server-status.bat

On *nix:
	server-status.sh

===============================================================================

6. If you want to remove a database run 'remove-db' script (note, you must stop server first!).

On Windows:
	remove-db.bat

On *nix:
	remove-db.sh

WARNING!!! REMOVE database before you commit your project to SVN!!!
