/** AWS credentials*/

variable "aws_access_key" {
  default = ""
}
variable "aws_secret_key" {
  default = ""
}
variable "region" {
  default = "eu-central-1"
}


/** EC2 ssh key*/
variable "key_name" {
  default = "test_key"
}

variable "public_key" {
  default = "ssh-rsa A..."
}


/** ElasticBeanstalk application*/

variable "beanstalk_app_name" {
  default = "test-application"
}

/** ElasticBeanstalk environment*/

variable "beanstalk_env_name" {
  default = "test-environment"
}


variable "private_subnets" {
  type    = list(string)
  default = ["subnet-YY1", "subnet-YY2"]
}

/** public subnets for load balancer to be publicly available */
variable "loadbalancer_subnets" {
  type    = list(string)
  default = ["subnet-XX1", "subnet-XX2"]
}

variable "dev_vpc_id" {
  default = "vpc-XXX"
}

variable "solution_stack_name" {
  default = "64bit Amazon Linux 2018.03 v3.4.19 running Tomcat 8.5 Java 8"
}

##  EnvironmentVariables

variable "settings" {
  type = list(object(
    {
      namespace = string
      name      = string
      value     = string
    }))
  default = [
    {
      namespace = "aws:elasticbeanstalk:application:environment"
      name      = "TEST_ENV"
      value     = "test-environment"
    },

    ## postgres database
    {
      namespace = "aws:elasticbeanstalk:application:environment"
      name      = "config.postgres.password"
      value     = ""
    },
    {
      namespace = "aws:elasticbeanstalk:application:environment"
      name      = "config.postgres.url"
      value     = ""
    },
    {
      namespace = "aws:elasticbeanstalk:application:environment"
      name      = "config.postgres.username"
      value     = ""
    },

    ## redis server
    {
      namespace = "aws:elasticbeanstalk:application:environment"
      name      = "config.redis.host"
      value     = ""
    },
    {
      namespace = "aws:elasticbeanstalk:application:environment"
      name      = "config.redis.port"
      value     = "6379"
    },
    ##### VPC settings
    {
      namespace = "aws:ec2:vpc"
      name      = "ELBScheme"
      value     = "public"
    },
    {
      namespace = "aws:ec2:vpc"
      name      = "AssociatePublicIpAddress"
      value     = "False"
    },
    {
      namespace = "aws:elasticbeanstalk:environment:process:default"
      name      = "MatcherHTTPCode"
      value     = "200"
    },
    {
      namespace = "aws:ec2:vpc"
      name      = "ELBScheme"
      value     = "internet facing"
    },
    ##load balancer settings
    {
      namespace = "aws:elasticbeanstalk:environment"
      name      = "LoadBalancerType"
      value     = "application"
    },
    {
      namespace = "aws:elbv2:listener:443"
      name      = "ListenerEnabled"
      value     = "true"
    },
    {
      namespace = "aws:elbv2:listener:443"
      name      = "Protocol"
      value     = "HTTPS"
    },
    {
      namespace = "aws:elbv2:listener:443"
      name      = "SSLCertificateArns"
      value     = ""
    },
    {
      namespace = "aws:elbv2:listener:443"
      name      = "SSLPolicy"
      value     = "ELBSecurityPolicy-2016-08"
    },
    ####
    {
      namespace = "aws:autoscaling:launchconfiguration"
      name      = "InstanceType"
      value     = "t3.medium"
    },
    {
      namespace = "aws:autoscaling:launchconfiguration"
      name      = "EC2KeyName"
      value     = "test_key"
    },
    {
      namespace = "aws:autoscaling:launchconfiguration"
      name      = "IamInstanceProfile"
      value     = "aws-elasticbeanstalk-ec2-role"
    },
    {
      namespace = "aws:autoscaling:asg"
      name      = "MinSize"
      value     = 1
    },
    {
      namespace = "aws:autoscaling:asg"
      name      = "MaxSize"
      value     = 2
    },
    {
      namespace = "aws:elasticbeanstalk:healthreporting:system"
      name      = "SystemType"
      value     = "enhanced"
    }
  ]
}


