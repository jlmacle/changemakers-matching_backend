:: The start command is used as a workaround 
:: for script freezing issues.

:: Work in progress TODO: line to remove eventually
:: Refer to these posts for requirements for running the scripts
:: https://fromcodetodemo.wordpress.com/2022/12/11/database-postgresql-running-the-service-with-docker/
:: https://fromcodetodemo.wordpress.com/2023/01/08/stepping-stones-running-back-end-and-front-end-with-docker/

:: The PostgreSQL Docker service has to be running before starting the script
:: A script can be found in scripts/a-postgresql-docker-service

echo off

echo **** Starting Docker Desktop.
start cmd /c "C:\Program Files\Docker\Docker\Docker Desktop.exe" 
echo Waiting for Docker Desktop to start
timeout /T 60

start docker_build_by_Spring.bat


