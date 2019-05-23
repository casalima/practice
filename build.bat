@echo off

cls 
rmdir /S /Q classes
mkdir classes
del WordFinder.jar

javac -d classes src/com/sandro/orion/*
jar cfm WordFinder.jar manifest -C classes .

echo.
echo ************************************************************************
echo    WordFinder.jar file was generated
echo.
echo    1) Execute the following command to run program:
echo       java -jar WordFinder.jar
echo ************************************************************************

