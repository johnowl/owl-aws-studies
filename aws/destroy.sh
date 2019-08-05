aws ec2 terminate-instances --instance-ids $(cat ec2_instances | tr '\n' ' ')
echo "" > ec2_instances