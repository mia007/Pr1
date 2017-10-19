# JAVA_HOME env variable must point to JDK 7+ (not JRE!)

## for Linux something like:
#export JAVA_HOME=/usr/java/default

# for MacOS something like:
#export JAVA_HOME=JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_65.jdk/Contents/Home

# don't change the following variable
export DERBY_HOME=$JAVA_HOME/db
