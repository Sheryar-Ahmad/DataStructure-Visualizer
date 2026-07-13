@echo off
setlocal

set BUILD_DIR=%TEMP%\DataStructureVisualizerBuild

if exist "%BUILD_DIR%" rmdir /s /q "%BUILD_DIR%"
mkdir "%BUILD_DIR%"

dir /s /b src\*.java > "%BUILD_DIR%\sources.txt"
javac -Xlint:-options -source 8 -target 8 -d "%BUILD_DIR%" @"%BUILD_DIR%\sources.txt"
if errorlevel 1 (
    echo.
    echo Compilation failed. Make sure a Java JDK is installed and javac is on PATH.
    exit /b 1
)

java -cp "%BUILD_DIR%;src" ApplicationLauncher
