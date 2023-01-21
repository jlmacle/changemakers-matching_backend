echo off

echo **** Starting the SonarQube server.
:: The SonarQube zip has been downloaded, 
:: and the Path variable for the user account has been edited to add StartSonar.bat
start "SonarQube" StartSonar.bat 
echo **** Waiting for the SonarQube server to start
timeout /T 90

cd ..
echo **** Starting the code quality analysis.
mvn clean verify sonar:sonar -Dsonar.projectKey=$CM_Backend-Sonarqube_project_key -Dsonar.host.url=http://127.0.0.1:9000 -Dsonar.login=$CM_Backend-Sonarqube_token