echo off

echo **** Starting Docker Desktop.
cmd /c "C:\Program Files\Docker\Docker\Docker Desktop.exe" 

echo **** Pulling postgres:alpine
docker pull postgres:alpine

echo **** Starting a Docker PostgreSQL service with Docker stack
docker stack deploy -c ./docker-compose-postgresql-stack_local-images.yml postgresql-stack_local-images