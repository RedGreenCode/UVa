@echo off
if "%1"=="" GOTO USAGE
md "%1"
copy ..\Template\Main.java "%1"
copy ..\Template\MainTest.java "%1"
copy ..\Template\test.cmd "%1"
copy ..\Template\input-test.txt "%1"
copy ..\Template\input.txt "%1"
copy ..\Template\output-check.txt "%1"
copy ..\Template\go.cmd "%1"
copy ..\Template\goo.cmd "%1"
copy ..\Template\goc.cmd "%1"
copy ..\Template\gor.cmd "%1"
copy ..\Template\Notes.txt "%1\%1.txt"
copy ..\Template\GenerateInput.java "%1"
copy ..\Template\gen.cmd "%1"
cd /d "%1"
uedit64 input.txt Main.java output-check.txt %1.txt
git add .
git commit -m "UVa %1: Initial commit"
GOTO END
:USAGE
ECHO usage: uva [problem_number]
ECHO.
:END
