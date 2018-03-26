@echo off
cd src
javac -encoding utf-8 -d ..\bin -classpath ..\bin parser\*.java scanner\*.java symbols\*.java
cd ..
pause
@echo on
