package aws;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.SystemPropertiesCredentialsProvider;

public class CustomAWSCredentialsProvider extends SystemPropertiesCredentialsProvider {

    private static final String AWS_ACCESS_KEY_ID ="<YOUR_AWS_ACCESS_KEY_ID>";
    private static final String AWS_SECRET_KEY = "YOUR_AWS_SECRET_KEY";

    public AWSCredentials getCredentials() {
        return new BasicAWSCredentials(AWS_ACCESS_KEY_ID, AWS_SECRET_KEY);
    }
}
