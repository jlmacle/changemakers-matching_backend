:: The start command is used as a workaround 
:: for script freezing issues.

:: Refer to these posts for requirements for running the scripts
:: https://fromcodetodemo.wordpress.com/2022/12/11/database-postgresql-running-the-service-with-docker/
:: https://fromcodetodemo.wordpress.com/2023/01/08/stepping-stones-running-back-end-and-front-end-with-docker/

echo off

echo **** Starting Docker Desktop.
cmd /c "C:\Program Files\Docker\Docker\Docker Desktop.exe" 

start docker_build_by_Spring.bat


