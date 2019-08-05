export AWS_IMAGE_ID=$(aws ec2 describe-images --owners 099720109477 --filters 'Name=name,Values=ubuntu/images/hvm-ssd/ubuntu-bionic-18.04-amd64-server-????????' 'Name=state,Values=available' --output json | jq -r '.Images | sort_by(.CreationDate) | last(.[]).ImageId')
export AWS_KEYPAIR_NAME=johnowl_aws
export AWS_SECURITY_GROUP_ID=$(.SecurityGroups[] | jq 'select( .GroupName == "only-ssh" ) | .GroupId' | sed 's/"//g')
export AWS_SUBNET_ID=$(aws ec2 describe-subnets | jq '[ .Subnets[] |  select( .OwnerId == "396398481082" and .DefaultForAz == true) ] | first | .SubnetId' | sed 's/"//g')