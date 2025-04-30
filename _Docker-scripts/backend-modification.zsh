#--------------------------------------------------------------------------------------------------------
# Re-building the image 
#--------------------------------------------------------------------------------------------------------
echo 
echo "Re-building the Docker image with the new code."
echo "- Removing the previous image if existant"
docker image rm changemakers-matching-backend:0.2  2> /dev/null

echo "- Building the Docker image with: mvn spring-boot:build-image."
cd ..
mvn spring-boot:build-image

#--------------------------------------------------------------------------------------------------------
# Rebuilding the stack
#--------------------------------------------------------------------------------------------------------
echo
echo "Re-building the Docker stack with the new image."
echo "- Removing the previous stack if existant"
docker stack rm cm_stack 2> /dev/null
sleep 10

echo "- Re-building the Docker stack"
docker stack deploy -c _Docker-scripts/docker-compose-stack-local-images.yml cm_stack
sleep 60

#--------------------------------------------------------------------------------------------------------
# Checking on the services
#--------------------------------------------------------------------------------------------------------
echo
echo "Checking the backend service"
echo "Should return response headers and {"username":"alice","email":"alice@mail.com","projectName":null}"
echo
curl http://localhost:8080/representatives/new-account -H "Content-Type: application/json" -d '{"username": "alice", "password": "s3cr3t"}' -i
