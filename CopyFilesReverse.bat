@echo off

REM This script copies php files from the source directory to the destination directory.

REM This is the source directory.
set /P source=Source Directory:

REM This is the destination directory.
set destination=%UserProfile%\Documents\GitHub\DSA\

echo Copying files from %source% to %destination%
xcopy %source%\*.php %destination% /E /Y /I /Q
pause
exit