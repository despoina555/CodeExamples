package aws;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.ec2.model.DescribeTagsRequest;
import com.amazonaws.services.ec2.model.DescribeTagsResult;
import com.amazonaws.services.ec2.model.Filter;
import com.amazonaws.services.ec2.model.TagDescription;

import com.amazonaws.services.elasticbeanstalk.AWSElasticBeanstalkClient;
import com.amazonaws.services.elasticbeanstalk.AWSElasticBeanstalkClientBuilder;
import com.amazonaws.services.elasticbeanstalk.model.EnvironmentDescription;
import com.amazonaws.util.EC2MetadataUtils;


import java.util.Collections;
import java.util.List;

public class AwsClient {


    private AmazonEC2Client ec2Client;
    private AWSElasticBeanstalkClient beanstalkClient;

    public AwsClient() {

        ec2Client = (AmazonEC2Client) AmazonEC2ClientBuilder.standard()
                .withCredentials(new CustomAWSCredentialsProvider())
                .withRegion(Regions.EU_CENTRAL_1)
                .build();

        beanstalkClient = (AWSElasticBeanstalkClient) AWSElasticBeanstalkClientBuilder.standard()
                .withCredentials(new CustomAWSCredentialsProvider())
                .withRegion(Regions.EU_CENTRAL_1)
                .build();
    }

    public String getInstanceId() {
        final String instanceId = EC2MetadataUtils.getInstanceId();
        System.out.println("instanceId ={}" + instanceId);
        return instanceId;
    }

    public String getEc2TagValue(final String instanceId, final String tagKey) {
        System.out.println("Get Environment Info");

        if (instanceId == null)
            return null;

        final DescribeTagsRequest req = new DescribeTagsRequest()
                .withFilters(new Filter("resource-id", Collections.singletonList(instanceId)));
        final DescribeTagsResult describeTagsResult = ec2Client.describeTags(req);
        final List<TagDescription> tags = describeTagsResult.getTags();

        final String tagValue = tags.stream()
                .filter(it -> it.getKey().equalsIgnoreCase(tagKey))
                .map(it -> it.getValue())
                .findAny()
                .orElse("");

        return tagValue;
    }


    public String getCNAME(final String envName) {

        final List<EnvironmentDescription> environments = beanstalkClient.describeEnvironments().getEnvironments();
        final String cname = environments.stream()
                .filter(it -> it.getEnvironmentName().equalsIgnoreCase(envName))
                .map(it -> it.getCNAME())
                .findAny()
                .orElse("");

        System.out.println(">>>> cname ={} " + cname);

        return cname;
    }

}
