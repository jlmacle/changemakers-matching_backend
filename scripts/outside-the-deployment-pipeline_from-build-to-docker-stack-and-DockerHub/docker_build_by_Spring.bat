echo off

:: Uncomment before running the script
 set CM_scriptsFolder=scripts/outside-the-deployment-pipeline_from-build-to-docker-stack-and-DockerHub

cd ../..

echo **** Removing a potential PostgreSQL service that would have been created with the application stack
docker stack rm demo0-stack_DockerHub-images

echo **** Building the Docker image with spring-boot:build-image.
cmd /c "mvn spring-boot:build-image"

echo **** Removing the jars in the target directory
del .\target\*.jar*

cd %CM_scriptsFolder%
start docker_stack_local.bat

::exit