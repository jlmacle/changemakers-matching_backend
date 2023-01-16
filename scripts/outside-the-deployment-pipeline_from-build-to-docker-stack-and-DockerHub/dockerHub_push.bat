:: docker tag local-image:tagname new-repo:tagname
:: docker push new-repo:tagname

:: Uncomment before running the script
set CM_version=demo0

@echo off
echo **** Tagging and pushing the image on DockerHub

:: This version assumes to be signed in on Docker Desktop
docker tag changemakers-matchmaking-backend:%CM_version% jlmacle/changemakers-matchmaking-backend:%CM_version%
docker push jlmacle/changemakers-matchmaking-backend:%CM_version%

:: exit