@echo off

REM This script copies php files from the source directory to the destination directory.
REM It also creates the destination directory if it does not exist.

REM This is the source directory.
set source=%UserProfile%\Documents\GitHub\DSA\

REM This is the destination directory.
set /P destination=Destination Directory:

echo Copying files from %source% to %destination%
xcopy %source%*.php %destination%\ /E /Y /I /Q
pause
exit