echo off

echo **** Starting the services (backend, PostgreSQL) with docker stack
docker pull postgres:alpine
docker stack deploy -c ./docker-compose-backend-stack_local-images.yml backend-stack_local-images

start dockerHub_push.bat

::exit