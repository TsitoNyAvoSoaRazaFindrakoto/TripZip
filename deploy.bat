@echo off
setlocal enabledelayedexpansion

REM Load environment variables from .env file
for /f "tokens=1* delims==" %%a in ('.env') do (
	set "%%a=%%b"
)

set "project_name=%PROJECT_NAME%"
set "work_dir=%WORK_DIR%\%PROJECT_NAME%"
set "lib=%work_dir%\lib"
set "bin=%work_dir%\bin"
set "web=%work_dir%\web"
set "web_xml=%work_dir%\config"
set "temp=%work_dir%\.temp"

set "war_file=%work_dir%\%project_name%.war"
set "destination=%TOMCAT_WEBAPPS%"

@REM --- CREATE TEMP FOLDER ---
:: Delete temp folder if it exists
if exist "%temp%" (
    rmdir /s /q "%temp%"
    rmdir /s /q "%bin%"
    del /f /q /a "%war_file%"
)
echo compilation
call compilateur.bat

mkdir %temp%
xcopy "%web%\" "%temp%\" /s /e /h /i /y
xcopy "%bin%\" "%temp%\WEB-INF\classes\" /s /e /h /i /y
xcopy "%web_xml%\" "%temp%\WEB-INF\" /s /e /h /i /y
xcopy "%lib%\" "%temp%\WEB-INF\lib\" /s /e /h /i /y

cd /d %temp%
jar cf "%war_file%" .

del /f /q /a "%destination%%project_name%"
copy /Y "%war_file%" "%destination%"
del /f /q /a %war_file%


