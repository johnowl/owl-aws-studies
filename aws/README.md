# Setup and Destroy environment on AWS

- **variables.sh**: this script retrievies the AWS Image Id from Ubuntu 18.04 x86, Security Group Id of a group named `only-ssh` and a defaut Subnet Id.
- **create.sh**: this script creates a T2 Micro EC2 instance and saves its id in the `ec2_instances` file.
- **destroy.sh**: this script destroy all the EC2 instances referenced in the `ec2_instances` file.
- **ec2_instances**: this file stores all the EC2 instances created by the `create.sh` script.