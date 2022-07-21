// aws credentials
provider "aws" {
    access_key = var.aws_access_key
    secret_key = var.aws_secret_key
    region = var.region
}

/** Deploy a new Key pair on EC2,
 SSH key-pair(public key, private key) is used for authentication
 you can SSH into your EC2 machine using the private key. */

resource "aws_key_pair" "deployer" {
  key_name   = var.key_name
  public_key = var.public_key
}

# Create elastic beanstalk application

resource "aws_elastic_beanstalk_application" "beanstalk-app" {
  name = var.beanstalk_app_name
}

# Create elastic beanstalk Environment

resource "aws_elastic_beanstalk_environment" "beanstalk-app-env" {
  name                = var.beanstalk_env_name
  application         = var.beanstalk_app_name
  solution_stack_name = var.solution_stack_name

  dynamic "setting" {
    for_each = var.settings
    content {
      namespace = setting.value["namespace"]
      name      = setting.value["name"]
      value     = setting.value["value"]
    }
  }

  setting {
    namespace = "aws:elasticbeanstalk:application:environment"
    name      = "AWS_ACCESS_KEY_ID"
    value     = var.aws_access_key
  }
  setting {
    namespace = "aws:elasticbeanstalk:application:environment"
    name      = "AWS_SECRET_KEY"
    value     = var.aws_secret_key
  }
  setting {
    namespace = "aws:ec2:vpc"
    name      = "VPCId"
    value     = var.dev_vpc_id
  }

  /** Subnets = contain the ID of the Auto Scaling group subnet  (private) */
  setting {
    namespace = "aws:ec2:vpc"
    name      = "Subnets"
    value     = join(",", var.private_subnets)
  }

  /** ELBSubnets = contain the ID of the subnet for the load balancer (public) */
  setting {
    namespace = "aws:ec2:vpc"
    name      = "ELBSubnets"
    value     = join(",", sort(var.loadbalancer_subnets))
  }
}


