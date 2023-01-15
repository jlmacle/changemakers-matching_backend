echo off

:: Uncomment before running the script
 set CM_scriptsFolder=scripts/outside-the-deployment-pipeline_from-build-to-docker-stack-and-DockerHub

cd ../..

echo **** Building the Docker image with spring-boot:build-image.
cmd /c "mvn spring-boot:build-image"

echo **** Removing the jars in the target directory
del .\target\*.jar*

cd %CM_scriptsFolder%
start docker_stack_local.bat

::exit