
ec2_instance_id=$(cat ec2_instances | tr -d '/n' | tr -d '[:space:]')

ec2_public_dns_name=$(aws ec2 describe-instances | jq --arg ec2_instance_id "$ec2_instance_id" '.Reservations[].Instances[] | select(.InstanceId == $ec2_instance_id) | .PublicDnsName' | sed 's/"//g')

echo "Installing openjdk-8"

ssh -o "StrictHostKeyChecking=no" -i ~/keys/johnowl_aws.pem ubuntu@$(echo $ec2_public_dns_name) 'sudo apt-get update \
 && sudo apt-get install openjdk-8-jdk --yes \
 && wget "https://github.com/johnowl/owl-aws-studies/raw/master/release/cart-0.0.1-SNAPSHOT.jar" \
 && wget "https://github.com/johnowl/owl-aws-studies/raw/master/release/product-0.0.1-SNAPSHOT.jar"'

echo "Running cart service"
ssh -o "StrictHostKeyChecking=no" -i ~/keys/johnowl_aws.pem ubuntu@$(echo $ec2_public_dns_name) 'nohup java -jar cart-0.0.1-SNAPSHOT.jar > cart.log 2> cart.err < /dev/null &'

echo "Running product service"
ssh -o "StrictHostKeyChecking=no" -i ~/keys/johnowl_aws.pem ubuntu@$(echo $ec2_public_dns_name) 'nohup java -jar product-0.0.1-SNAPSHOT.jar > product.log 2> product.err < /dev/null &'