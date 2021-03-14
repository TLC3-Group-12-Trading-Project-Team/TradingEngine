#!/bin/bash
set -e
ssh ubuntu@$EC2_PUBLIC_IP_ADDRESS  -v exit


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
#scp  -o StrictHostKeyChecking=no -r  ./docker-compose.yml ubuntu@$EC2_PUBLIC_IP_ADDRESS:/home/ubuntu/

