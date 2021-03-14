mkdir -p ~/.ssh
echo "private key ---------"
echo "$SECRET_KEY"
echo "$PRIVATE_KEY" | tr -d '\r' > ~/.ssh/id_rsa
cat ~/.ssh/id_rsa
chmod 400 ~/.ssh/id_rsa
eval "$(ssh-agent -s)"
ssh-add ~/.ssh/id_rsa
  #  ssh-keyscan -H 'gitlab.com' >> ~/.ssh/known_hosts
  #  chmod +x ./deploy.sh
scp  -o StrictHostKeyChecking=no -r  ./docker-compose.yml ubuntu@$EC2_PUBLIC_IP_ADDRESS:/home/ubuntu/

