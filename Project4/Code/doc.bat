@echo off
cd src
javadoc -private -encoding UTF-8 -author -version -d ..\doc -classpath ..\bin parser\*.java
cd ..
pause
@echo on
