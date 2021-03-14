#!/bin/bash
set -e
#ssh -o StrictHostKeyChecking=no ubuntu@$EC2_PUBLIC_IP_ADDRESS  mkdir /home/ubuntu/app
scp  -o StrictHostKeyChecking=no -r ./.env ./docker-compose.staging.yml ubuntu@$EC2_PUBLIC_IP_ADDRESS:/home/ubuntu/TradeProject/TradeEngine
#mkdir -p ~/.ssh
#echo "private key ---------"
#echo "$SSH_KEY"
#echo "$SSH_KEY" | tr -d '\r' > ~/.ssh/id_rsa
#cat ~/.ssh/id_rsa
#chmod 600 ~/.ssh/id_rsa
#eval "$(ssh-agent -s)"
#ssh-add ~/.ssh/id_rsa
#  #  ssh-keyscan -H 'gitlab.com' >> ~/.ssh/known_hosts
#  #  chmod +x ./deploy.sh
#scp  -o StrictHostKeyChecking=no -r  ./docker-compose.yml ubuntu@$EC2_PUBLIC_IP_ADDRESS:/home/ubuntu/TradeProject/TradeEngine
ssh -o StrictHostKeyChecking=no ubuntu@$EC2_PUBLIC_IP_ADDRESS << 'ENDSSH'
  cd /home/ubuntu/TradeProject/TradeEngine
  export $(cat ./.env | xargs)
  echo "$DOCKER_PASSWORD" | docker login -u "$DOCKER_USERNAME" --password-stdin
  docker pull elijahahianyo/trade-project:trade-engine-prod
  docker-compose -f docker-compose.staging.yml  up -d
ENDSSH

