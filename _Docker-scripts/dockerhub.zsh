echo
echo "docker login."
docker login

echo
echo "docker tag."
docker tag changemakers-matching-backend:0.2  jlmacle/changemakers-matching-backend:0.2

echo
echo "docker push."
docker push jlmacle/changemakers-matching-backend:0.2