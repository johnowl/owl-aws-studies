echo "Creating variables"
source variables.sh

echo "Creating instance"
aws ec2 run-instances --image-id $AWS_IMAGE_ID --count 1 --instance-type t2.micro --key-name $AWS_KEYPAIR_NAME --security-group-ids $AWS_SECURITY_GROUP_ID --subnet-id $AWS_SUBNET_ID | jq '.Instances[0].InstanceId' | sed 's/"//g' >> ec2_instances
echo "Instance created $(cat ec2_instances)"

echo "Installing software"
source install.sh