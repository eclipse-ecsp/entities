@echo off
SETLOCAL EnableDelayedExpansion

echo 'Cleaning previously created files in parent module'
del .\DEPENDENCIES
del .\maven.deps

set dependencies=%cd%\maven.deps

echo 'Checking dependencies'

CALL mvn clean verify dependency:list -D maven.test.skip=true -D maven.javadoc.skip=true -D appendOutput=true -D outputFile=%dependencies%
echo 'Maven dependency extraction and file creation complete. %dependencies% created.'

echo 'Running dash tool for all dependencies license compliance'
CALL java -jar ./eclipse-dash/dash.jar maven.deps -summary DEPENDENCIES

echo DEPENDENCIES file created.