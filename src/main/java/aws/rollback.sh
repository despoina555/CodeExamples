##destroy terraform resources:

### destroy the beanstalk environment
 terraform destroy -target= aws_elastic_beanstalk_environment.beanstalk-app-env

### destroy the beanstalk application
terraform destroy -target=aws_elastic_beanstalk_application.beanstalk-app


## to destroy all
terraform destroy

