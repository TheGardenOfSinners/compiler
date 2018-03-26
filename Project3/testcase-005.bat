@echo off
rem : Produce tc-005.infix and tc-005.postfix
cd bin
java Testcase5
cd ..

@echo Running Testcase 005: long testcase in Tail recursion
@echo ==============================================
@echo The input is:
type testcases\tc-005.infix
@echo ----------------------------------------------
cd bin

rem : Run the testcase with input direction
java Postfix2 < ..\testcases\tc-005.infix

rem : Compare the expected output
@echo ----------------------------------------------
@echo The output should be: 
type ..\testcases\tc-005.postfix

cd ..
@echo ==============================================
pause

@echo Running Testcase 005: long testcase in cycling
@echo ==============================================
@echo The input is:
type testcases\tc-005.infix
@echo ----------------------------------------------
cd bin

rem : Run the testcase with input direction
java Postfix3 < ..\testcases\tc-005.infix

rem : Compare the expected output
@echo ----------------------------------------------
@echo The output should be: 
type ..\testcases\tc-005.postfix

cd ..
@echo ==============================================
pause

