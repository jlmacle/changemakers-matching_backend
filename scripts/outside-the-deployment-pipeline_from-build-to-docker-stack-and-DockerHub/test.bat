
echo off
echo %CM_version%
echo %DB_NAME%

if "%DB_NAME%"=="db" (echo "db found") else (echo "db not found as a value")

echo %CM_scriptsFolder%
cd ..
dir
cd %CM_scriptsFolder%