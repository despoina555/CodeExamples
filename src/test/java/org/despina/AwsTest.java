package org.despina;

import aws.AwsClient;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AwsTest {

    @Before // setup()
    public void before() throws Exception {
        System.out.println("Let's test AWS APIs!");
    }

    @Test
    public void testGetCNAME() {

        AwsClient client = new AwsClient();

        final String envName = client.getEc2TagValue("i-05555ddddd3333d", "Name");
        System.out.println(" envName= " + envName);

        final String cname = client.getCNAME(envName);
        System.out.println(" cname= " + cname);

        Assert.assertEquals(envName, "uat");
        Assert.assertNotNull(cname);
    }
}
