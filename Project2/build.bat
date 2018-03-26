@echo off
javac -d .\bin -sourcepath .\src src\agenda\*.java src\*java
javadoc  -private -d .\doc -sourcepath .\src src\*java src\agenda\*java
pause